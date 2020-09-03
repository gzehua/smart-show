package com.coder.zzq.smartshow.dialog.list;

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

import java.util.Arrays;
import java.util.List;

public class ChooseListDialog extends SmartDialog<ChooseListDialogProvider> {
    public static ChooseListDialog create(AppCompatActivity lifecycleBinder, String businessTag) {
        return create(lifecycleBinder.getSupportFragmentManager(), businessTag);
    }

    public static ChooseListDialog create(Fragment lifecycleBinder, String businessTag) {
        return create(lifecycleBinder.getChildFragmentManager(), businessTag);
    }

    private static ChooseListDialog create(FragmentManager lifecycleBinder, String businessTag) {
        DialogHostFragment fragment = (DialogHostFragment) lifecycleBinder.findFragmentByTag(businessTag);
        if (fragment == null) {
            ChooseListDialog smartDialog = new ChooseListDialog();
            smartDialog.setDialogProvider(new ChooseListDialogProvider());
            smartDialog.getDialogProvider().setBuildParams(new BuildParams());
            smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider);
            SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog);
            DialogHostFragment.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity());
            return smartDialog;
        }
        return (ChooseListDialog) SmartDialogCoordinator.retrieve(fragment.getIdentity());
    }

    public ChooseListDialog resetWhenShowAgain(boolean resetWhenShowAgain) {
        mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain);
        return this;
    }

    public ChooseListDialog title(String title) {
        mDialogProvider.getBuildParams().title(title);
        return this;
    }

    public ChooseListDialog titleStyle(TextStyle titleStyle) {
        mDialogProvider.getBuildParams().titleStyle(titleStyle);
        return this;
    }

    public ChooseListDialog items(List items) {
        mDialogProvider.getBuildParams().items(items);
        return this;
    }

    public ChooseListDialog items(Object... items) {
        mDialogProvider.getBuildParams().items(Arrays.asList(items));
        return this;
    }

    public ChooseListDialog itemStyle(TextStyle itemStyle) {
        mDialogProvider.getBuildParams().itemStyle(itemStyle);
        return this;
    }

    public ChooseListDialog choiceMode(@ChooseListDialogProvider.ChoiceMode int choiceMode) {
        mDialogProvider.getBuildParams().choiceMode(choiceMode);
        return this;
    }

    public ChooseListDialog defaultChosenItems(int[] defaultChosenItems) {
        mDialogProvider.getBuildParams().defaultChosenItems(defaultChosenItems);
        return this;
    }

    public ChooseListDialog checkMarkPos(@ChooseListDialogProvider.CheckMarkPos int checkMarkPos) {
        mDialogProvider.getBuildParams().checkMarkPos(checkMarkPos);
        return this;
    }

    public ChooseListDialog checkMarkType(@ChooseListDialogProvider.CheckMarkType int checkMarkType) {
        mDialogProvider.getBuildParams().checkMarkType(checkMarkType);
        return this;
    }

    public ChooseListDialog confirmBtnLabel(String confirmBtnLabel) {
        mDialogProvider.getBuildParams().confirmBtnLabel(confirmBtnLabel);
        return this;
    }

    public ChooseListDialog confirmLabelStyle(TextStyle confirmLabelStyle) {
        mDialogProvider.getBuildParams().confirmLabelStyle(confirmLabelStyle);
        return this;
    }

    public ChooseListDialog delayToConfirm(int delayToConfirm) {
        mDialogProvider.getBuildParams().delayToConfirm(delayToConfirm);
        return this;
    }

    public ChooseListDialog onConfirmBtnClickedListener(
            OnDialogBtnClickedListener onConfirmBtnClickedListener) {
        mDialogProvider.getBuildParams().onConfirmBtnClickedListener(onConfirmBtnClickedListener);
        return this;
    }

    public ChooseListDialog cancelBtnLabel(String cancelBtnLabel) {
        mDialogProvider.getBuildParams().cancelBtnLabel(cancelBtnLabel);
        return this;
    }

    public ChooseListDialog cancelBtnLabelStyle(TextStyle cancelBtnLabelStyle) {
        mDialogProvider.getBuildParams().cancelBtnLabelStyle(cancelBtnLabelStyle);
        return this;
    }

    public ChooseListDialog onCancelBtnClickedListener(
            OnDialogBtnClickedListener onCancelBtnClickedListener) {
        mDialogProvider.getBuildParams().onCancelBtnClickedListener(onCancelBtnClickedListener);
        return this;
    }

    public static class BuildParams extends SmartBuildParams<BuildParams> {
        public static final String PARAM_TITLE = "title";

        public static final String PARAM_TITLE_STYLE = "titleStyle";

        public static final String PARAM_ITEMS = "items";

        public static final String PARAM_ITEM_STYLE = "itemStyle";

        public static final String PARAM_CHOICE_MODE = "choiceMode";

        public static final String PARAM_DEFAULT_CHOSEN_ITEMS = "defaultChosenItems";

        public static final String PARAM_CHECK_MARK_POS = "checkMarkPos";

        public static final String PARAM_CHECK_MARK_TYPE = "checkMarkType";

        public static final String PARAM_CONFIRM_BTN_LABEL = "confirmBtnLabel";

        public static final String PARAM_CONFIRM_LABEL_STYLE = "confirmLabelStyle";

        public static final String PARAM_DELAY_TO_CONFIRM = "delayToConfirm";

        public static final String PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER = "onConfirmBtnClickedListener";

        public static final String PARAM_CANCEL_BTN_LABEL = "cancelBtnLabel";

        public static final String PARAM_CANCEL_BTN_LABEL_STYLE = "cancelBtnLabelStyle";

        public static final String PARAM_ON_CANCEL_BTN_CLICKED_LISTENER = "onCancelBtnClickedListener";

        protected StringDataItem mTitle;

        protected ObjectDataItem<TextStyle> mTitleStyle;

        protected ObjectDataItem<List> mItems;

        protected ObjectDataItem<TextStyle> mItemStyle;

        protected IntDataItem mChoiceMode;

        protected ObjectDataItem<int[]> mDefaultChosenItems;

        protected IntDataItem mCheckMarkPos;

        protected IntDataItem mCheckMarkType;

        protected StringDataItem mConfirmBtnLabel;

        protected ObjectDataItem<TextStyle> mConfirmLabelStyle;

        protected IntDataItem mDelayToConfirm;

        protected ObjectDataItem<OnDialogBtnClickedListener> mOnConfirmBtnClickedListener;

        protected StringDataItem mCancelBtnLabel;

        protected ObjectDataItem<TextStyle> mCancelBtnLabelStyle;

        protected ObjectDataItem<OnDialogBtnClickedListener> mOnCancelBtnClickedListener;

        public BuildParams() {
            super();
            mDelayToConfirm = new IntDataItem();
            mDelayToConfirm.setName(PARAM_DELAY_TO_CONFIRM);
            mDelayToConfirm.setNewData(0);
        }

        public BuildParams title(String title) {
            if (mTitle == null) {
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
            if (mTitleStyle == null) {
                mTitleStyle = new ObjectDataItem();
                mTitleStyle.setName(BuildParams.PARAM_TITLE_STYLE);
            }
            mTitleStyle.setNewData(titleStyle);
            return this;
        }

        public TextStyle getTitleStyle() {
            return mTitleStyle == null ? null : mTitleStyle.getCurrentData();
        }

        public BuildParams items(List items) {
            if (mItems == null) {
                mItems = new ObjectDataItem();
                mItems.setName(BuildParams.PARAM_ITEMS);
            }
            mItems.setNewData(items);
            return this;
        }

        public List getItems() {
            return mItems == null ? null : mItems.getCurrentData();
        }

        public BuildParams itemStyle(TextStyle itemStyle) {
            if (mItemStyle == null) {
                mItemStyle = new ObjectDataItem();
                mItemStyle.setName(BuildParams.PARAM_ITEM_STYLE);
            }
            mItemStyle.setNewData(itemStyle);
            return this;
        }

        public TextStyle getItemStyle() {
            return mItemStyle == null ? null : mItemStyle.getCurrentData();
        }

        public BuildParams choiceMode(
                @ChooseListDialogProvider.ChoiceMode int choiceMode) {
            if (mChoiceMode == null) {
                mChoiceMode = new IntDataItem();
                mChoiceMode.setName(BuildParams.PARAM_CHOICE_MODE);
            }
            mChoiceMode.setNewData(choiceMode);
            return this;
        }

        @ChooseListDialogProvider.ChoiceMode
        public int getChoiceMode() {
            return mChoiceMode == null ? 0 : mChoiceMode.getCurrentData();
        }

        public BuildParams defaultChosenItems(int[] defaultChosenItems) {
            if (mDefaultChosenItems == null) {
                mDefaultChosenItems = new ObjectDataItem();
                mDefaultChosenItems.setName(BuildParams.PARAM_DEFAULT_CHOSEN_ITEMS);
            }
            mDefaultChosenItems.setNewData(defaultChosenItems);
            return this;
        }

        public int[] getDefaultChosenItems() {
            return mDefaultChosenItems == null ? null : mDefaultChosenItems.getCurrentData();
        }

        public BuildParams checkMarkPos(
                @ChooseListDialogProvider.CheckMarkPos int checkMarkPos) {
            if (mCheckMarkPos == null) {
                mCheckMarkPos = new IntDataItem();
                mCheckMarkPos.setName(BuildParams.PARAM_CHECK_MARK_POS);
            }
            mCheckMarkPos.setNewData(checkMarkPos);
            return this;
        }

        @ChooseListDialogProvider.CheckMarkPos
        public int getCheckMarkPos() {
            return mCheckMarkPos == null ? 0 : mCheckMarkPos.getCurrentData();
        }

        public BuildParams checkMarkType(
                @ChooseListDialogProvider.CheckMarkType int checkMarkType) {
            if (mCheckMarkType == null) {
                mCheckMarkType = new IntDataItem();
                mCheckMarkType.setName(BuildParams.PARAM_CHECK_MARK_TYPE);
            }
            mCheckMarkType.setNewData(checkMarkType);
            return this;
        }

        @ChooseListDialogProvider.CheckMarkType
        public int getCheckMarkType() {
            return mCheckMarkType == null ? 0 : mCheckMarkType.getCurrentData();
        }

        public BuildParams confirmBtnLabel(String confirmBtnLabel) {
            if (mConfirmBtnLabel == null) {
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
            if (mConfirmLabelStyle == null) {
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
            if (mDelayToConfirm == null) {
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
            if (mOnConfirmBtnClickedListener == null) {
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
            if (mCancelBtnLabel == null) {
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
            if (mCancelBtnLabelStyle == null) {
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
            if (mOnCancelBtnClickedListener == null) {
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
            if (mOnBuildParamChangedListener == null) {
                return;
            }
            if (mTitle != null && mTitle.isDataChanged()) {
                mTitle.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mTitle.getName());
            }
            if (mTitleStyle != null && mTitleStyle.isDataChanged()) {
                mTitleStyle.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mTitleStyle.getName());
            }
            if (mItems != null && mItems.isDataChanged()) {
                mItems.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mItems.getName());
            }
            if (mItemStyle != null && mItemStyle.isDataChanged()) {
                mItemStyle.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mItemStyle.getName());
            }
            if (mChoiceMode != null && mChoiceMode.isDataChanged()) {
                mChoiceMode.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mChoiceMode.getName());
            }
            if (mDefaultChosenItems != null && mDefaultChosenItems.isDataChanged()) {
                mDefaultChosenItems.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mDefaultChosenItems.getName());
            }
            if (mCheckMarkPos != null && mCheckMarkPos.isDataChanged()) {
                mCheckMarkPos.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mCheckMarkPos.getName());
            }
            if (mCheckMarkType != null && mCheckMarkType.isDataChanged()) {
                mCheckMarkType.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mCheckMarkType.getName());
            }
            if (mConfirmBtnLabel != null && mConfirmBtnLabel.isDataChanged()) {
                mConfirmBtnLabel.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mConfirmBtnLabel.getName());
            }
            if (mConfirmLabelStyle != null && mConfirmLabelStyle.isDataChanged()) {
                mConfirmLabelStyle.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mConfirmLabelStyle.getName());
            }
            if (mDelayToConfirm != null && mDelayToConfirm.isDataChanged()) {
                mDelayToConfirm.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mDelayToConfirm.getName());
            }
            if (mOnConfirmBtnClickedListener != null && mOnConfirmBtnClickedListener.isDataChanged()) {
                mOnConfirmBtnClickedListener.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mOnConfirmBtnClickedListener.getName());
            }
            if (mCancelBtnLabel != null && mCancelBtnLabel.isDataChanged()) {
                mCancelBtnLabel.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mCancelBtnLabel.getName());
            }
            if (mCancelBtnLabelStyle != null && mCancelBtnLabelStyle.isDataChanged()) {
                mCancelBtnLabelStyle.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mCancelBtnLabelStyle.getName());
            }
            if (mOnCancelBtnClickedListener != null && mOnCancelBtnClickedListener.isDataChanged()) {
                mOnCancelBtnClickedListener.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mOnCancelBtnClickedListener.getName());
            }
        }

        public DataItem getData(String dataName) {
            super.getData(dataName);
            switch (dataName) {
                case PARAM_TITLE:
                    return mTitle;
                case PARAM_TITLE_STYLE:
                    return mTitleStyle;
                case PARAM_ITEMS:
                    return mItems;
                case PARAM_ITEM_STYLE:
                    return mItemStyle;
                case PARAM_CHOICE_MODE:
                    return mChoiceMode;
                case PARAM_DEFAULT_CHOSEN_ITEMS:
                    return mDefaultChosenItems;
                case PARAM_CHECK_MARK_POS:
                    return mCheckMarkPos;
                case PARAM_CHECK_MARK_TYPE:
                    return mCheckMarkType;
                case PARAM_CONFIRM_BTN_LABEL:
                    return mConfirmBtnLabel;
                case PARAM_CONFIRM_LABEL_STYLE:
                    return mConfirmLabelStyle;
                case PARAM_DELAY_TO_CONFIRM:
                    return mDelayToConfirm;
                case PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER:
                    return mOnConfirmBtnClickedListener;
                case PARAM_CANCEL_BTN_LABEL:
                    return mCancelBtnLabel;
                case PARAM_CANCEL_BTN_LABEL_STYLE:
                    return mCancelBtnLabelStyle;
                case PARAM_ON_CANCEL_BTN_CLICKED_LISTENER:
                    return mOnCancelBtnClickedListener;
                default:
                    return null;
            }
        }

        public void reset() {
            super.reset();
            if (mTitle != null) {
                mTitle.reset();
            }
            if (mTitleStyle != null) {
                mTitleStyle.reset();
            }
            if (mItems != null) {
                mItems.reset();
            }
            if (mItemStyle != null) {
                mItemStyle.reset();
            }
            if (mChoiceMode != null) {
                mChoiceMode.reset();
            }
            if (mDefaultChosenItems != null) {
                mDefaultChosenItems.reset();
            }
            if (mCheckMarkPos != null) {
                mCheckMarkPos.reset();
            }
            if (mCheckMarkType != null) {
                mCheckMarkType.reset();
            }
            if (mConfirmBtnLabel != null) {
                mConfirmBtnLabel.reset();
            }
            if (mConfirmLabelStyle != null) {
                mConfirmLabelStyle.reset();
            }
            if (mDelayToConfirm != null) {
                mDelayToConfirm.reset();
            }
            if (mOnConfirmBtnClickedListener != null) {
                mOnConfirmBtnClickedListener.reset();
            }
            if (mCancelBtnLabel != null) {
                mCancelBtnLabel.reset();
            }
            if (mCancelBtnLabelStyle != null) {
                mCancelBtnLabelStyle.reset();
            }
            if (mOnCancelBtnClickedListener != null) {
                mOnCancelBtnClickedListener.reset();
            }
        }
    }
}
