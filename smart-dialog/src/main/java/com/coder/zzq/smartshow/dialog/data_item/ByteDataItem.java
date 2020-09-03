package com.coder.zzq.smartshow.dialog.data_item;

public class ByteDataItem extends DataItem {
    protected byte mData;
    protected byte mNewData;

    public void setNewData(byte data) {
        mNewData = data;
    }

    public byte getCurrentData() {
        return mNewData;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public byte toByte() {
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
