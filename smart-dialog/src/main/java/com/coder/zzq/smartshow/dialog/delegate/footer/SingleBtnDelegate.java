package com.coder.zzq.smartshow.dialog.delegate.footer;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.base.ISmartDialog;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.delegate.BaseDelegate;
import com.coder.zzq.smartshow.dialog.util.TextStyle;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.example.annotations.BuildDelegate;
import com.example.annotations.BuildParam;

@BuildDelegate(
        buildParams = {
                @BuildParam(
                        name = SingleBtnDelegate.PARAM_CONFIRM_BTN_LABEL,
                        type = String.class,
                        defaultValue = "确定"
                ),
                @BuildParam(
                        name = SingleBtnDelegate.PARAM_CONFIRM_BTN_LABEL_STYLE,
                        type = TextStyle.class
                ),
                @BuildParam(
                        name = SingleBtnDelegate.PARAM_DELAY_TO_CONFIRM,
                        type = int.class,
                        defaultValue = "0"
                ),
                @BuildParam(
                        name = SingleBtnDelegate.PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER,
                        type = OnDialogBtnClickedListener.class
                ),

        }
)
public class SingleBtnDelegate extends BaseDelegate implements FooterDelegate, View.OnClickListener, ConfirmDelayCallback {

    public static final String PARAM_CONFIRM_BTN_LABEL = "confirmBtnLabel";
    public static final String PARAM_CONFIRM_BTN_LABEL_STYLE = "confirmLabelStyle";
    public static final String PARAM_DELAY_TO_CONFIRM = "delayToConfirm";
    public static final String PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER = "onConfirmBtnClickedListener";

    protected DataCallback mDataCallback;
    protected ActionCheckCallback mActionCheckCallback;

    private int mSecondsDelayConfirmTotal;
    private int mSecondsDelayInitValue = mSecondsDelayConfirmTotal;
    private String mSrcConfirmBtnLabel;
    private int mSrcConfirmLabelColor;
    private StringBuilder mConfirmLabelWhenDelay = new StringBuilder();


    public SingleBtnDelegate(DelegateScaffoldDialog delegateDialog) {
        super(delegateDialog);
    }

    public SingleBtnDelegate setDataCallback(DataCallback dataCallback) {
        mDataCallback = dataCallback;
        return this;
    }

    public SingleBtnDelegate setActionCheckCallback(ActionCheckCallback actionCheckCallback) {
        mActionCheckCallback = actionCheckCallback;
        return this;
    }

    @Override
    public int provideFooterLayout() {
        return R.layout.smart_show_default_single_button;
    }

    @Override
    public void initFooter(final AppCompatDialog nestedDialog, final ISmartDialog smartDialog, final ViewHolder viewHolder) {
        viewHolder.setOnClickListener(R.id.smart_show_dialog_confirm_btn, this);
        viewHolder.addOnAttachStateChangeListener(R.id.smart_show_dialog_confirm_btn, this);
        viewHolder.setTextColorRes(R.id.smart_show_dialog_confirm_btn, R.color.colorPrimary);
    }

    @Override
    public void onDataChanged(DataItem dataItem) {
        switch (dataItem.getName()) {
            case PARAM_CONFIRM_BTN_LABEL:
                mDelegateDialog.getViewHolder().setText(R.id.smart_show_dialog_confirm_btn, dataItem.toText());
                break;
            case PARAM_CONFIRM_BTN_LABEL_STYLE:
                if (dataItem.toData(TextStyle.class) != null) {
                    dataItem.toData(TextStyle.class).apply(mDelegateDialog.getViewHolder().getTextView(R.id.smart_show_dialog_confirm_btn));
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (mActionCheckCallback != null && !mActionCheckCallback.canProceed()) {
            return;
        }
        if (v.getId() == R.id.smart_show_dialog_confirm_btn) {
            if (mDelegateDialog.getBuildParams().getData(PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER) != null) {
                mDelegateDialog.getBuildParams().getData(PARAM_ON_CONFIRM_BTN_CLICKED_LISTENER)
                        .toData(OnDialogBtnClickedListener.class)
                        .onDialogBtnClicked(mDelegateDialog, mDataCallback == null ? null : mDataCallback.provideDataWhenConfirmButtonClicked());
            } else {
                mDelegateDialog.dismiss();
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        reset();
        if (mSecondsDelayConfirmTotal > 0) {
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setEnabled(false);
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setTextColor(Color.parseColor("#bbbbbb"));
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().post(this);
        }
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        if (mSecondsDelayConfirmTotal <= 0) {
            return;
        }
        mDelegateDialog.getViewHolder().setText(R.id.smart_show_dialog_confirm_btn, mSrcConfirmBtnLabel);
        mDelegateDialog.getViewHolder().setTextColor(R.id.smart_show_dialog_confirm_btn, mSrcConfirmLabelColor);
        mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().removeCallbacks(this);
        mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setEnabled(true);
        mSecondsDelayConfirmTotal = mSecondsDelayInitValue;
    }

    @Override
    public void reset() {
        mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().removeCallbacks(this);
        DataItem delayToConfirm = mDelegateDialog.getBuildParams().getData(PARAM_DELAY_TO_CONFIRM);
        mSecondsDelayConfirmTotal = delayToConfirm == null ? 0 : delayToConfirm.toInt();
        mSecondsDelayInitValue = mSecondsDelayConfirmTotal;
        mSrcConfirmBtnLabel = mDelegateDialog.getViewHolder().getText(R.id.smart_show_dialog_confirm_btn);
        mSrcConfirmLabelColor = mDelegateDialog.getViewHolder().getTextColor(R.id.smart_show_dialog_confirm_btn);
    }

    @Override
    public void run() {
        if (mSecondsDelayConfirmTotal > 0) {
            mConfirmLabelWhenDelay.delete(0, mConfirmLabelWhenDelay.length());
            mConfirmLabelWhenDelay.append(mSrcConfirmBtnLabel)
                    .append("(")
                    .append(mSecondsDelayConfirmTotal)
                    .append("s)");
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setText(mConfirmLabelWhenDelay.toString());
            mSecondsDelayConfirmTotal--;
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().postDelayed(this, 1000);
        } else {
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setEnabled(true);
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setText(mSrcConfirmBtnLabel);
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().setTextColor(mSrcConfirmLabelColor);
            mDelegateDialog.getViewHolder().getView(R.id.smart_show_dialog_confirm_btn).toTextView().removeCallbacks(this);
        }
    }


    public interface DataCallback {
        Object provideDataWhenConfirmButtonClicked();
    }

    public interface ActionCheckCallback {
        boolean canProceed();
    }
}
