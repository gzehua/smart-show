package com.coder.zzq.smartshow.dialog.data_item;

public class StringDataItem extends ObjectDataItem<String> {

    @Override
    public String toText() {
        return mNewData;
    }
}
