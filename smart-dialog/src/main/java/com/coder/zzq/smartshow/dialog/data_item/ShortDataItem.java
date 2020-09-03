package com.coder.zzq.smartshow.dialog.data_item;

public class ShortDataItem extends DataItem {
    protected short mData;
    protected short mNewData;

    public void setNewData(short data) {
        mNewData = data;
    }

    public short getCurrentData() {
        return mNewData;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    protected boolean judgeDataChanged() {
        return mData != mNewData;
    }


    @Override
    public short toShort() {
        return mNewData;
    }


    @Override
    public void onUpdateData() {
        mData = mNewData;
    }
}
