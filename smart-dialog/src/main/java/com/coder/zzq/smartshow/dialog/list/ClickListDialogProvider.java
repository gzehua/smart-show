package com.coder.zzq.smartshow.dialog.list;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.base.ISmartDialog;
import com.coder.zzq.smartshow.dialog.delegate.header.HeaderDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.TitleDelegate;
import com.coder.zzq.toolkit.Utils;

public class ClickListDialogProvider extends DelegateScaffoldDialog<ClickListDialog.BuildParams> {

    @Override
    protected HeaderDelegate createHeaderDelegate() {
        return new TitleDelegate(this);
    }

    @Override
    protected int bodyLayout() {
        return R.layout.smart_show_click_list_dialog;
    }

    @Override
    protected void initBody() {
        super.initBody();
        getViewHolder().getView(R.id.smart_show_list_view).toListView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        getViewHolder().getView(R.id.smart_show_list_view).toListView().setDivider(new ColorDrawable(Color.parseColor("#cccccc")));
        getViewHolder().getView(R.id.smart_show_list_view).toListView().setDividerHeight(Utils.dpToPx(0.5f));
        getViewHolder().getView(R.id.smart_show_list_view).toListView().setAdapter(new ClickListAdapter());
        getViewHolder().getView(R.id.smart_show_list_view).toListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mBuildParams.getOnItemClickListener() != null) {
                    mBuildParams.getOnItemClickListener().onItemClicked(ClickListDialogProvider.this, position, mViewHolder.getView(R.id.smart_show_list_view).toListView().getAdapter().getItem(position));
                } else {
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onBuildParamChanged(String paramName) {
        super.onBuildParamChanged(paramName);
        switch (paramName) {
            case ClickListDialog.BuildParams.PARAM_ITEMS:
                ((ClickListAdapter) mViewHolder.getListView(R.id.smart_show_list_view).getAdapter()).setItems(mBuildParams.getItems());
                adjustItemsToShow();
                break;
            case ClickListDialog.BuildParams.PARAM_ITEM_CENTER:
                ((ClickListAdapter) mViewHolder.getListView(R.id.smart_show_list_view).getAdapter()).setItemCenter(mBuildParams.isItemCenter());
                break;
            case ClickListDialog.BuildParams.PARAM_ITEM_STYLE:
                ((ClickListAdapter) mViewHolder.getListView(R.id.smart_show_list_view).getAdapter()).setTextStyle(mBuildParams.getItemStyle());
                break;

        }
    }

    @Override
    protected void onScreenLandscape() {
        super.onScreenLandscape();
        adjustItemsToShow();
    }

    @Override
    protected void onScreenPortrait() {
        super.onScreenPortrait();
        adjustItemsToShow();
    }

    private void adjustItemsToShow() {
        if (mBuildParams.getItems() == null || mBuildParams.getItems().isEmpty()) {
            return;
        }

        ViewGroup.LayoutParams lp = mViewHolder.getListView(R.id.smart_show_list_view).getLayoutParams();
        if (mBuildParams.getItems().size() <= (Utils.isScreenPortrait() ? 5 : 4)) {
            lp.height = ListView.LayoutParams.WRAP_CONTENT;
        } else {
            lp.height = Utils.dpToPx(50) * (Utils.isScreenPortrait() ? 5 : 4);
        }
        mViewHolder.getListView(R.id.smart_show_list_view).setLayoutParams(lp);
    }

    public interface OnItemClickedListener {
        void onItemClicked(ISmartDialog smartDialog, int pos, Object data);
    }
}
