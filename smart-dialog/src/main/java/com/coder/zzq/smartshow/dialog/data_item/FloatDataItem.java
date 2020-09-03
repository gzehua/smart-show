package com.coder.zzq.smartshow.dialog.data_item;

public class FloatDataItem extends DataItem {
    protected float mData;
    protected float mNewData;

    public void setNewData(float data) {
        mNewData = data;
    }

    public float getCurrentData() {
        return mNewData;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }


    @Override
    protected boolean judgeDataChanged() {
        return Float.compare(mData, mNewData) != 0;
    }

    @Override
    public void onUpdateData() {
        mData = mNewData;
    }

    @Override
    public float toFloat() {
        return mNewData;
    }
}
