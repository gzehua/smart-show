package com.coder.zzq.smartshow.dialog.input;

import android.app.Activity;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.delegate.footer.DoubleBtnDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.FooterDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.SingleBtnDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.HeaderDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.TitleDelegate;
import com.coder.zzq.toolkit.Utils;


public class InputTextDialogProvider extends DelegateScaffoldDialog<InputTextDialog.BuildParams> implements TextWatcher, View.OnClickListener, SingleBtnDelegate.DataCallback, SingleBtnDelegate.ActionCheckCallback {
    public static int INPUT_NO_LIMIT = -1;


    @Override
    protected void createNestedDialog(Activity activity, int screenOrientation) {
        super.createNestedDialog(activity, screenOrientation);
        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Utils.popKeyboardWhenDialogShow(mNestedDialog);
        }
    }

    @Override
    public void onResetDialogWhenShowAgain() {
        super.onResetDialogWhenShowAgain();
        mViewHolder.setText(R.id.smart_show_input_edt, mBuildParams.getInitialFilledText());
        mViewHolder.setSelection(R.id.smart_show_input_edt, mBuildParams.getInitialFilledText().length());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Utils.hideKeyboard(mViewHolder.getRootView().toView());
        }
    }

    @Override
    protected HeaderDelegate createHeaderDelegate() {
        return new TitleDelegate(this);
    }

    @Override
    protected int bodyLayout() {
        return R.layout.smart_show_input_text;
    }

    @Override
    protected void initBody() {
        super.initBody();
        mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().setPadding(
                mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().getPaddingLeft(),
                mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().getPaddingTop(),
                mViewHolder.getView(R.id.smart_show_dialog_body_wrapper).toView().getPaddingRight(),
                0);
        mViewHolder.getView(R.id.smart_show_input_edt).toEditText().requestFocus();
        mViewHolder.getView(R.id.smart_show_input_edt).toEditText().addTextChangedListener(this);
        mViewHolder.setText(R.id.smart_show_input_edt, mBuildParams.getInitialFilledText());
        mViewHolder.setSelection(R.id.smart_show_input_edt, mBuildParams.getInitialFilledText().length());
        mViewHolder.getView(R.id.smart_show_clear_input).toView().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mViewHolder.getView(R.id.smart_show_input_edt).toEditText().setText("");
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        showNumCounter(s.toString());
        if (mBuildParams.getAtMostInput() != INPUT_NO_LIMIT && s.length() > mBuildParams.getAtMostInput()) {
            mViewHolder.getView(R.id.smart_show_input_edt).toEditText().setText(s.subSequence(0, mBuildParams.getAtMostInput()));
            mViewHolder.getView(R.id.smart_show_input_edt).toEditText().setSelection(mViewHolder.getView(R.id.smart_show_input_edt).toEditText().getText().length());
        }
    }

    private void showNumCounter(String s) {
        if (mBuildParams.getAtMostInput() == INPUT_NO_LIMIT) {
            mViewHolder.getView(R.id.smart_show_input_edt).toEditText().setText(String.valueOf(s.length()));
        } else {
            mViewHolder.getView(R.id.smart_show_input_count_mark).toTextView().setText(String.format("%d/%d", s.length(), mBuildParams.getAtMostInput() <= 0 ? INPUT_NO_LIMIT : mBuildParams.getAtMostInput()));
        }
    }

    @Override
    protected FooterDelegate createFooterDelegate() {
        return new DoubleBtnDelegate(this).setDataCallback(this).setActionCheckCallback(this);
    }

    @Override
    public void onBuildParamChanged(String paramName) {
        super.onBuildParamChanged(paramName);
        switch (paramName) {
            case InputTextDialog.BuildParams.PARAM_HINT:
                mViewHolder.setHint(R.id.smart_show_input_edt, mBuildParams.getHint());
                break;
            case InputTextDialog.BuildParams.PARAM_AT_MOST_INPUT:
                if (mBuildParams.getAtMostInput() <= 0) {
                    showNumCounter(mViewHolder.getText(R.id.smart_show_input_edt));
                }
                break;
            case InputTextDialog.BuildParams.PARAM_INPUT_COUNTER_COLOR:
                mViewHolder.setTextColor(R.id.smart_show_input_count_mark, mBuildParams.getInputCounterColor());
                break;
        }
    }

    @Override
    public Object provideDataWhenConfirmButtonClicked() {
        return mViewHolder.getText(R.id.smart_show_input_edt);
    }

    @Override
    public boolean canProceed() {
        return !Utils.isEmpty(mViewHolder.getText(R.id.smart_show_input_edt));
    }
}
