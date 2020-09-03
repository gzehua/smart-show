package com.coder.zzq.smartshow.dialog.data_item;

public class DoubleDataItem extends DataItem {
    protected double mData;
    protected double mNewData;

    public double getCurrentData() {
        return mNewData;
    }

    public void setNewData(double data) {
        mNewData = data;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    protected boolean judgeDataChanged() {
        return Double.compare(mData, mNewData) != 0;
    }

    @Override
    public void onUpdateData() {
        mData = mNewData;
    }

    @Override
    public double toDouble() {
        return mNewData;
    }
}
