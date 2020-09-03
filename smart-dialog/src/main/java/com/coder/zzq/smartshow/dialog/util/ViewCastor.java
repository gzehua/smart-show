package com.coder.zzq.smartshow.dialog.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewCastor {
    private View mView;

    public ViewCastor(View view) {
        mView = view;
    }


    public <T> T toView(Class<T> viewType) {
        return viewType.cast(mView);
    }

    public TextView toTextView() {
        return (TextView) mView;
    }

    public ImageView toImgView() {
        return (ImageView) mView;
    }

    public Button toButton(){
        return (Button) mView;
    }

    public View toView() {
        return mView;
    }

    public ViewGroup toViewGroup() {
        return (ViewGroup) mView;
    }

    public EditText toEditText() {
        return (EditText) mView;
    }

    public ListView toListView() {
        return (ListView) mView;
    }

    public <T> T toViewGroup(Class<T> viewGroupClass) {
        return (T) mView;
    }
}