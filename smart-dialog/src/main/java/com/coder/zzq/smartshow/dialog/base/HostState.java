package com.coder.zzq.smartshow.dialog.base;

public class HostState {
    private boolean mDialogDismissed;
    private boolean mHostStarted;
    private boolean mHostSaveInstanceCalled;
    private boolean mDismissNormally;

    public boolean isDialogDismissed() {
        return mDialogDismissed;
    }

    public void setDialogDismissed(boolean dialogDismissed) {
        mDialogDismissed = dialogDismissed;
    }

    public boolean isHostStarted() {
        return mHostStarted;
    }

    public void setHostStarted(boolean hostStarted) {
        mHostStarted = hostStarted;
    }

    public boolean isHostSaveInstanceCalled() {
        return mHostSaveInstanceCalled;
    }

    public void setHostSaveInstanceCalled(boolean hostSaveInstanceCalled) {
        mHostSaveInstanceCalled = hostSaveInstanceCalled;
    }

    public boolean isDismissNormally() {
        return mDismissNormally;
    }

    public void setDismissNormally(boolean dismissNormally) {
        mDismissNormally = dismissNormally;
    }
}

