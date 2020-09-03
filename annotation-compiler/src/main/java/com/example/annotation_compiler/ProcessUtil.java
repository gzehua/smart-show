package com.example.annotation_compiler;

import com.squareup.javapoet.ClassName;

import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;

public class ProcessUtil {
    public static TypeWrapper parseClassType(ParseTypeCallback callback) {
        TypeWrapper typeWrapper = new TypeWrapper();
        try {
            callback.parse();
        } catch (MirroredTypeException ex) {
            typeWrapper.mTypeMirror = ex.getTypeMirror();
            typeWrapper.mTypeName = ClassName.get(ex.getTypeMirror());
        }
        return typeWrapper;
    }


    public static TypeWrapper[] parseClassTypes(ParseTypeCallback callback) {
        TypeWrapper[] typeWrappers = null;
        try {
            callback.parse();
        } catch (MirroredTypesException ex) {
            typeWrappers = new TypeWrapper[ex.getTypeMirrors().size()];
            for (int index = 0; index < typeWrappers.length; index++) {
                TypeWrapper wrapper = new TypeWrapper();
                wrapper.mTypeMirror = ex.getTypeMirrors().get(index);
                wrapper.mTypeName = ClassName.get(wrapper.mTypeMirror);
                typeWrappers[index] = wrapper;
            }
        }
        return typeWrappers;
    }

}
