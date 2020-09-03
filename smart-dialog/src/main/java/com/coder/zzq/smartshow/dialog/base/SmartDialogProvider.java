package com.coder.zzq.smartshow.dialog.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.coordinator.SmartDialogCoordinator;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.coder.zzq.toolkit.Utils;

public abstract class SmartDialogProvider<BuildParamsType extends SmartBuildParams> implements ISmartDialog, SmartBuildParams.OnBuildParamChangedListener, DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";

    protected BuildParamsType mBuildParams;
    protected ViewHolder mViewHolder;
    protected AppCompatDialog mNestedDialog;
    protected boolean mHasShown;
    protected HostState mHostState;
    protected Bundle mDialogState;

    protected SmartDialogProvider() {
        mHostState = new HostState();
    }

    public void setBuildParams(BuildParamsType buildParams) {
        mBuildParams = buildParams;
    }

    public BuildParamsType getBuildParams() {
        return mBuildParams;
    }

    public AppCompatDialog getNestedDialog() {
        return mNestedDialog;
    }

    public void buildNestedDialog(Activity activity, Bundle savedInstanceState) {
        if (mNestedDialog == null || mNestedDialog.getOwnerActivity() != activity) {
            createNestedDialog(activity, Utils.screenOrientation());
            mNestedDialog.setOwnerActivity(activity);
            mNestedDialog.setOnDismissListener(this);
            if (savedInstanceState != null) {
                mDialogState = savedInstanceState.getBundle(SAVED_DIALOG_STATE_TAG);
            }
        }
    }


    protected abstract void createNestedDialog(Activity activity, int screenOrientation);

    public void callbackBeforeEveryShow() {
        if (mHasShown && mBuildParams.isResetWhenShowAgain()) {
            onResetDialogWhenShowAgain();
        }
    }

    public void onResetDialogWhenShowAgain() {

    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            onScreenPortrait();
        } else {
            onScreenLandscape();
        }
    }

    protected void onScreenLandscape() {

    }

    protected void onScreenPortrait() {
    }


    @Override
    public void onBuildParamChanged(String paramName) {
        switch (paramName) {
            case BuildParamsType.PARAM_DARK_BEHIND_WHEN_SHOW:
                if (mBuildParams.isDarkBehindWhenShow()) {
                    mNestedDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                } else {
                    mNestedDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                }
                break;
            case BuildParamsType.PARAM_WINDOW_BACKGROUND:
                mNestedDialog.getWindow().setBackgroundDrawableResource(mBuildParams.getWindowBackground());
                break;
            case BuildParamsType.PARAM_CANCELABLE:
                mNestedDialog.setCancelable(mBuildParams.isCancelable());
                if (!mBuildParams.isCancelable()) {
                    mNestedDialog.setCanceledOnTouchOutside(false);
                }
                break;
            case BuildParamsType.PARAM_CANCELABLE_ON_TOUCH_OUTSIDE:
                mNestedDialog.setCanceledOnTouchOutside(mBuildParams.isCancelable() && mBuildParams.isCancelableOnTouchOutside());
                break;
            case BuildParamsType.PARAM_ON_DIALOG_SHOW_LISTENER:
                mNestedDialog.setOnShowListener(mBuildParams.getOnDialogShowListener());
                break;
            case BuildParamsType.PARAM_ON_DIALOG_CANCEL_LISTENER:
                mNestedDialog.setOnCancelListener(mBuildParams.getOnDialogCancelListener());
                break;
        }
    }

    @Override
    public void show() {
        mHostState.setDialogDismissed(false);
        mHostState.setDismissNormally(true);
        if (mHostState.isHostStarted()) {
            showNestedDialog();
        }
    }

    @Override
    public void dismiss() {
        mHostState.setDialogDismissed(true);
        mHostState.setDismissNormally(true);
        dismissNestedDialog();
    }

    @Override
    public boolean isShowing() {
        return !mHostState.isDialogDismissed() && isNestedDialogShowing();
    }


    private boolean isNestedDialogShowing() {
        return mNestedDialog != null && mNestedDialog.isShowing();
    }

    private void showNestedDialog() {
        mBuildParams.update();
        callbackBeforeEveryShow();
        if (mDialogState != null) {
            mNestedDialog.onRestoreInstanceState(mDialogState);
            mDialogState = null;
        }
        mNestedDialog.show();
        mHasShown = true;
    }

    private void hideNestedDialog() {
        if (mNestedDialog != null) {
            mNestedDialog.hide();
        }
    }

    private void dismissNestedDialog() {
        if (mNestedDialog != null) {
            mNestedDialog.dismiss();
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        mHostState.setDialogDismissed(true);
        if (mBuildParams.getOnDialogDismissListener() != null) {
            mBuildParams.getOnDialogDismissListener().onDismiss(mNestedDialog);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mHostState.setDismissNormally(true);
        if (getBuildParams().getOnDialogCancelListener() != null) {
            getBuildParams().getOnDialogCancelListener().onCancel(mNestedDialog);
        }
    }


    public void onHostSaveInstanceState(Bundle outState) {
        Bundle dialogState = mNestedDialog.onSaveInstanceState();
        outState.putBundle(SAVED_DIALOG_STATE_TAG, dialogState);
        mHostState.setHostSaveInstanceCalled(true);
    }


    public void onHostStart() {
        if (mHostState.isDialogDismissed() && mHostState.isDismissNormally()) {
            dismissNestedDialog();
        } else {
            showNestedDialog();
        }
        mHostState.setHostStarted(true);
    }

    public void onHostStop() {
        mHostState.setHostStarted(false);
        mHostState.setDismissNormally(!mNestedDialog.isShowing());
        hideNestedDialog();
    }

    public void onHostDestroyView() {
        if (mNestedDialog != null) {
            mNestedDialog.dismiss();
        }
    }

    public boolean onHostDestroyed(String identity) {
        mNestedDialog = null;
        if (mViewHolder != null) {
            mViewHolder.destroy();
            mViewHolder = null;
        }
        if (!mHostState.isHostSaveInstanceCalled()) {
            SmartDialogCoordinator.delete(identity);
            mBuildParams.onDestroyed();
            mBuildParams = null;
            mHostState = null;
            return true;
        } else {
            mBuildParams.reset();
        }
        mHostState.setHostSaveInstanceCalled(false);
        return false;
    }


}
