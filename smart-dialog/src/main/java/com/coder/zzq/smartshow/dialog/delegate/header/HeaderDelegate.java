package com.coder.zzq.smartshow.dialog.delegate.header;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.delegate.Delegate;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;

public interface HeaderDelegate extends Delegate {
    @LayoutRes
    int provideHeaderLayout();

    void initHeader(AppCompatDialog dialog, ViewHolder viewHolder);
}
