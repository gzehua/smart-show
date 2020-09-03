package com.coder.zzq.smartshow.dialog.list;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.annotation.IntDef;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.delegate.footer.DoubleBtnDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.FooterDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.SingleBtnDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.HeaderDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.TitleDelegate;
import com.coder.zzq.toolkit.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ChooseListDialogProvider extends DelegateScaffoldDialog<ChooseListDialog.BuildParams> implements SingleBtnDelegate.DataCallback {
    public static final int CHECK_MARK_TYPE_CIRCLE = 0;
    public static final int CHECK_MARK_TYPE_CUBE = 1;
    public static final int CHOICE_MODE_SINGLE = 0;
    public static final int CHOICE_MODE_MULTIPLE = 1;
    public static final int CHECK_MARK_POS_LEFT = 0;
    public static final int CHECK_MARK_POS_RIGHT = 1;

    @Retention(RetentionPolicy.CLASS)
    @IntDef({CHECK_MARK_TYPE_CIRCLE, CHECK_MARK_TYPE_CUBE})
    public @interface CheckMarkType {

    }

    @Retention(RetentionPolicy.CLASS)
    @IntDef({CHOICE_MODE_SINGLE, CHOICE_MODE_MULTIPLE})
    public @interface ChoiceMode {

    }

    @Retention(RetentionPolicy.CLASS)
    @IntDef({CHECK_MARK_POS_LEFT, CHECK_MARK_POS_RIGHT})
    public @interface CheckMarkPos {
    }

    @Override
    public void onResetDialogWhenShowAgain() {
        super.onResetDialogWhenShowAgain();
        setDefaultChosenItems(mBuildParams.getDefaultChosenItems());
    }

    @Override
    protected HeaderDelegate createHeaderDelegate() {
        return new TitleDelegate(this);
    }

    @Override
    protected int bodyLayout() {
        return R.layout.smart_show_list_dialog;
    }

    @Override
    protected void initBody() {
        super.initBody();
        mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().setPadding(
                mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().getPaddingLeft(),
                mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().getPaddingTop(),
                mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().getPaddingRight(),
                0);
        mViewHolder.getView(R.id.smart_show_list_view).toListView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        mViewHolder.getView(R.id.smart_show_list_view).toListView().setDivider(new ColorDrawable(Color.parseColor("#cccccc")));
        mViewHolder.getView(R.id.smart_show_list_view).toListView().setDividerHeight(Utils.dpToPx(0.5f));
        mViewHolder.getView(R.id.smart_show_list_view).toListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mViewHolder.getView(R.id.smart_show_list_view).toListView().setAdapter(new ChooseListAdapter());
    }

    @Override
    protected FooterDelegate createFooterDelegate() {
        return new DoubleBtnDelegate(this)
                .setDataCallback(this);
    }

    @Override
    public Object provideDataWhenConfirmButtonClicked() {
        int chosenCount = mViewHolder.getView(R.id.smart_show_list_view).toListView().getCheckedItemCount();
        if (chosenCount == 0) {
            return ChooseResult.emptyResultInstance();
        }
        int[] choosePositions = new int[chosenCount];

        Object[] chooseItems = new Object[chosenCount];

        switch (mViewHolder.getView(R.id.smart_show_list_view).toListView().getChoiceMode()) {
            case ListView.CHOICE_MODE_SINGLE:
                int singleChoosePos = mViewHolder.getView(R.id.smart_show_list_view).toListView().getCheckedItemPosition();
                choosePositions[0] = singleChoosePos;
                chooseItems[0] = mViewHolder.getView(R.id.smart_show_list_view).toListView().getItemAtPosition(singleChoosePos);
                break;

            case ListView.CHOICE_MODE_MULTIPLE:
                SparseBooleanArray sparseBooleanArray = mViewHolder.getView(R.id.smart_show_list_view).toListView().getCheckedItemPositions();
                for (int index = 0, chooseIndex = 0; index < sparseBooleanArray.size(); index++) {
                    if (sparseBooleanArray.valueAt(index)) {
                        int choosePos = sparseBooleanArray.keyAt(index);
                        choosePositions[chooseIndex] = choosePos;
                        chooseItems[chooseIndex] = mViewHolder.getView(R.id.smart_show_list_view).toListView().getItemAtPosition(choosePos);
                        chooseIndex++;
                    }
                }
                break;
        }
        return new ChooseResult()
                .setChoosePositions(choosePositions)
                .setChooseItems(chooseItems);
    }


    @Override
    public void onBuildParamChanged(String paramName) {
        super.onBuildParamChanged(paramName);
        switch (paramName) {
            case ChooseListDialog.BuildParams.PARAM_ITEMS:
                ((ChooseListAdapter) mViewHolder.getView(R.id.smart_show_list_view).toListView().getAdapter()).setItems(mBuildParams.getItems());
                adjustHeight();
                break;
            case ChooseListDialog.BuildParams.PARAM_DEFAULT_CHOSEN_ITEMS:
                setDefaultChosenItems(mBuildParams.getDefaultChosenItems());
                break;
            case ChooseListDialog.BuildParams.PARAM_CHECK_MARK_POS:
                ((ChooseListAdapter) mViewHolder.getView(R.id.smart_show_list_view).toListView()
                        .getAdapter()).setCheckMarkPos(mBuildParams.getCheckMarkPos());
                break;
            case ChooseListDialog.BuildParams.PARAM_CHECK_MARK_TYPE:
                ((ChooseListAdapter) mViewHolder.getView(R.id.smart_show_list_view).toListView()
                        .getAdapter()).setUseCubeMark(mBuildParams.getCheckMarkType() == CHECK_MARK_TYPE_CUBE);
                break;
            case ChooseListDialog.BuildParams.PARAM_CHOICE_MODE:
                switch (mBuildParams.getChoiceMode()) {
                    case CHOICE_MODE_MULTIPLE:
                        mViewHolder.getView(R.id.smart_show_list_view).toListView()
                                .setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                        break;
                    default:
                        mViewHolder.getView(R.id.smart_show_list_view).toListView()
                                .setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                }
                break;
            case ChooseListDialog.BuildParams.PARAM_ITEM_STYLE:
                ((ChooseListAdapter) mViewHolder.getListView(R.id.smart_show_list_view).getAdapter()).setTextStyle(mBuildParams.getItemStyle());
                break;
        }
    }


    private void setDefaultChosenItems(int[] chosenPos) {
        mViewHolder.getListView(R.id.smart_show_list_view).clearChoices();
        if (chosenPos == null || chosenPos.length == 0) {
            return;
        }
        int length = mBuildParams.getChoiceMode() == CHOICE_MODE_SINGLE ? 1 : Math.min(chosenPos.length, mViewHolder.getListView(R.id.smart_show_list_view).getCount());
        for (int index = 0; index < length; index++) {
            if (chosenPos[index] >= 0 && chosenPos[index] < mViewHolder.getListView(R.id.smart_show_list_view).getCount()) {
                mViewHolder.getView(R.id.smart_show_list_view).toListView()
                        .setItemChecked(chosenPos[index], true);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adjustHeight();
    }

    private void adjustHeight() {
        if (getBuildParams().getItems() == null || getBuildParams().getItems().isEmpty()) {
            return;
        }
        ViewGroup.LayoutParams lp = getViewHolder().getListView(R.id.smart_show_list_view).getLayoutParams();
        switch (Utils.screenOrientation()) {
            case Configuration.ORIENTATION_PORTRAIT:
                adjustHeightWhenScreenPortrait(lp);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                adjustHeightWhenScreenLandscape(lp);
                break;
        }
        getViewHolder().getListView(R.id.smart_show_list_view).setLayoutParams(lp);
    }

    private void adjustHeightWhenScreenPortrait(ViewGroup.LayoutParams lp) {
        if (getBuildParams().getItems().size() < 4) {
            lp.height = Utils.dpToPx(50) * (getBuildParams().getItems().size() + 2);
        } else if (getBuildParams().getItems().size() > 5) {
            lp.height = Utils.dpToPx(50) * 5;
        } else {
            lp.height = ListView.LayoutParams.WRAP_CONTENT;
        }
    }

    private void adjustHeightWhenScreenLandscape(ViewGroup.LayoutParams lp) {
        if (getBuildParams().getItems().size() <= 3) {
            lp.height = Utils.dpToPx(50) * (getBuildParams().getItems().size() + (3 - getBuildParams().getItems().size()));
        } else {
            lp.height = Utils.dpToPx(50) * 3;
        }
    }
}
