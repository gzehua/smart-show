package com.coder.zzq.smartshow.dialog.base;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.coder.zzq.toolkit.Utils;

public abstract class NormalDialog<BuildParamsType extends SmartBuildParams> extends SmartDialogProvider<BuildParamsType> {
    public NormalDialog() {
        super();
    }


    @Override
    protected void createNestedDialog(Activity activity, int screenOrientation) {
        mNestedDialog = new AppCompatDialog(activity, provideDialogStyle());
        mViewHolder = new ViewHolder(Utils.inflate(provideContentLayout(), null));
        initView();
        ViewGroup.MarginLayoutParams rootLp = new ViewGroup.MarginLayoutParams(provideDialogWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mNestedDialog.setContentView(mViewHolder.getRootView().toView(), rootLp);
    }


    protected int provideDialogStyle() {
        return R.style.smart_show_dialog;
    }

    protected void initView() {

    }

    protected int provideDialogWidth() {
        return Math.min(Utils.screenWidth() - Utils.dpToPx(75), Utils.dpToPx(285));
    }

    @LayoutRes
    protected abstract int provideContentLayout();

}
