package com.example.annotation_compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class ClassNames {
    public static final String DATA_ITEM_PACKAGE_NAME = "com.coder.zzq.smartshow.dialog.data_item";
    public static final ClassName DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "DataItem");
    public static final ClassName CHAR_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "CharDataItem");
    public static final ClassName BYTE_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "ByteDataItem");
    public static final ClassName SHORT_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "ShortDataItem");
    public static final ClassName INT_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "IntDataItem");
    public static final ClassName LONG_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "LongDataItem");
    public static final ClassName FLOAT_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "FloatDataItem");
    public static final ClassName DOUBLE_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "DoubleDataItem");
    public static final ClassName BOOL_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "BoolDataItem");
    public static final ClassName STRING_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "StringDataItem");
    public static final ClassName OBJECT_DATA_ITEM = ClassName.get(DATA_ITEM_PACKAGE_NAME, "ObjectDataItem");
    public static final ClassName ON_DATA_CHANGE_LISTENER = ClassName.get(DATA_ITEM_PACKAGE_NAME, "DataItem", "OnDataChangedListener");

    public static final ClassName BASE_DIALOG_BUILDER = ClassName.get("com.coder.zzq.smartshow.dialog.base", "SmartDialogBuilder");

    public static final ClassName BASE_DIALOG = ClassName.get("com.coder.zzq.smartshow.dialog.base", "SmartDialog");

    public static final ClassName BASE_DIALOG_BUILDER_PARAMS = ClassName.get("com.coder.zzq.smartshow.dialog.base", "SmartBuildParams");
    public static final ClassName FRAGMENT_MANAGER = ClassName.get("androidx.fragment.app", "FragmentManager");
    public static final ClassName ACTIVITY = ClassName.get("androidx.appcompat.app", "AppCompatActivity");
    public static final ClassName FRAGMENT = ClassName.get("androidx.fragment.app", "Fragment");
    public static final ClassName DIALOG_HOST_FRAGMENT = ClassName.get("com.coder.zzq.smartshow.dialog.base", "DialogHostFragment");
    public static final ClassName DIALOG_COORDINATOR = ClassName.get("com.coder.zzq.smartshow.dialog.coordinator", "SmartDialogCoordinator");
    public static final ClassName SMART_DIALOG_INTERFACE = ClassName.get("com.coder.zzq.smartshow.dialog.base", "ISmartDialog");


    public static TypeName typeObjectDataItem(TypeName typeVar) {
        return ParameterizedTypeName.get(OBJECT_DATA_ITEM, typeVar);
    }
}
