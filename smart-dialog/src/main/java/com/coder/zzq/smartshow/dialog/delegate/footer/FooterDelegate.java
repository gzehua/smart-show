package com.coder.zzq.smartshow.dialog.delegate.footer;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.delegate.Delegate;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.coder.zzq.smartshow.dialog.base.ISmartDialog;

public interface FooterDelegate extends Delegate {
    @LayoutRes
    int provideFooterLayout();

    void initFooter(AppCompatDialog nestedDialog, ISmartDialog smartDialog, ViewHolder viewHolder);
}
