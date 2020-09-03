package com.example.annotation_compiler;

import com.example.annotations.BuildDelegate;
import com.example.annotations.BuildParam;
import com.example.annotations.CommonParams;
import com.example.annotations.DialogProvider;
import com.example.annotations.ParamValue;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class SmartShowProcessor extends AbstractProcessor {
    private String[] supportedAnnotations = {
            DialogProvider.class.getCanonicalName(),
            BuildDelegate.class.getCanonicalName(),
    };

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(supportedAnnotations));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(DialogProvider.class);
        for (Element element : elements) {
            TypeElement annotatedElement = (TypeElement) element;
            final DialogProvider dialogBuilder = annotatedElement.getAnnotation(DialogProvider.class);
            BuildParam[] buildParams = dialogBuilder.buildParams();
            List<FieldSpec> fieldsOfBuildParamsClass = new ArrayList<>();
            List<MethodSpec> fieldSetterMethodsForSmartDialog = new ArrayList<>();
            List<MethodSpec> methodsOfBuildParamsClass = new ArrayList<>();
            CodeBlock.Builder updateCodeBlockBuilder = CodeBlock.builder();
            CodeBlock.Builder getDataByNameBlockBuilder = CodeBlock.builder()
                    .addStatement("super.getData(dataName)")
                    .beginControlFlow("switch(dataName)");

            CodeBlock.Builder initConstructorOfBuildParams = CodeBlock.builder();

            CodeBlock.Builder resetMethodCodeBuilder = CodeBlock.builder();


            ClassName dialogClassName = ClassName.get("", parseProviderName(annotatedElement.getSimpleName().toString()));
            ClassName buildParamsClassName = ClassName.get("", dialogClassName.simpleName(), "BuildParams");
            List<BuildParam> headerParams = getDelegateBuildParams(roundEnvironment, new ParseTypeCallback() {
                @Override
                public void parse() {
                    dialogBuilder.headerDelegate();
                }
            });

            List<BuildParam> bodyParams = getDelegateBuildParams(roundEnvironment, new ParseTypeCallback() {
                @Override
                public void parse() {
                    dialogBuilder.bodyDelegate();
                }
            });

            List<BuildParam> footerParams = getDelegateBuildParams(roundEnvironment, new ParseTypeCallback() {
                @Override
                public void parse() {
                    dialogBuilder.footerDelegate();
                }
            });

            List<BuildParam> extendsParams = getExtendsParams(roundEnvironment, new ParseTypeCallback() {

                @Override
                public void parse() {
                    dialogBuilder.extendsParams();
                }
            });

            List<BuildParam> buildParamList = new ArrayList<>();
            buildParamList.addAll(extendsParams);
            buildParamList.addAll(headerParams);
            buildParamList.addAll(Arrays.asList(buildParams));
            buildParamList.addAll(bodyParams);
            buildParamList.addAll(footerParams);
            for (final BuildParam buildParam : buildParamList) {
                TypeWrapper typeWrapper = ProcessUtil.parseClassType(new ParseTypeCallback() {
                    @Override
                    public void parse() {
                        buildParam.type();
                    }
                });


                TypeWrapper[] annotationsForFiled = ProcessUtil.parseClassTypes(new ParseTypeCallback() {
                    @Override
                    public void parse() {
                        buildParam.annotations();
                    }
                });

                TypeWrapper[] typeVars = ProcessUtil.parseClassTypes(new ParseTypeCallback() {
                    @Override
                    public void parse() {
                        buildParam.typeVariable();
                    }
                });

                List<TypeName> validTypeVarList = new ArrayList<>();
                for (int index = 0; index < typeVars.length; index++) {
                    if (isValidTypeVar(typeVars[index].mTypeMirror)) {
                        validTypeVarList.add(typeVars[index].mTypeName);
                    }
                }

                List<AnnotationSpec> annotationSpecs = new ArrayList<>();
                for (TypeWrapper wrapper : annotationsForFiled) {
                    annotationSpecs.add(
                            AnnotationSpec.builder((ClassName) wrapper.mTypeName).build()
                    );
                }


                TypeName dataItem = parseDataItem(typeWrapper, typeVars);
                String fieldName = "m" + bigHump(buildParam.name());
                FieldSpec field = FieldSpec.builder(dataItem, fieldName)
                        .addModifiers(Modifier.PROTECTED)
                        .build();
                FieldSpec fieldParamName = FieldSpec.builder(String.class, transferFieldParamName(buildParam.name()))
                        .addModifiers(Modifier.STATIC, Modifier.PUBLIC, Modifier.FINAL)
                        .initializer("$S", buildParam.name())
                        .build();
                fieldsOfBuildParamsClass.add(field);
                fieldsOfBuildParamsClass.add(fieldParamName);

                MethodSpec fieldSetter = MethodSpec.methodBuilder(buildParam.name())
                        .addModifiers(Modifier.PUBLIC)
                        .returns(buildParamsClassName)
                        .addParameter(ParameterSpec.builder(validTypeVarList.isEmpty()
                                ? typeWrapper.mTypeName
                                : ParameterizedTypeName.get((ClassName) typeWrapper.mTypeName, validTypeVarList.toArray(new TypeName[validTypeVarList.size()])), buildParam.name())
                                .addAnnotations(annotationSpecs)
                                .build())
                        .beginControlFlow("if($L == null)", fieldName)
                        .addStatement("$L = new $T()", fieldName, typeWrapper.mTypeMirror.getKind().isPrimitive() || typeWrapper.mTypeMirror.toString().equals("java.lang.String")
                                ? dataItem
                                : ClassNames.OBJECT_DATA_ITEM)
                        .addStatement("$L.setName($T.$L)", fieldName, buildParamsClassName, transferFieldParamName(buildParam.name()))
                        .endControlFlow()
                        .addStatement("$L.setNewData($L)", fieldName, buildParam.name())
                        .addStatement("return this")
                        .build();

                MethodSpec fieldSetterForSmartDialog = MethodSpec.methodBuilder(buildParam.name())
                        .addModifiers(Modifier.PUBLIC)
                        .returns(dialogClassName)
                        .addParameter(ParameterSpec.builder(validTypeVarList.isEmpty()
                                ? typeWrapper.mTypeName
                                : ParameterizedTypeName.get((ClassName) typeWrapper.mTypeName, validTypeVarList.toArray(new TypeName[validTypeVarList.size()])), buildParam.name())
                                .addAnnotations(annotationSpecs)
                                .build())
                        .addStatement("mDialogProvider.getBuildParams().$L($L)", buildParam.name(), buildParam.name())
                        .addStatement("return this")
                        .build();
                fieldSetterMethodsForSmartDialog.add(fieldSetterForSmartDialog);
                methodsOfBuildParamsClass.add(fieldSetter);

                MethodSpec fieldGetter = MethodSpec.methodBuilder(transferFieldGetterName(buildParam.name(), typeWrapper.mTypeMirror.getKind() == TypeKind.BOOLEAN))
                        .addModifiers(Modifier.PUBLIC)
                        .returns(validTypeVarList.isEmpty()
                                ? typeWrapper.mTypeName
                                : ParameterizedTypeName.get((ClassName) typeWrapper.mTypeName, validTypeVarList.toArray(new TypeName[validTypeVarList.size()])))
                        .addAnnotations(annotationSpecs)
                        .addStatement("return $L == null ? $L : $L.getCurrentData()", fieldName, getDefaultValue(typeWrapper, buildParam.defaultValue()), fieldName)
                        .build();

                methodsOfBuildParamsClass.add(fieldGetter);

                if (canSetDefaultValue(typeWrapper.mTypeMirror, buildParam.defaultValue())) {
                    initConstructorOfBuildParams
                            .addStatement("$L = new $T()", fieldName, typeWrapper.mTypeMirror.getKind().isPrimitive() || typeWrapper.mTypeMirror.toString().equals("java.lang.String")
                                    ? dataItem
                                    : ClassNames.OBJECT_DATA_ITEM)
                            .addStatement("$L.setName($L)", fieldName, transferFieldParamName(buildParam.name()))

                            .addStatement("$L.setNewData($L)", fieldName, typeWrapper.mTypeMirror.toString().equals("java.lang.String") ? "\"" + buildParam.defaultValue() + "\"" : buildParam.defaultValue());
                }


                updateCodeBlockBuilder
                        .beginControlFlow("if($L != null && $L.isDataChanged())", fieldName, fieldName)
                        .addStatement("$L.updateData()", fieldName)
                        .addStatement("mOnBuildParamChangedListener.onBuildParamChanged($L.getName())", fieldName)
                        .endControlFlow();

                getDataByNameBlockBuilder.addStatement("case $L:return $L", transferFieldParamName(buildParam.name()), fieldName);

                resetMethodCodeBuilder.beginControlFlow("if($L != null)", fieldName)
                        .addStatement("$L.reset()", fieldName)
                        .endControlFlow();


            }


            MethodSpec updateMethod = MethodSpec.methodBuilder("update")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(TypeName.VOID)
                    .addStatement("super.update()")
                    .beginControlFlow("if(mOnBuildParamChangedListener == null)")
                    .addStatement("return")
                    .endControlFlow()
                    .addCode(updateCodeBlockBuilder.build())
                    .build();

            methodsOfBuildParamsClass.add(updateMethod);

            getDataByNameBlockBuilder
                    .addStatement("default:return null")
                    .endControlFlow();
            MethodSpec getDataByName = MethodSpec.methodBuilder("getData")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(ClassNames.DATA_ITEM)
                    .addParameter(String.class, "dataName")
                    .addCode(getDataByNameBlockBuilder.build())
                    .build();

            methodsOfBuildParamsClass.add(getDataByName);

            MethodSpec resetMethod = MethodSpec.methodBuilder("reset")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(TypeName.VOID)
                    .addStatement("super.reset()")
                    .addCode(resetMethodCodeBuilder.build())
                    .build();

            for (ParamValue paramValue : dialogBuilder.replaceDefaultValues()) {
                initConstructorOfBuildParams.addStatement("m$L.setNewData($L)", bigHump(paramValue.key()), paramValue.newValue());
            }

            MethodSpec constructorOfBuildParams = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("super()")
                    .addCode(initConstructorOfBuildParams.build())
                    .build();
            TypeSpec buildParamsClass = TypeSpec.classBuilder(buildParamsClassName)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addFields(fieldsOfBuildParamsClass)
                    .addMethods(methodsOfBuildParamsClass)
                    .addMethod(constructorOfBuildParams)
                    .addMethod(resetMethod)
                    .superclass(ParameterizedTypeName.get(ClassNames.BASE_DIALOG_BUILDER_PARAMS, buildParamsClassName))
                    .build();


            MethodSpec createDialogByFragmentManager = MethodSpec.methodBuilder("create")
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .returns(dialogClassName)
                    .addParameter(ClassNames.FRAGMENT_MANAGER, "lifecycleBinder")
                    .addParameter(String.class, "businessTag")
                    .addStatement("$T fragment = ($T) lifecycleBinder.findFragmentByTag(businessTag)", ClassNames.DIALOG_HOST_FRAGMENT, ClassNames.DIALOG_HOST_FRAGMENT)
                    .beginControlFlow("if (fragment == null)")
                    .addStatement("$T smartDialog = new $T()", dialogClassName, dialogClassName)
                    .addStatement("smartDialog.setDialogProvider(new $T())", ClassName.get(annotatedElement))
                    .addStatement("smartDialog.getDialogProvider().setBuildParams(new $T())", buildParamsClassName)
                    .addStatement("smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider)")
                    .addStatement("SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog)")
                    .addStatement("$T.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity())", ClassNames.DIALOG_HOST_FRAGMENT)
                    .addStatement("return smartDialog")
                    .endControlFlow()
                    .addStatement("return ($T) $T.retrieve(fragment.getIdentity())", dialogClassName, ClassNames.DIALOG_COORDINATOR)
                    .build();

            MethodSpec createDialogByActivity = MethodSpec.methodBuilder("create")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(dialogClassName)
                    .addParameter(ClassNames.ACTIVITY, "lifecycleBinder")
                    .addParameter(String.class, "businessTag")
                    .addStatement("return create(lifecycleBinder.getSupportFragmentManager(),businessTag)")
                    .build();

            MethodSpec createDialogByFragment = MethodSpec.methodBuilder("create")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(dialogClassName)
                    .addParameter(ClassNames.FRAGMENT, "lifecycleBinder")
                    .addParameter(String.class, "businessTag")
                    .addStatement("return create(lifecycleBinder.getChildFragmentManager(),businessTag)")
                    .build();

            MethodSpec resetWhenShowAgain = MethodSpec.methodBuilder("resetWhenShowAgain")
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(TypeName.BOOLEAN, "resetWhenShowAgain")
                    .returns(dialogClassName)
                    .addStatement("mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain)")
                    .addStatement("return this")
                    .build();

            TypeSpec dialogClass = TypeSpec.classBuilder(dialogClassName)
                    .superclass(ParameterizedTypeName.get(ClassNames.BASE_DIALOG, ClassName.get(annotatedElement)))
                    .addType(buildParamsClass)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(createDialogByActivity)
                    .addMethod(createDialogByFragment)
                    .addMethod(createDialogByFragmentManager)
                    .addMethod(resetWhenShowAgain)
                    .addMethods(fieldSetterMethodsForSmartDialog)
                    .build();


            try {
                JavaFile.builder(processingEnv.getElementUtils().getPackageOf(annotatedElement).toString(), dialogClass)
                        .build()
                        .writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private String parseProviderName(String simpleName) {
        if (simpleName.endsWith("Provider")) {
            simpleName = simpleName.substring(0, simpleName.lastIndexOf("Provider"));
        }
        if (!simpleName.endsWith("Dialog")) {
            simpleName = simpleName + "Dialog";
        }
        return simpleName;
    }

    private List<BuildParam> getExtendsParams(RoundEnvironment roundEnvironment, ParseTypeCallback callback) {
        List<BuildParam> buildParamList = new ArrayList<>();
        TypeWrapper[] typeWrappers = ProcessUtil.parseClassTypes(callback);
        for (TypeWrapper typeWrapper : typeWrappers) {
            buildParamList.addAll(getSingleExtendsParams(roundEnvironment, typeWrapper));
        }

        return buildParamList;
    }


    private List<BuildParam> getSingleExtendsParams(RoundEnvironment roundEnvironment, TypeWrapper typeWrapper) {
        if (typeWrapper.mTypeMirror.getKind() == TypeKind.VOID) {
            return new ArrayList<>(0);
        }

        Set<TypeElement> elements = (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(CommonParams.class);

        TypeElement target = null;

        for (TypeElement element : elements) {
            if (element.getQualifiedName().toString().equals(typeWrapper.mTypeMirror.toString())) {
                target = element;
            }
        }
        if (target == null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "the delegate can not found:" + typeWrapper.mTypeMirror.toString());
        }

        return Arrays.asList(target.getAnnotation(CommonParams.class).buildParams());

    }

    private List<BuildParam> getDelegateBuildParams(RoundEnvironment roundEnvironment, ParseTypeCallback parseDelegateTypeCallback) {
        TypeWrapper partDelegate = ProcessUtil.parseClassType(parseDelegateTypeCallback);

        if (partDelegate.mTypeMirror.getKind() == TypeKind.VOID) {
            return new ArrayList<>(0);
        }

        Set<TypeElement> annotatedDelegates = (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(BuildDelegate.class);

        TypeElement targetDelegateElement = null;
        for (TypeElement delegateElement : annotatedDelegates) {
            if (delegateElement.getQualifiedName().toString().equals(partDelegate.mTypeMirror.toString())) {
                targetDelegateElement = delegateElement;
            }
        }
        if (targetDelegateElement == null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "the delegate can not found:" + partDelegate.mTypeMirror.toString());
        }

        return Arrays.asList(targetDelegateElement.getAnnotation(BuildDelegate.class).buildParams());
    }


    private boolean canSetDefaultValue(TypeMirror typeMirror, String defaultValue) {
        return (typeMirror.getKind().isPrimitive() || typeMirror.toString().equals("java.lang.String")) && !defaultValue.trim().isEmpty();
    }

    private String transferFieldParamName(String name) {
        StringBuilder stringBuilder = new StringBuilder("PARAM");
        int pointer = 0;
        int start = 0;
        while (pointer < name.length()) {
            if (Character.isLowerCase(name.charAt(pointer))) {
                pointer++;
                continue;
            }
            stringBuilder.append("_");
            stringBuilder.append(name.substring(start, pointer).toUpperCase());
            start = pointer;
            pointer++;
        }
        stringBuilder.append("_");
        stringBuilder.append(name.substring(start).toUpperCase());

        return stringBuilder.toString();
    }

    private String getDefaultValue(TypeWrapper typeWrapper, String defaultValue) {
        switch (typeWrapper.mTypeMirror.getKind()) {
            case BOOLEAN:
                return "false";
            case CHAR:
                return "''";
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
                return "0";
            case FLOAT:
                return "0.0f";
            case DOUBLE:
                return "0.0";
            default:
                return typeWrapper.mTypeMirror.toString().equals("java.lang.String") ? "\"\"" : "null";
        }
    }


    private String transferFieldGetterName(String name, boolean isBoolean) {
        return (isBoolean ? "is" : "get") + bigHump(name);
    }

    public String bigHump(String name) {
        if (name.length() < 2) {
            return name.toUpperCase();
        }

        if (Character.isUpperCase(name.charAt(0))) {
            return name;
        }


        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private TypeName parseDataItem(TypeWrapper type, TypeWrapper[] typeVars) {
        switch (type.mTypeMirror.getKind()) {
            case BOOLEAN:
                return ClassNames.BOOL_DATA_ITEM;
            case CHAR:
                return ClassNames.CHAR_DATA_ITEM;
            case BYTE:
                return ClassNames.BYTE_DATA_ITEM;
            case SHORT:
                return ClassNames.SHORT_DATA_ITEM;
            case INT:
                return ClassNames.INT_DATA_ITEM;
            case LONG:
                return ClassNames.LONG_DATA_ITEM;
            case FLOAT:
                return ClassNames.FLOAT_DATA_ITEM;
            case DOUBLE:
                return ClassNames.DOUBLE_DATA_ITEM;
            default:
                if (isStringType(type.mTypeMirror)) {
                    return ClassNames.STRING_DATA_ITEM;
                }
                List<TypeName> validTypeVarList = new ArrayList<>();
                for (int index = 0; index < typeVars.length; index++) {
                    if (isValidTypeVar(typeVars[index].mTypeMirror)) {
                        validTypeVarList.add(typeVars[index].mTypeName);
                    }
                }
                return ClassNames.typeObjectDataItem(validTypeVarList.isEmpty() ? type.mTypeName : ParameterizedTypeName.get((ClassName) type.mTypeName, validTypeVarList.toArray(new TypeName[validTypeVarList.size()])));
        }
    }

    private boolean isValidTypeVar(TypeMirror typeMirror) {
        return !typeMirror.getKind().isPrimitive() && typeMirror.getKind() != TypeKind.VOID;
    }


    private boolean isStringType(TypeMirror typeMirror) {
        return typeMirror.toString().equals("java.lang.String");
    }

}