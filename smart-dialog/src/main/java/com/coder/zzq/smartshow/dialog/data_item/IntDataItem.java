package com.coder.zzq.smartshow.dialog.data_item;

public class IntDataItem extends DataItem {
    protected int mData;
    protected int mNewData;

    public void setNewData(int data) {
        mNewData = data;
    }

    public int getCurrentData() {
        return mNewData;
    }

    @Override
    public int toInt() {
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
    public void onUpdateData() {
        mData = mNewData;
    }
}
