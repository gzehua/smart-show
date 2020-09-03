package com.coder.zzq.smartshow.dialog.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.smartshow.dialog.base.DialogHostFragment;
import com.coder.zzq.smartshow.dialog.base.SmartBuildParams;
import com.coder.zzq.smartshow.dialog.base.SmartDialog;
import com.coder.zzq.smartshow.dialog.coordinator.SmartDialogCoordinator;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.data_item.IntDataItem;
import com.coder.zzq.smartshow.dialog.data_item.ObjectDataItem;
import com.coder.zzq.smartshow.dialog.data_item.StringDataItem;
import com.coder.zzq.smartshow.dialog.delegate.footer.OnDialogBtnClickedListener;
import com.coder.zzq.smartshow.dialog.util.TextStyle;

public class EnsureDialog extends SmartDialog<EnsureDialogProvider> {
  public static EnsureDialog create(AppCompatActivity lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getSupportFragmentManager(),businessTag);
  }

  public static EnsureDialog create(Fragment lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getChildFragmentManager(),businessTag);
  }

  private static EnsureDialog create(FragmentManager lifecycleBinder, String businessTag) {
    DialogHostFragment fragment = (DialogHostFragment) lifecycleBinder.findFragmentByTag(businessTag);
    if (fragment == null) {
      EnsureDialog smartDialog = new EnsureDialog();
      smartDialog.setDialogProvider(new EnsureDialogProvider());
      smartDialog.getDialogProvider().setBuildParams(new BuildParams());
      smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider);
      SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog);
      DialogHostFragment.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity());
      return smartDialog;
    }
    return (EnsureDialog) SmartDialogCoordinator.retrieve(fragment.getIdentity());
  }

  public EnsureDialog resetWhenShowAgain(boolean resetWhenShowAgain) {
    mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain);
    return this;
  }

  public EnsureDialog title(String title) {
    mDialogProvider.getBuildParams().title(title);
    return this;
  }

  public EnsureDialog titleStyle(TextStyle titleStyle) {
    mDialogProvider.getBuildParams().titleStyle(titleStyle);
    return this;
  }

  public EnsureDialog message(String message) {
    mDialogProvider.getBuildParams().message(message);
    return this;
  }

  public EnsureDialog messageStyle(TextStyle messageStyle) {
    mDialogProvider.getBuildParams().messageStyle(messageStyle);
    return this;
  }

  public EnsureDialog confirmBtnLabel(String confirmBtnLabel) {
    mDialogProvider.getBuildParams().confirmBtnLabel(confirmBtnLabel);
    return this;
  }

  public EnsureDialog confirmLabelStyle(TextStyle confirmLabelStyle) {
    mDialogProvider.getBuildParams().confirmLabelStyle(confirmLabelStyle);
    return this;
  }

  public EnsureDialog delayToConfirm(int delayToConfirm) {
    mDialogProvider.getBuildParams().delayToConfirm(delayToConfirm);
    return this;
  }

  public EnsureDialog onConfirmBtnClickedListener(
      OnDialogBtnClickedListener onConfirmBtnClickedListener) {
    mDialogProvider.getBuildParams().onConfirmBtnClickedListener(onConfirmBtnClickedListener);
    return this;
  }

  public EnsureDialog cancelBtnLabel(String cancelBtnLabel) {
    mDialogProvider.getBuildParams().cancelBtnLabel(cancelBtnLabel);
    return this;
  }

  public EnsureDialog cancelBtnLabelStyle(TextStyle cancelBtnLabelStyle) {
    mDialogProvider.getBuildParams().cancelBtnLabelStyle(cancelBtnLabelStyle);
    return this;
  }

  public EnsureDialog onCancelBtnClickedListener(
      OnDialogBtnClickedListener onCancelBtnClickedListener) {
    mDialogProvider.getBuildParams().onCancelBtnClickedListener(onCancelBtnClickedListener);
    return this;
  }

  public static class BuildParams extends SmartBuildParams<BuildParams> {
    public static final String PARAM_TITLE = "title";

    public static final String PARAM_TITLE_STYLE = "titleStyle";

    public static final String PARAM_MESSAGE = "message";

    public static final String PARAM_MESSAGE_STYLE = "messageStyle";

    public static final String PARAM_CONFIRM_BTN_LABEL = "confirmBtnLabel";

    public static final String PARAM_CONFIRM_LABEL_STYLE = "confirmLabelStyle";

    public static final String PARAM_DELAY_TO_CONFIRM = "delayToConfirm";

    public static final String PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER = "onConfirmBtnClickedListener";

    public static final String PARAM_CANCEL_BTN_LABEL = "cancelBtnLabel";

    public static final String PARAM_CANCEL_BTN_LABEL_STYLE = "cancelBtnLabelStyle";

    public static final String PARAM_ON_CANCEL_BTN_CLICKED_LISTENER = "onCancelBtnClickedListener";

    protected StringDataItem mTitle;

    protected ObjectDataItem<TextStyle> mTitleStyle;

    protected StringDataItem mMessage;

    protected ObjectDataItem<TextStyle> mMessageStyle;

    protected StringDataItem mConfirmBtnLabel;

    protected ObjectDataItem<TextStyle> mConfirmLabelStyle;

    protected IntDataItem mDelayToConfirm;

    protected ObjectDataItem<OnDialogBtnClickedListener> mOnConfirmBtnClickedListener;

    protected StringDataItem mCancelBtnLabel;

    protected ObjectDataItem<TextStyle> mCancelBtnLabelStyle;

    protected ObjectDataItem<OnDialogBtnClickedListener> mOnCancelBtnClickedListener;

    public BuildParams() {
      super();
      mMessage = new StringDataItem();
      mMessage.setName(PARAM_MESSAGE);
      mMessage.setNewData("一条通知");
      mDelayToConfirm = new IntDataItem();
      mDelayToConfirm.setName(PARAM_DELAY_TO_CONFIRM);
      mDelayToConfirm.setNewData(0);
    }

    public BuildParams title(String title) {
      if(mTitle == null) {
        mTitle = new StringDataItem();
        mTitle.setName(BuildParams.PARAM_TITLE);
      }
      mTitle.setNewData(title);
      return this;
    }

    public String getTitle() {
      return mTitle == null ? "" : mTitle.getCurrentData();
    }

    public BuildParams titleStyle(TextStyle titleStyle) {
      if(mTitleStyle == null) {
        mTitleStyle = new ObjectDataItem();
        mTitleStyle.setName(BuildParams.PARAM_TITLE_STYLE);
      }
      mTitleStyle.setNewData(titleStyle);
      return this;
    }

    public TextStyle getTitleStyle() {
      return mTitleStyle == null ? null : mTitleStyle.getCurrentData();
    }

    public BuildParams message(String message) {
      if(mMessage == null) {
        mMessage = new StringDataItem();
        mMessage.setName(BuildParams.PARAM_MESSAGE);
      }
      mMessage.setNewData(message);
      return this;
    }

    public String getMessage() {
      return mMessage == null ? "" : mMessage.getCurrentData();
    }

    public BuildParams messageStyle(TextStyle messageStyle) {
      if(mMessageStyle == null) {
        mMessageStyle = new ObjectDataItem();
        mMessageStyle.setName(BuildParams.PARAM_MESSAGE_STYLE);
      }
      mMessageStyle.setNewData(messageStyle);
      return this;
    }

    public TextStyle getMessageStyle() {
      return mMessageStyle == null ? null : mMessageStyle.getCurrentData();
    }

    public BuildParams confirmBtnLabel(String confirmBtnLabel) {
      if(mConfirmBtnLabel == null) {
        mConfirmBtnLabel = new StringDataItem();
        mConfirmBtnLabel.setName(BuildParams.PARAM_CONFIRM_BTN_LABEL);
      }
      mConfirmBtnLabel.setNewData(confirmBtnLabel);
      return this;
    }

    public String getConfirmBtnLabel() {
      return mConfirmBtnLabel == null ? "" : mConfirmBtnLabel.getCurrentData();
    }

    public BuildParams confirmLabelStyle(TextStyle confirmLabelStyle) {
      if(mConfirmLabelStyle == null) {
        mConfirmLabelStyle = new ObjectDataItem();
        mConfirmLabelStyle.setName(BuildParams.PARAM_CONFIRM_LABEL_STYLE);
      }
      mConfirmLabelStyle.setNewData(confirmLabelStyle);
      return this;
    }

    public TextStyle getConfirmLabelStyle() {
      return mConfirmLabelStyle == null ? null : mConfirmLabelStyle.getCurrentData();
    }

    public BuildParams delayToConfirm(int delayToConfirm) {
      if(mDelayToConfirm == null) {
        mDelayToConfirm = new IntDataItem();
        mDelayToConfirm.setName(BuildParams.PARAM_DELAY_TO_CONFIRM);
      }
      mDelayToConfirm.setNewData(delayToConfirm);
      return this;
    }

    public int getDelayToConfirm() {
      return mDelayToConfirm == null ? 0 : mDelayToConfirm.getCurrentData();
    }

    public BuildParams onConfirmBtnClickedListener(
        OnDialogBtnClickedListener onConfirmBtnClickedListener) {
      if(mOnConfirmBtnClickedListener == null) {
        mOnConfirmBtnClickedListener = new ObjectDataItem();
        mOnConfirmBtnClickedListener.setName(BuildParams.PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER);
      }
      mOnConfirmBtnClickedListener.setNewData(onConfirmBtnClickedListener);
      return this;
    }

    public OnDialogBtnClickedListener getOnConfirmBtnClickedListener() {
      return mOnConfirmBtnClickedListener == null ? null : mOnConfirmBtnClickedListener.getCurrentData();
    }

    public BuildParams cancelBtnLabel(String cancelBtnLabel) {
      if(mCancelBtnLabel == null) {
        mCancelBtnLabel = new StringDataItem();
        mCancelBtnLabel.setName(BuildParams.PARAM_CANCEL_BTN_LABEL);
      }
      mCancelBtnLabel.setNewData(cancelBtnLabel);
      return this;
    }

    public String getCancelBtnLabel() {
      return mCancelBtnLabel == null ? "" : mCancelBtnLabel.getCurrentData();
    }

    public BuildParams cancelBtnLabelStyle(TextStyle cancelBtnLabelStyle) {
      if(mCancelBtnLabelStyle == null) {
        mCancelBtnLabelStyle = new ObjectDataItem();
        mCancelBtnLabelStyle.setName(BuildParams.PARAM_CANCEL_BTN_LABEL_STYLE);
      }
      mCancelBtnLabelStyle.setNewData(cancelBtnLabelStyle);
      return this;
    }

    public TextStyle getCancelBtnLabelStyle() {
      return mCancelBtnLabelStyle == null ? null : mCancelBtnLabelStyle.getCurrentData();
    }

    public BuildParams onCancelBtnClickedListener(
        OnDialogBtnClickedListener onCancelBtnClickedListener) {
      if(mOnCancelBtnClickedListener == null) {
        mOnCancelBtnClickedListener = new ObjectDataItem();
        mOnCancelBtnClickedListener.setName(BuildParams.PARAM_ON_CANCEL_BTN_CLICKED_LISTENER);
      }
      mOnCancelBtnClickedListener.setNewData(onCancelBtnClickedListener);
      return this;
    }

    public OnDialogBtnClickedListener getOnCancelBtnClickedListener() {
      return mOnCancelBtnClickedListener == null ? null : mOnCancelBtnClickedListener.getCurrentData();
    }

    public void update() {
      super.update();
      if(mOnBuildParamChangedListener == null) {
        return;
      }
      if(mTitle != null && mTitle.isDataChanged()) {
        mTitle.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mTitle.getName());
      }
      if(mTitleStyle != null && mTitleStyle.isDataChanged()) {
        mTitleStyle.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mTitleStyle.getName());
      }
      if(mMessage != null && mMessage.isDataChanged()) {
        mMessage.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mMessage.getName());
      }
      if(mMessageStyle != null && mMessageStyle.isDataChanged()) {
        mMessageStyle.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mMessageStyle.getName());
      }
      if(mConfirmBtnLabel != null && mConfirmBtnLabel.isDataChanged()) {
        mConfirmBtnLabel.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mConfirmBtnLabel.getName());
      }
      if(mConfirmLabelStyle != null && mConfirmLabelStyle.isDataChanged()) {
        mConfirmLabelStyle.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mConfirmLabelStyle.getName());
      }
      if(mDelayToConfirm != null && mDelayToConfirm.isDataChanged()) {
        mDelayToConfirm.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mDelayToConfirm.getName());
      }
      if(mOnConfirmBtnClickedListener != null && mOnConfirmBtnClickedListener.isDataChanged()) {
        mOnConfirmBtnClickedListener.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mOnConfirmBtnClickedListener.getName());
      }
      if(mCancelBtnLabel != null && mCancelBtnLabel.isDataChanged()) {
        mCancelBtnLabel.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mCancelBtnLabel.getName());
      }
      if(mCancelBtnLabelStyle != null && mCancelBtnLabelStyle.isDataChanged()) {
        mCancelBtnLabelStyle.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mCancelBtnLabelStyle.getName());
      }
      if(mOnCancelBtnClickedListener != null && mOnCancelBtnClickedListener.isDataChanged()) {
        mOnCancelBtnClickedListener.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mOnCancelBtnClickedListener.getName());
      }
    }

    public DataItem getData(String dataName) {
      super.getData(dataName);
      switch(dataName) {
        case PARAM_TITLE:return mTitle;
        case PARAM_TITLE_STYLE:return mTitleStyle;
        case PARAM_MESSAGE:return mMessage;
        case PARAM_MESSAGE_STYLE:return mMessageStyle;
        case PARAM_CONFIRM_BTN_LABEL:return mConfirmBtnLabel;
        case PARAM_CONFIRM_LABEL_STYLE:return mConfirmLabelStyle;
        case PARAM_DELAY_TO_CONFIRM:return mDelayToConfirm;
        case PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER:return mOnConfirmBtnClickedListener;
        case PARAM_CANCEL_BTN_LABEL:return mCancelBtnLabel;
        case PARAM_CANCEL_BTN_LABEL_STYLE:return mCancelBtnLabelStyle;
        case PARAM_ON_CANCEL_BTN_CLICKED_LISTENER:return mOnCancelBtnClickedListener;
        default:return null;
      }
    }

    public void reset() {
      super.reset();
      if(mTitle != null) {
        mTitle.reset();
      }
      if(mTitleStyle != null) {
        mTitleStyle.reset();
      }
      if(mMessage != null) {
        mMessage.reset();
      }
      if(mMessageStyle != null) {
        mMessageStyle.reset();
      }
      if(mConfirmBtnLabel != null) {
        mConfirmBtnLabel.reset();
      }
      if(mConfirmLabelStyle != null) {
        mConfirmLabelStyle.reset();
      }
      if(mDelayToConfirm != null) {
        mDelayToConfirm.reset();
      }
      if(mOnConfirmBtnClickedListener != null) {
        mOnConfirmBtnClickedListener.reset();
      }
      if(mCancelBtnLabel != null) {
        mCancelBtnLabel.reset();
      }
      if(mCancelBtnLabelStyle != null) {
        mCancelBtnLabelStyle.reset();
      }
      if(mOnCancelBtnClickedListener != null) {
        mOnCancelBtnClickedListener.reset();
      }
    }
  }
}
