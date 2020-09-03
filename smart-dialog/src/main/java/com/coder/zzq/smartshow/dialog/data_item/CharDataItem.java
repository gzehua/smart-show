package com.coder.zzq.smartshow.dialog.data_item;

public class CharDataItem extends DataItem {
    private char mData;
    private char mNewData;


    public char getCurrentData() {
        return mNewData;
    }

    public void setNewData(char data) {
        mNewData = data;
    }


    @Override
    public void onUpdateData() {
        mData = mNewData;
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
    public char toChar() {
        return mNewData;
    }
}
