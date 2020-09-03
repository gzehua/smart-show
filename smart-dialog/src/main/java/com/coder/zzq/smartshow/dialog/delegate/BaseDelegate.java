package com.coder.zzq.smartshow.dialog.delegate;

import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;

public abstract class BaseDelegate implements Delegate {
    protected DelegateScaffoldDialog mDelegateDialog;

    public BaseDelegate(DelegateScaffoldDialog delegateDialog) {
        mDelegateDialog = delegateDialog;
    }

    @Override
    public void destroy() {
        mDelegateDialog = null;
    }
}
