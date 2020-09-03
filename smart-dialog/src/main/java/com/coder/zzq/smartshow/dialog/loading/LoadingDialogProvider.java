package com.coder.zzq.smartshow.dialog.loading;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.NormalDialog;
import com.coder.zzq.toolkit.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LoadingDialogProvider extends NormalDialog<LoadingDialog.BuildParams> {

    public static final int BOX_SIZE_LARGE = 0;
    public static final int BOX_SIZE_MIDDLE = 1;
    public static final int BOX_SIZE_SMALL = 2;

    @Retention(RetentionPolicy.CLASS)
    @IntDef({BOX_SIZE_LARGE, BOX_SIZE_MIDDLE, BOX_SIZE_SMALL})
    public @interface BoxSizeMode {

    }

    private static final int LARGE_BOX_SIZE = Utils.dpToPx(110);
    private static final int MIDDLE_BOX_SIZE = Utils.dpToPx(80);
    private static final int SMALL_BOX_SIZE = Utils.dpToPx(36);

    private static final int LARGE_PROGRESS_BAR_SIZE = Utils.dpToPx(34);
    private static final int MIDDLE_PROGRESS_BAR_SIZE = Utils.dpToPx(28);
    private static final int SMALL_PROGRESS_BAR_SIZE = Utils.dpToPx(20);

    private static final int LARGE_TEXT_SIZE = 15;
    private static final int MIDDLE_TEXT_SIZE = 12;

    private static final int LARGE_TEXT_TOP_PADDING = Utils.dpToPx(14);
    private static final int MIDDLE_TEXT_TOP_PADDING = Utils.dpToPx(10);


    @Override
    protected int provideContentLayout() {
        return R.layout.smart_show_loading_dialog;
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected int provideDialogWidth() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }


    @Override
    public void onBuildParamChanged(String paramName) {
        super.onBuildParamChanged(paramName);
        switch (paramName) {
            case LoadingDialog.BuildParams.PARAM_BOX_SIZE:
                ViewGroup.LayoutParams boxLp = mViewHolder.getView(R.id.smart_show_loading_box).toView().getLayoutParams();
                ViewGroup.LayoutParams progressBarLp = mViewHolder.getProgressBar(R.id.smart_show_loading_progress_bar).getLayoutParams();
                int textSize = LARGE_TEXT_SIZE;
                int textTopPadding = LARGE_TEXT_TOP_PADDING;
                switch (mBuildParams.getBoxSize()) {
                    case BOX_SIZE_LARGE:
                        boxLp.width = LARGE_BOX_SIZE;
                        boxLp.height = LARGE_BOX_SIZE;
                        progressBarLp.width = LARGE_PROGRESS_BAR_SIZE;
                        progressBarLp.height = LARGE_PROGRESS_BAR_SIZE;
                        textSize = LARGE_TEXT_SIZE;
                        textTopPadding = LARGE_TEXT_TOP_PADDING;
                        break;
                    case BOX_SIZE_MIDDLE:
                        boxLp.width = MIDDLE_BOX_SIZE;
                        boxLp.height = MIDDLE_BOX_SIZE;
                        progressBarLp.width = MIDDLE_PROGRESS_BAR_SIZE;
                        progressBarLp.height = MIDDLE_PROGRESS_BAR_SIZE;
                        textSize = MIDDLE_TEXT_SIZE;
                        textTopPadding = MIDDLE_TEXT_TOP_PADDING;
                        break;
                    case BOX_SIZE_SMALL:
                        boxLp.width = SMALL_BOX_SIZE;
                        boxLp.height = SMALL_BOX_SIZE;
                        progressBarLp.width = SMALL_PROGRESS_BAR_SIZE;
                        progressBarLp.height = SMALL_PROGRESS_BAR_SIZE;
                        break;
                }

                mViewHolder.getView(R.id.smart_show_loading_box).toView().setLayoutParams(boxLp);
                mViewHolder.getProgressBar(R.id.smart_show_loading_progress_bar).setLayoutParams(progressBarLp);
                mViewHolder.getTextView(R.id.smart_show_loading_message_view).setTextSize(textSize);
                mViewHolder.getTextView(R.id.smart_show_loading_message_view).setPadding(
                        mViewHolder.getTextView(R.id.smart_show_loading_message_view).getPaddingLeft(),
                        textTopPadding,
                        mViewHolder.getTextView(R.id.smart_show_loading_message_view).getPaddingRight(),
                        mViewHolder.getTextView(R.id.smart_show_loading_message_view).getPaddingBottom()
                );
                mViewHolder.setVisibility(R.id.smart_show_loading_message_view, mBuildParams.getBoxSize() == BOX_SIZE_SMALL || Utils.isEmpty(mBuildParams.getMessage()) ? View.GONE : View.VISIBLE);
                break;

            case LoadingDialog.BuildParams.PARAM_MESSAGE:
                mViewHolder.setText(R.id.smart_show_loading_message_view, mBuildParams.getMessage());
                mViewHolder.setVisibility(R.id.smart_show_loading_message_view, mBuildParams.getBoxSize() == BOX_SIZE_SMALL || Utils.isEmpty(mBuildParams.getMessage()) ? View.GONE : View.VISIBLE);
                break;
        }
    }
}
