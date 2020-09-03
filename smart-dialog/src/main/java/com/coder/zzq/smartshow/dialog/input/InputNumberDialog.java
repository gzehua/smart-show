package com.coder.zzq.smartshow.dialog.input;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.smartshow.dialog.base.DialogHostFragment;
import com.coder.zzq.smartshow.dialog.base.SmartBuildParams;
import com.coder.zzq.smartshow.dialog.base.SmartDialog;
import com.coder.zzq.smartshow.dialog.coordinator.SmartDialogCoordinator;
import com.coder.zzq.smartshow.dialog.data_item.BoolDataItem;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.data_item.IntDataItem;
import com.coder.zzq.smartshow.dialog.data_item.ObjectDataItem;
import com.coder.zzq.smartshow.dialog.data_item.StringDataItem;
import com.coder.zzq.smartshow.dialog.delegate.footer.OnDialogBtnClickedListener;
import com.coder.zzq.smartshow.dialog.util.TextStyle;

public class InputNumberDialog extends SmartDialog<InputNumberDialogProvider> {
  public static InputNumberDialog create(AppCompatActivity lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getSupportFragmentManager(),businessTag);
  }

  public static InputNumberDialog create(Fragment lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getChildFragmentManager(),businessTag);
  }

  private static InputNumberDialog create(FragmentManager lifecycleBinder, String businessTag) {
    DialogHostFragment fragment = (DialogHostFragment) lifecycleBinder.findFragmentByTag(businessTag);
    if (fragment == null) {
      InputNumberDialog smartDialog = new InputNumberDialog();
      smartDialog.setDialogProvider(new InputNumberDialogProvider());
      smartDialog.getDialogProvider().setBuildParams(new BuildParams());
      smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider);
      SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog);
      DialogHostFragment.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity());
      return smartDialog;
    }
    return (InputNumberDialog) SmartDialogCoordinator.retrieve(fragment.getIdentity());
  }

  public InputNumberDialog resetWhenShowAgain(boolean resetWhenShowAgain) {
    mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain);
    return this;
  }

  public InputNumberDialog title(String title) {
    mDialogProvider.getBuildParams().title(title);
    return this;
  }

  public InputNumberDialog titleStyle(TextStyle titleStyle) {
    mDialogProvider.getBuildParams().titleStyle(titleStyle);
    return this;
  }

  public InputNumberDialog initialFilledNumber(String initialFilledNumber) {
    mDialogProvider.getBuildParams().initialFilledNumber(initialFilledNumber);
    return this;
  }

  public InputNumberDialog numberUnit(String numberUnit) {
    mDialogProvider.getBuildParams().numberUnit(numberUnit);
    return this;
  }

  public InputNumberDialog numberType(@InputNumberDialogProvider.NumberType int numberType) {
    mDialogProvider.getBuildParams().numberType(numberType);
    return this;
  }

  public InputNumberDialog hasSigned(boolean hasSigned) {
    mDialogProvider.getBuildParams().hasSigned(hasSigned);
    return this;
  }

  public InputNumberDialog inputCheckCallback(InputCheckCallback inputCheckCallback) {
    mDialogProvider.getBuildParams().inputCheckCallback(inputCheckCallback);
    return this;
  }

  public InputNumberDialog confirmBtnLabel(String confirmBtnLabel) {
    mDialogProvider.getBuildParams().confirmBtnLabel(confirmBtnLabel);
    return this;
  }

  public InputNumberDialog confirmLabelStyle(TextStyle confirmLabelStyle) {
    mDialogProvider.getBuildParams().confirmLabelStyle(confirmLabelStyle);
    return this;
  }

  public InputNumberDialog delayToConfirm(int delayToConfirm) {
    mDialogProvider.getBuildParams().delayToConfirm(delayToConfirm);
    return this;
  }

  public InputNumberDialog onConfirmBtnClickedListener(
      OnDialogBtnClickedListener onConfirmBtnClickedListener) {
    mDialogProvider.getBuildParams().onConfirmBtnClickedListener(onConfirmBtnClickedListener);
    return this;
  }

  public InputNumberDialog cancelBtnLabel(String cancelBtnLabel) {
    mDialogProvider.getBuildParams().cancelBtnLabel(cancelBtnLabel);
    return this;
  }

  public InputNumberDialog cancelBtnLabelStyle(TextStyle cancelBtnLabelStyle) {
    mDialogProvider.getBuildParams().cancelBtnLabelStyle(cancelBtnLabelStyle);
    return this;
  }

  public InputNumberDialog onCancelBtnClickedListener(
      OnDialogBtnClickedListener onCancelBtnClickedListener) {
    mDialogProvider.getBuildParams().onCancelBtnClickedListener(onCancelBtnClickedListener);
    return this;
  }

  public static class BuildParams extends SmartBuildParams<BuildParams> {
    public static final String PARAM_TITLE = "title";

    public static final String PARAM_TITLE_STYLE = "titleStyle";

    public static final String PARAM_INITIAL_FILLED_NUMBER = "initialFilledNumber";

    public static final String PARAM_NUMBER_UNIT = "numberUnit";

    public static final String PARAM_NUMBER_TYPE = "numberType";

    public static final String PARAM_HAS_SIGNED = "hasSigned";

    public static final String PARAM_INPUT_CHECK_CALLBACK = "inputCheckCallback";

    public static final String PARAM_CONFIRM_BTN_LABEL = "confirmBtnLabel";

    public static final String PARAM_CONFIRM_LABEL_STYLE = "confirmLabelStyle";

    public static final String PARAM_DELAY_TO_CONFIRM = "delayToConfirm";

    public static final String PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER = "onConfirmBtnClickedListener";

    public static final String PARAM_CANCEL_BTN_LABEL = "cancelBtnLabel";

    public static final String PARAM_CANCEL_BTN_LABEL_STYLE = "cancelBtnLabelStyle";

    public static final String PARAM_ON_CANCEL_BTN_CLICKED_LISTENER = "onCancelBtnClickedListener";

    protected StringDataItem mTitle;

    protected ObjectDataItem<TextStyle> mTitleStyle;

    protected StringDataItem mInitialFilledNumber;

    protected StringDataItem mNumberUnit;

    protected IntDataItem mNumberType;

    protected BoolDataItem mHasSigned;

    protected ObjectDataItem<InputCheckCallback> mInputCheckCallback;

    protected StringDataItem mConfirmBtnLabel;

    protected ObjectDataItem<TextStyle> mConfirmLabelStyle;

    protected IntDataItem mDelayToConfirm;

    protected ObjectDataItem<OnDialogBtnClickedListener> mOnConfirmBtnClickedListener;

    protected StringDataItem mCancelBtnLabel;

    protected ObjectDataItem<TextStyle> mCancelBtnLabelStyle;

    protected ObjectDataItem<OnDialogBtnClickedListener> mOnCancelBtnClickedListener;

    public BuildParams() {
      super();
      mInitialFilledNumber = new StringDataItem();
      mInitialFilledNumber.setName(PARAM_INITIAL_FILLED_NUMBER);
      mInitialFilledNumber.setNewData("0");
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

    public BuildParams initialFilledNumber(String initialFilledNumber) {
      if(mInitialFilledNumber == null) {
        mInitialFilledNumber = new StringDataItem();
        mInitialFilledNumber.setName(BuildParams.PARAM_INITIAL_FILLED_NUMBER);
      }
      mInitialFilledNumber.setNewData(initialFilledNumber);
      return this;
    }

    public String getInitialFilledNumber() {
      return mInitialFilledNumber == null ? "" : mInitialFilledNumber.getCurrentData();
    }

    public BuildParams numberUnit(String numberUnit) {
      if(mNumberUnit == null) {
        mNumberUnit = new StringDataItem();
        mNumberUnit.setName(BuildParams.PARAM_NUMBER_UNIT);
      }
      mNumberUnit.setNewData(numberUnit);
      return this;
    }

    public String getNumberUnit() {
      return mNumberUnit == null ? "" : mNumberUnit.getCurrentData();
    }

    public BuildParams numberType(
        @InputNumberDialogProvider.NumberType int numberType) {
      if(mNumberType == null) {
        mNumberType = new IntDataItem();
        mNumberType.setName(BuildParams.PARAM_NUMBER_TYPE);
      }
      mNumberType.setNewData(numberType);
      return this;
    }

    @InputNumberDialogProvider.NumberType
    public int getNumberType() {
      return mNumberType == null ? 0 : mNumberType.getCurrentData();
    }

    public BuildParams hasSigned(boolean hasSigned) {
      if(mHasSigned == null) {
        mHasSigned = new BoolDataItem();
        mHasSigned.setName(BuildParams.PARAM_HAS_SIGNED);
      }
      mHasSigned.setNewData(hasSigned);
      return this;
    }

    public boolean isHasSigned() {
      return mHasSigned == null ? false : mHasSigned.getCurrentData();
    }

    public BuildParams inputCheckCallback(InputCheckCallback inputCheckCallback) {
      if(mInputCheckCallback == null) {
        mInputCheckCallback = new ObjectDataItem();
        mInputCheckCallback.setName(BuildParams.PARAM_INPUT_CHECK_CALLBACK);
      }
      mInputCheckCallback.setNewData(inputCheckCallback);
      return this;
    }

    public InputCheckCallback getInputCheckCallback() {
      return mInputCheckCallback == null ? null : mInputCheckCallback.getCurrentData();
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
      if(mInitialFilledNumber != null && mInitialFilledNumber.isDataChanged()) {
        mInitialFilledNumber.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mInitialFilledNumber.getName());
      }
      if(mNumberUnit != null && mNumberUnit.isDataChanged()) {
        mNumberUnit.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mNumberUnit.getName());
      }
      if(mNumberType != null && mNumberType.isDataChanged()) {
        mNumberType.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mNumberType.getName());
      }
      if(mHasSigned != null && mHasSigned.isDataChanged()) {
        mHasSigned.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mHasSigned.getName());
      }
      if(mInputCheckCallback != null && mInputCheckCallback.isDataChanged()) {
        mInputCheckCallback.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mInputCheckCallback.getName());
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
        case PARAM_INITIAL_FILLED_NUMBER:return mInitialFilledNumber;
        case PARAM_NUMBER_UNIT:return mNumberUnit;
        case PARAM_NUMBER_TYPE:return mNumberType;
        case PARAM_HAS_SIGNED:return mHasSigned;
        case PARAM_INPUT_CHECK_CALLBACK:return mInputCheckCallback;
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
      if(mInitialFilledNumber != null) {
        mInitialFilledNumber.reset();
      }
      if(mNumberUnit != null) {
        mNumberUnit.reset();
      }
      if(mNumberType != null) {
        mNumberType.reset();
      }
      if(mHasSigned != null) {
        mHasSigned.reset();
      }
      if(mInputCheckCallback != null) {
        mInputCheckCallback.reset();
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
