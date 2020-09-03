package com.coder.zzq.smartshow.dialog.base;

import java.util.UUID;

public class SmartDialog<PROVIDER_TYPE extends SmartDialogProvider> {
    protected String mIdentity;
    protected PROVIDER_TYPE mDialogProvider;

    public SmartDialog() {
        mIdentity = UUID.randomUUID().toString();
    }

    protected String getIdentity() {
        return mIdentity;
    }


    protected PROVIDER_TYPE getDialogProvider() {
        return mDialogProvider;
    }

    protected SmartDialog<PROVIDER_TYPE> setDialogProvider(PROVIDER_TYPE dialogProvider) {
        mDialogProvider = dialogProvider;
        return this;
    }


    public void show() {
        getDialogProvider().show();
    }


    public void dismiss() {
        getDialogProvider().dismiss();
    }

    public boolean isShowing() {
        return getDialogProvider().isShowing();
    }
}
