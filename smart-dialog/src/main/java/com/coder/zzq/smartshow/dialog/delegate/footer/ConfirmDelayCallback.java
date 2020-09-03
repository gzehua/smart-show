package com.coder.zzq.smartshow.dialog.delegate.footer;

import android.view.View;

public interface ConfirmDelayCallback extends View.OnAttachStateChangeListener, Runnable {
    void reset();
}
