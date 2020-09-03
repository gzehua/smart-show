package com.coder.zzq.smartshow.dialog.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import com.coder.zzq.toolkit.Utils;
import com.coder.zzq.toolkit.log.EasyLogger;

public class ViewHolder {
    private ViewCastor mRootView;
    private SparseArray<ViewCastor> mIdToView;


    public ViewHolder(View rootView) {
        mRootView = new ViewCastor(rootView);
        mIdToView = new SparseArray<>();
    }

    private ViewCastor findView(@IdRes int viewId) {
        ViewCastor viewCastor = mIdToView.get(viewId);
        if (viewCastor == null) {
            viewCastor = new ViewCastor(mRootView.toView().findViewById(viewId));
            mIdToView.append(viewId, viewCastor);
        }
        return viewCastor;
    }


    public void setText(@IdRes int viewId, CharSequence text) {
        findView(viewId).toTextView().setText(text);
    }

    public void setText(@IdRes int viewId, @StringRes int text) {
        setText(viewId, Utils.getStringFromRes(text));
    }


    public void setImg(@IdRes int viewId, Drawable drawable) {
        findView(viewId).toImgView().setImageDrawable(drawable);
    }

    public void setImg(@IdRes int viewId, @DrawableRes int drawableRes) {
        findView(viewId).toImgView().setImageResource(drawableRes);
    }

    public void setImg(@IdRes int viewId, Bitmap bitmap) {
        findView(viewId).toImgView().setImageBitmap(bitmap);
    }

    public void setOnClickListener(@IdRes int viewId, View.OnClickListener onClickListener) {
        getView(viewId).toView().setOnClickListener(onClickListener);
    }

    public void addOnAttachStateChangeListener(@IdRes int viewId, View.OnAttachStateChangeListener listener) {
        getView(viewId).toView().addOnAttachStateChangeListener(listener);
    }

    public void setTextColorRes(int viewId, @ColorRes int colorRes) {
        getView(viewId).toTextView().setTextColor(Utils.getColorFromRes(colorRes));
    }

    public void setTextColor(int viewId, @ColorInt int colorValue) {
        getView(viewId).toTextView().setTextColor(colorValue);
    }

    public ViewCastor getView(@IdRes int idRes) {
        return findView(idRes);
    }

    public ViewCastor getRootView() {
        return mRootView;
    }

    public void destroy() {
        mRootView = null;
        mIdToView.clear();
        EasyLogger.d(String.format("the ViewHolder#" + Utils.getObjectDesc(this) + "# is destroyed"));
    }

    public TextView getTextView(int viewId) {
        return getView(viewId).toTextView();
    }

    public void setVisibility(int viewId, int visibility) {
        getView(viewId).toView().setVisibility(visibility);
    }

    public String getText(int viewId) {
        return getTextView(viewId).getText().toString();
    }

    public int getTextColor(int viewId) {
        return getTextView(viewId).getCurrentTextColor();
    }

    public void setInputType(int viewId, int type) {
        getEditText(viewId).setInputType(type);
    }

    public EditText getEditText(int viewId) {
        return getView(viewId).toEditText();
    }

    public void setHint(int viewId, String hint) {
        getEditText(viewId).setHint(hint);
    }

    public void setSelection(int viewId, int selection) {
        getEditText(viewId).setSelection(selection);
    }

    public ListView getListView(int viewId) {
        return getView(viewId).toListView();
    }

    public ProgressBar getProgressBar(int viewId) {
        return getView(viewId).toView(ProgressBar.class);
    }

    public CheckBox getCheckBox(int viewId) {
        return getView(viewId).toView(CheckBox.class);
    }

    public RadioButton getRadioButton(int viewId) {
        return getView(viewId).toView(RadioButton.class);
    }

    public boolean exists(@IdRes int viewId) {
        return getView(viewId).toView() != null;
    }
}