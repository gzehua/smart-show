package com.coder.zzq.smartshow.dialog.data_item;

public class LongDataItem extends DataItem {
    private long mData;
    private long mNewData;

    public void setNewData(long data) {
        mNewData = data;
    }

    public long getCurrentData() {
        return mNewData;
    }

    @Override
    public long toLong() {
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
