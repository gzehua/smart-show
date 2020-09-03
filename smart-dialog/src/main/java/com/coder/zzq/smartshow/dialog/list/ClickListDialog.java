package com.coder.zzq.smartshow.dialog.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.smartshow.dialog.base.DialogHostFragment;
import com.coder.zzq.smartshow.dialog.base.SmartBuildParams;
import com.coder.zzq.smartshow.dialog.base.SmartDialog;
import com.coder.zzq.smartshow.dialog.coordinator.SmartDialogCoordinator;
import com.coder.zzq.smartshow.dialog.data_item.BoolDataItem;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.data_item.ObjectDataItem;
import com.coder.zzq.smartshow.dialog.data_item.StringDataItem;
import com.coder.zzq.smartshow.dialog.util.TextStyle;

import java.util.Arrays;
import java.util.List;

public class ClickListDialog extends SmartDialog<ClickListDialogProvider> {
    public static ClickListDialog create(AppCompatActivity lifecycleBinder, String businessTag) {
        return create(lifecycleBinder.getSupportFragmentManager(), businessTag);
    }

    public static ClickListDialog create(Fragment lifecycleBinder, String businessTag) {
        return create(lifecycleBinder.getChildFragmentManager(), businessTag);
    }

    private static ClickListDialog create(FragmentManager lifecycleBinder, String businessTag) {
        DialogHostFragment fragment = (DialogHostFragment) lifecycleBinder.findFragmentByTag(businessTag);
        if (fragment == null) {
            ClickListDialog smartDialog = new ClickListDialog();
            smartDialog.setDialogProvider(new ClickListDialogProvider());
            smartDialog.getDialogProvider().setBuildParams(new BuildParams());
            smartDialog.getDialogProvider().getBuildParams().setOnBuildParamChangedListener(smartDialog.mDialogProvider);
            SmartDialogCoordinator.store(smartDialog.getIdentity(), smartDialog);
            DialogHostFragment.addInstance(lifecycleBinder, businessTag, smartDialog.getIdentity());
            return smartDialog;
        }
        return (ClickListDialog) SmartDialogCoordinator.retrieve(fragment.getIdentity());
    }

    public ClickListDialog resetWhenShowAgain(boolean resetWhenShowAgain) {
        mDialogProvider.getBuildParams().resetWhenShowAgain(resetWhenShowAgain);
        return this;
    }

    public ClickListDialog title(String title) {
        mDialogProvider.getBuildParams().title(title);
        return this;
    }

    public ClickListDialog titleStyle(TextStyle titleStyle) {
        mDialogProvider.getBuildParams().titleStyle(titleStyle);
        return this;
    }

    public ClickListDialog items(List items) {
        mDialogProvider.getBuildParams().items(items);
        return this;
    }

    public ClickListDialog items(Object... items) {
        mDialogProvider.getBuildParams().items(Arrays.asList(items));
        return this;
    }

    public ClickListDialog itemCenter(boolean itemCenter) {
        mDialogProvider.getBuildParams().itemCenter(itemCenter);
        return this;
    }

    public ClickListDialog itemStyle(TextStyle itemStyle) {
        mDialogProvider.getBuildParams().itemStyle(itemStyle);
        return this;
    }

    public ClickListDialog onItemClickListener(
            ClickListDialogProvider.OnItemClickedListener onItemClickListener) {
        mDialogProvider.getBuildParams().onItemClickListener(onItemClickListener);
        return this;
    }

    public static class BuildParams extends SmartBuildParams<BuildParams> {
        public static final String PARAM_TITLE = "title";

        public static final String PARAM_TITLE_STYLE = "titleStyle";

        public static final String PARAM_ITEMS = "items";

        public static final String PARAM_ITEM_CENTER = "itemCenter";

        public static final String PARAM_ITEM_STYLE = "itemStyle";

        public static final String PARAM_ON_ITEM_CLICK_LISTENER = "onItemClickListener";

        protected StringDataItem mTitle;

        protected ObjectDataItem<TextStyle> mTitleStyle;

        protected ObjectDataItem<List> mItems;

        protected BoolDataItem mItemCenter;

        protected ObjectDataItem<TextStyle> mItemStyle;

        protected ObjectDataItem<ClickListDialogProvider.OnItemClickedListener> mOnItemClickListener;

        public BuildParams() {
            super();
            mItemCenter = new BoolDataItem();
            mItemCenter.setName(PARAM_ITEM_CENTER);
            mItemCenter.setNewData(true);
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

        public BuildParams itemCenter(boolean itemCenter) {
            if (mItemCenter == null) {
                mItemCenter = new BoolDataItem();
                mItemCenter.setName(BuildParams.PARAM_ITEM_CENTER);
            }
            mItemCenter.setNewData(itemCenter);
            return this;
        }

        public boolean isItemCenter() {
            return mItemCenter == null ? false : mItemCenter.getCurrentData();
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

        public BuildParams onItemClickListener(
                ClickListDialogProvider.OnItemClickedListener onItemClickListener) {
            if (mOnItemClickListener == null) {
                mOnItemClickListener = new ObjectDataItem();
                mOnItemClickListener.setName(BuildParams.PARAM_ON_ITEM_CLICK_LISTENER);
            }
            mOnItemClickListener.setNewData(onItemClickListener);
            return this;
        }

        public ClickListDialogProvider.OnItemClickedListener getOnItemClickListener() {
            return mOnItemClickListener == null ? null : mOnItemClickListener.getCurrentData();
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
            if (mItemCenter != null && mItemCenter.isDataChanged()) {
                mItemCenter.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mItemCenter.getName());
            }
            if (mItemStyle != null && mItemStyle.isDataChanged()) {
                mItemStyle.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mItemStyle.getName());
            }
            if (mOnItemClickListener != null && mOnItemClickListener.isDataChanged()) {
                mOnItemClickListener.updateData();
                mOnBuildParamChangedListener.onBuildParamChanged(mOnItemClickListener.getName());
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
                case PARAM_ITEM_CENTER:
                    return mItemCenter;
                case PARAM_ITEM_STYLE:
                    return mItemStyle;
                case PARAM_ON_ITEM_CLICK_LISTENER:
                    return mOnItemClickListener;
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
            if (mItemCenter != null) {
                mItemCenter.reset();
            }
            if (mItemStyle != null) {
                mItemStyle.reset();
            }
            if (mOnItemClickListener != null) {
                mOnItemClickListener.reset();
            }
        }
    }
}
