package com.coder.zzq.smartshow.dialog.util;

import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

import com.coder.zzq.toolkit.Utils;

import java.util.Arrays;

public class TextStyle {
    @ColorInt
    private int mColor;
    private float mSize;
    private boolean mBold = false;


    private TextStyle() {

    }

    public static TextStyle create() {
        return new TextStyle();
    }

    public int getColor() {
        return mColor;
    }

    public TextStyle setColorInt(@ColorInt int colorValue) {
        mColor = colorValue;
        return this;
    }

    public TextStyle setColorRes(@ColorRes int colorRes) {
        mColor = Utils.getColorFromRes(colorRes);
        return this;
    }

    public float getSize() {
        return mSize;
    }

    public TextStyle setSize(float size) {
        mSize = size;
        return this;
    }

    public boolean isBold() {
        return mBold;
    }

    public TextStyle setBold(boolean bold) {
        mBold = bold;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextStyle textStyle = (TextStyle) o;
        return mColor == textStyle.mColor &&
                Float.compare(textStyle.mSize, mSize) == 0 &&
                mBold == textStyle.mBold;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{mColor, mSize, mColor});
    }

    public void apply(TextView textView) {
        if (textView == null) {
            return;
        }

        if (mColor != 0) {
            textView.setTextColor(mColor);
        }

        if (mSize != 0) {
            textView.setTextSize(mSize);
        }

        textView.getPaint().setFakeBoldText(mBold);
    }
}
