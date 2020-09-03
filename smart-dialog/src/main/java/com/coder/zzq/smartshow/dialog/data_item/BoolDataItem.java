package com.coder.zzq.smartshow.dialog.data_item;

public class BoolDataItem extends DataItem {
    private boolean mData;
    private boolean mNewData;

    public void setNewData(boolean data) {
        mNewData = data;
    }

    public boolean getCurrentData() {
        return mNewData;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean toBoolean() {
        return mNewData;
    }

    @Override
    protected boolean judgeDataChanged() {
        return mData != mNewData;
    }

    @Override
    public void onUpdateData() {
        mData = mNewData;
    }


}
