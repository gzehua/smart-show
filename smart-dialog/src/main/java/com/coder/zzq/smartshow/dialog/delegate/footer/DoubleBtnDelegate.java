package com.coder.zzq.smartshow.dialog.delegate.footer;

import android.view.View;

import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.base.ISmartDialog;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.util.TextStyle;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.example.annotations.BuildDelegate;
import com.example.annotations.BuildParam;

@BuildDelegate(
        buildParams = {
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_CONFIRM_BTN_LABEL,
                        type = String.class
                ),
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_CONFIRM_BTN_LABEL_STYLE,
                        type = TextStyle.class
                ),
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_DELAY_TO_CONFIRM,
                        type = int.class,
                        defaultValue = "0"
                ),
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER,
                        type = OnDialogBtnClickedListener.class
                ),
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_CANCEL_BTN_LABEL,
                        type = String.class
                ),
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_CANCEL_BTN_LABEL_STYLE,
                        type = TextStyle.class
                ),
                @BuildParam(
                        name = DoubleBtnDelegate.PARAM_ON_CANCEL_BTN_CLICKED_LISTENER,
                        type = OnDialogBtnClickedListener.class
                ),

        }
)
public class DoubleBtnDelegate extends SingleBtnDelegate implements FooterDelegate {
    public static final String PARAM_CANCEL_BTN_LABEL = "cancelBtnLabel";
    public static final String PARAM_CANCEL_BTN_LABEL_STYLE = "cancelBtnLabelStyle";
    public static final String PARAM_ON_CANCEL_BTN_CLICKED_LISTENER = "onCancelBtnClickedListener";

    public DoubleBtnDelegate(DelegateScaffoldDialog delegateDialog) {
        super(delegateDialog);
    }


    @Override
    public int provideFooterLayout() {
        return R.layout.smart_show_default_double_btn;
    }

    @Override
    public void initFooter(AppCompatDialog nestedDialog, ISmartDialog smartDialog, ViewHolder viewHolder) {
        super.initFooter(nestedDialog, smartDialog, viewHolder);
        mDelegateDialog.getViewHolder().setOnClickListener(R.id.smart_show_dialog_cancel_btn, this);
    }

    @Override
    public void onDataChanged(DataItem dataItem) {
        super.onDataChanged(dataItem);
        switch (dataItem.getName()) {
            case PARAM_CANCEL_BTN_LABEL:
                mDelegateDialog.getViewHolder().setText(R.id.smart_show_dialog_cancel_btn, dataItem.toText());
                break;
            case PARAM_CANCEL_BTN_LABEL_STYLE:
                if (dataItem.toData(TextStyle.class) != null) {
                    dataItem.toData(TextStyle.class).apply(mDelegateDialog.getViewHolder().getTextView(R.id.smart_show_dialog_cancel_btn));
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.smart_show_dialog_cancel_btn) {
            if (mDelegateDialog.getBuildParams().getData(PARAM_ON_CANCEL_BTN_CLICKED_LISTENER) != null
                    && mDelegateDialog.getBuildParams().getData(PARAM_ON_CANCEL_BTN_CLICKED_LISTENER).toData(OnDialogBtnClickedListener.class) != null) {
                mDelegateDialog.getBuildParams().getData(PARAM_ON_CANCEL_BTN_CLICKED_LISTENER)
                        .toData(OnDialogBtnClickedListener.class)
                        .onDialogBtnClicked(mDelegateDialog, mDataCallback == null ? null : mDataCallback.provideDataWhenConfirmButtonClicked());
            } else {
                mDelegateDialog.dismiss();
            }
        }
    }
}
