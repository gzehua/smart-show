package com.coder.zzq.smartshow.dialog.base;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.toolkit.Utils;

public abstract class ScaffoldDialog<BuildParamsType extends SmartBuildParams> extends NormalDialog<BuildParamsType> {


    @Override
    protected int provideContentLayout() {
        return R.layout.smart_show_scaffold_dialog;
    }

    @Override
    protected void initView() {
        super.initView();
        inflateWrappedView(provideHeaderLayout(), R.id.smart_show_dialog_header_wrapper);
        inflateWrappedView(provideBodyLayout(), R.id.smart_show_dialog_body_wrapper);
        inflateWrappedView(provideFooterLayout(), R.id.smart_show_dialog_foot_wrapper);

        initHeader();
        initBody();
        initFooter();
    }

    private void inflateWrappedView(@LayoutRes int layout, @IdRes int parentId) {
        if (layout != 0) {
            Utils.inflate(layout, mViewHolder.getView(parentId).toViewGroup(), true);
        }
    }

    @LayoutRes
    protected abstract int provideHeaderLayout();

    protected void initHeader() {

    }

    @LayoutRes
    protected abstract int provideBodyLayout();

    protected void initBody() {

    }


    @LayoutRes
    protected abstract int provideFooterLayout();

    protected void initFooter() {

    }
}
