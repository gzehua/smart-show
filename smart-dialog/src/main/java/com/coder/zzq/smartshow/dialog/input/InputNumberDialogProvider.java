package com.coder.zzq.smartshow.dialog.input;

import android.app.Activity;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;

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

public class InputNumberDialogProvider extends DelegateScaffoldDialog<InputNumberDialog.BuildParams> implements SingleBtnDelegate.DataCallback, TextWatcher, SingleBtnDelegate.ActionCheckCallback {
    public static final int NUMBER_TYPE_INT = 0;
    public static final int NUMBER_TYPE_DECIMAL = 1;

    @Retention(RetentionPolicy.CLASS)
    @IntDef({NUMBER_TYPE_INT, NUMBER_TYPE_DECIMAL})
    public @interface NumberType {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mBuildParams.getInputCheckCallback() != null) {
            mViewHolder.setVisibility(R.id.smart_show_error_tip_line, mBuildParams.getInputCheckCallback().isValid(s.toString()) ? View.GONE : View.VISIBLE);
            if (!mBuildParams.getInputCheckCallback().isValid(s.toString())) {
                mViewHolder.setText(R.id.smart_show_error_tip, mBuildParams.getInputCheckCallback().errorInfoWhenInputInvalid());
            }
        }
    }

    @Override
    protected void createNestedDialog(Activity activity, int screenOrientation) {
        super.createNestedDialog(activity, screenOrientation);
        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Utils.popKeyboardWhenDialogShow(mNestedDialog);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Utils.hideKeyboard(mViewHolder.getRootView().toView());
        }
    }

    @Override
    public void onResetDialogWhenShowAgain() {
        super.onResetDialogWhenShowAgain();
        mViewHolder.setVisibility(R.id.smart_show_error_tip_line, View.GONE);
        mViewHolder.setText(R.id.smart_show_input_edt, mBuildParams.getInitialFilledNumber());
        mViewHolder.setSelection(R.id.smart_show_input_edt, mViewHolder.getText(R.id.smart_show_input_edt).length());
    }

    @Override
    protected HeaderDelegate createHeaderDelegate() {
        return new TitleDelegate(this);
    }

    @Override
    protected int bodyLayout() {
        return R.layout.smart_show_input_num;
    }

    @Override
    protected void initBody() {
        super.initBody();
        mViewHolder.getView(R.id.smart_show_input_edt).toView().getLayoutParams().width = provideDialogWidth() / 3;
        mViewHolder.getEditText(R.id.smart_show_input_edt).requestFocus();
        mViewHolder.getTextView(R.id.smart_show_num_unit).setMaxWidth(provideDialogWidth() / 3 - Utils.dpToPx(40));
        mViewHolder.setInputType(R.id.smart_show_input_edt, EditorInfo.TYPE_CLASS_NUMBER);
        mViewHolder.setText(R.id.smart_show_input_edt, mBuildParams.getInitialFilledNumber());
        mViewHolder.setSelection(R.id.smart_show_input_edt, mBuildParams.getInitialFilledNumber().length());
        mViewHolder.getEditText(R.id.smart_show_input_edt).addTextChangedListener(this);
    }

    private int parseInputType() {
        int type = EditorInfo.TYPE_CLASS_NUMBER;
        switch (mBuildParams.getNumberType()) {
            case NUMBER_TYPE_INT:
                type = EditorInfo.TYPE_CLASS_NUMBER;
                break;
            case NUMBER_TYPE_DECIMAL:
                type |= EditorInfo.TYPE_NUMBER_FLAG_DECIMAL;
                break;
        }
        if (mBuildParams.isHasSigned()) {
            type |= EditorInfo.TYPE_NUMBER_FLAG_SIGNED;
        }

        return type;
    }

    @Override
    protected FooterDelegate createFooterDelegate() {
        return new DoubleBtnDelegate(this).setDataCallback(this).setActionCheckCallback(this);
    }

    @Override
    public Object provideDataWhenConfirmButtonClicked() {
        return mViewHolder.getText(R.id.smart_show_input_edt);
    }

    @Override
    public boolean canProceed() {
        return mBuildParams.getInputCheckCallback() != null && mBuildParams.getInputCheckCallback().isValid(mViewHolder.getText(R.id.smart_show_input_edt));
    }

    @Override
    public void onBuildParamChanged(String paramName) {
        super.onBuildParamChanged(paramName);
        switch (paramName) {
            case InputNumberDialog.BuildParams.PARAM_NUMBER_UNIT:
                if (!Utils.isEmpty(mBuildParams.getNumberUnit())) {
                    mViewHolder.setVisibility(R.id.smart_show_input_number_unit_part, View.VISIBLE);
                    mViewHolder.setText(R.id.smart_show_num_unit, mBuildParams.getNumberUnit());
                } else {
                    mViewHolder.setVisibility(R.id.smart_show_input_number_unit_part, View.GONE);
                }
                break;
            case InputNumberDialog.BuildParams.PARAM_NUMBER_TYPE:
            case InputNumberDialog.BuildParams.PARAM_HAS_SIGNED:
                mViewHolder.setInputType(R.id.smart_show_input_edt, parseInputType());
                break;
        }
    }
}
