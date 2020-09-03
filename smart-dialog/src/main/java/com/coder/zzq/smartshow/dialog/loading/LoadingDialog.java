package com.coder.zzq.smartshow.dialog.loading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.smartshow.dialog.base.DialogHostFragment;
import com.coder.zzq.smartshow.dialog.base.SmartBuildParams;
import com.coder.zzq.smartshow.dialog.base.SmartDialog;
import com.coder.zzq.smartshow.dialog.coordinator.SmartDialogCoordinator;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.data_item.IntDataItem;
import com.coder.zzq.smartshow.dialog.data_item.StringDataItem;

public class LoadingDialog extends SmartDialog<LoadingDialogProvider> {
  public static LoadingDialog create(AppCompatActivity lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getSupportFragmentManager(),businessTag);
  }

  public static LoadingDialog create(Fragment lifecycleBinder, String businessTag) {
    return create(lifecycleBinder.getChildFragmentManager(),businessTag);
  }

  private static LoadingDialog create(FragmentManager lifecycleBinder, String businessTag) {
    DialogHostFragment fragment = (DialogHostFragment) lifecycleBinder.findFragmentByTag(businessTag);
    if (fragment == null) {
      LoadingDialog smartDialog = new LoadingDialog();
      smartDialog.setDialogProvider(new LoadingDialogProvider());
      smartDialog.getDialogProvider().setBuildParams(new BuildParams());
      smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider);
      SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog);
      DialogHostFragment.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity());
      return smartDialog;
    }
    return (LoadingDialog) SmartDialogCoordinator.retrieve(fragment.getIdentity());
  }

  public LoadingDialog resetWhenShowAgain(boolean resetWhenShowAgain) {
    mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain);
    return this;
  }

  public LoadingDialog boxSize(@LoadingDialogProvider.BoxSizeMode int boxSize) {
    mDialogProvider.getBuildParams().boxSize(boxSize);
    return this;
  }

  public LoadingDialog message(String message) {
    mDialogProvider.getBuildParams().message(message);
    return this;
  }

  public static class BuildParams extends SmartBuildParams<BuildParams> {
    public static final String PARAM_BOX_SIZE = "boxSize";

    public static final String PARAM_MESSAGE = "message";

    protected IntDataItem mBoxSize;

    protected StringDataItem mMessage;

    public BuildParams() {
      super();
      mBoxSize = new IntDataItem();
      mBoxSize.setName(PARAM_BOX_SIZE);
      mBoxSize.setNewData(LoadingDialogProvider.BOX_SIZE_LARGE);
      mMessage = new StringDataItem();
      mMessage.setName(PARAM_MESSAGE);
      mMessage.setNewData("正在加载");
      mWindowBackground.setNewData(com.coder.zzq.smartshow.dialog.R.drawable.smart_show_loading_bg);
      mDarkBehindWhenShow.setNewData(false);
    }

    public BuildParams boxSize(@LoadingDialogProvider.BoxSizeMode int boxSize) {
      if(mBoxSize == null) {
        mBoxSize = new IntDataItem();
        mBoxSize.setName(BuildParams.PARAM_BOX_SIZE);
      }
      mBoxSize.setNewData(boxSize);
      return this;
    }

    @LoadingDialogProvider.BoxSizeMode
    public int getBoxSize() {
      return mBoxSize == null ? 0 : mBoxSize.getCurrentData();
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

    public void update() {
      super.update();
      if(mOnBuildParamChangedListener == null) {
        return;
      }
      if(mBoxSize != null && mBoxSize.isDataChanged()) {
        mBoxSize.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mBoxSize.getName());
      }
      if(mMessage != null && mMessage.isDataChanged()) {
        mMessage.updateData();
        mOnBuildParamChangedListener.onBuildParamChanged(mMessage.getName());
      }
    }

    public DataItem getData(String dataName) {
      super.getData(dataName);
      switch(dataName) {
        case PARAM_BOX_SIZE:return mBoxSize;
        case PARAM_MESSAGE:return mMessage;
        default:return null;
      }
    }

    public void reset() {
      super.reset();
      if(mBoxSize != null) {
        mBoxSize.reset();
      }
      if(mMessage != null) {
        mMessage.reset();
      }
    }
  }
}
