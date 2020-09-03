package com.coder.zzq.smartshow.dialog.data_item;

import com.coder.zzq.toolkit.Utils;

public class ObjectDataItem<Data> extends DataItem {
    protected Data mData;
    protected Data mNewData;

    public void setNewData(Data data) {
        mNewData = data;
    }

    public Data getCurrentData() {
        return mNewData;
    }


    @Override
    public Object toData() {
        return mNewData;
    }

    @Override
    public <Data> Data toData(Class<Data> dataClass) {
        if (dataClass == null) {
            throw new IllegalArgumentException("the data class can not be null!");
        }
        return dataClass.cast(mNewData);
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    protected boolean judgeDataChanged() {
        return !Utils.equals(mData, mNewData);
    }


    @Override
    public void onUpdateData() {
        mData = mNewData;
    }

}
