package com.coder.zzq.smartshow.dialog.delegate.header;

import android.view.View;

import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.delegate.BaseDelegate;
import com.coder.zzq.smartshow.dialog.util.TextStyle;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.toolkit.Utils;
import com.example.annotations.BuildDelegate;
import com.example.annotations.BuildParam;

@BuildDelegate(buildParams = {
        @BuildParam(
                name = TitleDelegate.PARAM_TITLE,
                type = String.class
        ),
        @BuildParam(
                name = TitleDelegate.PARAM_TITLE_STYLE,
                type = TextStyle.class
        )
})
public class TitleDelegate extends BaseDelegate implements HeaderDelegate {
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_TITLE_STYLE = "titleStyle";


    public TitleDelegate(DelegateScaffoldDialog delegateDialog) {
        super(delegateDialog);
    }


    @Override
    public int provideHeaderLayout() {
        return R.layout.smart_show_dialog_title;
    }

    @Override
    public void initHeader(AppCompatDialog dialog, ViewHolder viewHolder) {
        String title = mDelegateDialog.getBuildParams().getData(PARAM_TITLE) == null ? "" : mDelegateDialog.getBuildParams().getData(PARAM_TITLE).toText();
        mDelegateDialog.getViewHolder().setVisibility(R.id.smart_show_dialog_header_wrapper, Utils.isEmpty(title) ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onDataChanged(DataItem dataItem) {
        switch (dataItem.getName()) {
            case PARAM_TITLE:
                mDelegateDialog.getViewHolder().setVisibility(R.id.smart_show_dialog_header_wrapper, Utils.isEmpty(dataItem.toText()) ? View.GONE : View.VISIBLE);
                if (!Utils.isEmpty(dataItem.toText())) {
                    mDelegateDialog.getViewHolder().setText(R.id.smart_show_dialog_title_view, dataItem.toText());
                }
                break;
            case PARAM_TITLE_STYLE:
                if (dataItem.toData(TextStyle.class) != null) {
                    dataItem.toData(TextStyle.class).apply(mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_title_view).toTextView());
                }
                break;
        }
    }
}
