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

public class NotificationDialog extends SmartDialog<NotificationDialogProvider> {
  public static NotificationDialog create(AppCompatActivity lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getSupportFragmentManager(),businessTag);
  }

  public static NotificationDialog create(Fragment lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getChildFragmentManager(),businessTag);
  }

  private static NotificationDialog create(FragmentManager lifecycleBinder, String businessTag) {
    DialogHostFragment fragment = (DialogHostFragment) lifecycleBinder.findFragmentByTag(businessTag);
    if (fragment == null) {
      NotificationDialog smartDialog = new NotificationDialog();
      smartDialog.setDialogProvider(new NotificationDialogProvider());
      smartDialog.getDialogProvider().setBuildParams(new BuildParams());
      smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider);
      SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog);
      DialogHostFragment.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity());
      return smartDialog;
    }
    return (NotificationDialog) SmartDialogCoordinator.retrieve(fragment.getIdentity());
  }

  public NotificationDialog resetWhenShowAgain(boolean resetWhenShowAgain) {
    mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain);
    return this;
  }

  public NotificationDialog title(String title) {
    mDialogProvider.getBuildParams().title(title);
    return this;
  }

  public NotificationDialog titleStyle(TextStyle titleStyle) {
    mDialogProvider.getBuildParams().titleStyle(titleStyle);
    return this;
  }

  public NotificationDialog message(String message) {
    mDialogProvider.getBuildParams().message(message);
    return this;
  }

  public NotificationDialog messageStyle(TextStyle messageStyle) {
    mDialogProvider.getBuildParams().messageStyle(messageStyle);
    return this;
  }

  public NotificationDialog confirmBtnLabel(String confirmBtnLabel) {
    mDialogProvider.getBuildParams().confirmBtnLabel(confirmBtnLabel);
    return this;
  }

  public NotificationDialog confirmLabelStyle(TextStyle confirmLabelStyle) {
    mDialogProvider.getBuildParams().confirmLabelStyle(confirmLabelStyle);
    return this;
  }

  public NotificationDialog delayToConfirm(int delayToConfirm) {
    mDialogProvider.getBuildParams().delayToConfirm(delayToConfirm);
    return this;
  }

  public NotificationDialog onConfirmBtnClickedListener(
      OnDialogBtnClickedListener onConfirmBtnClickedListener) {
    mDialogProvider.getBuildParams().onConfirmBtnClickedListener(onConfirmBtnClickedListener);
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

    protected StringDataItem mTitle;

    protected ObjectDataItem<TextStyle> mTitleStyle;

    protected StringDataItem mMessage;

    protected ObjectDataItem<TextStyle> mMessageStyle;

    protected StringDataItem mConfirmBtnLabel;

    protected ObjectDataItem<TextStyle> mConfirmLabelStyle;

    protected IntDataItem mDelayToConfirm;

    protected ObjectDataItem<OnDialogBtnClickedListener> mOnConfirmBtnClickedListener;

    public BuildParams() {
      super();
      mMessage = new StringDataItem();
      mMessage.setName(PARAM_MESSAGE);
      mMessage.setNewData("一条通知");
      mConfirmBtnLabel = new StringDataItem();
      mConfirmBtnLabel.setName(PARAM_CONFIRM_BTN_LABEL);
      mConfirmBtnLabel.setNewData("确定");
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
    }
  }
}
