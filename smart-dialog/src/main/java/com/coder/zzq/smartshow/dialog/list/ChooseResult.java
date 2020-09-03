package com.coder.zzq.smartshow.dialog.list;

public class ChooseResult {
    private static final ChooseResult EMPTY_RESULT = new ChooseResult();
    private int[] mChoosePositions;
    private Object[] mChooseItems;


    public boolean isAnyItemChosen() {
        return mChoosePositions != null;
    }

    public int[] getChoosePositions() {
        return mChoosePositions;
    }

    public ChooseResult setChoosePositions(int[] choosePositions) {
        mChoosePositions = choosePositions;
        return this;
    }

    public Object[] getChooseItems() {
        return mChooseItems;
    }

    public ChooseResult setChooseItems(Object[] chooseItems) {
        mChooseItems = chooseItems;
        return this;
    }

    public static ChooseResult emptyResultInstance() {
        return EMPTY_RESULT;
    }
}
