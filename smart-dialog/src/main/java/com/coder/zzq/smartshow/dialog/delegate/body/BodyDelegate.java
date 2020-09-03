package com.coder.zzq.smartshow.dialog.delegate.body;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.delegate.Delegate;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;

public interface BodyDelegate extends Delegate {
    @LayoutRes
    int provideBodyLayout();

    void initBody(AppCompatDialog dialog, ViewHolder viewHolder);
}
