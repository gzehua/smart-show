package com.coder.zzq.smartshow.dialog.base;

import com.coder.zzq.smartshow.dialog.delegate.body.BodyDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.FooterDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.HeaderDelegate;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;

public abstract class DelegateScaffoldDialog<BuildParamsType extends SmartBuildParams> extends ScaffoldDialog<BuildParamsType> {

    protected HeaderDelegate mHeaderDelegate;
    protected BodyDelegate mBodyDelegate;
    protected FooterDelegate mFooterDelegate;

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }

    @Override
    protected void initView() {
        mHeaderDelegate = createHeaderDelegate();
        mBodyDelegate = createBodyDelegate();
        mFooterDelegate = createFooterDelegate();
        super.initView();
    }

    protected HeaderDelegate createHeaderDelegate() {
        return null;
    }

    protected FooterDelegate createFooterDelegate() {
        return null;
    }

    protected BodyDelegate createBodyDelegate() {
        return null;
    }

    @Override
    protected final int provideHeaderLayout() {
        return mHeaderDelegate == null ? headerLayout() : mHeaderDelegate.provideHeaderLayout();
    }

    protected int headerLayout() {
        return 0;
    }

    @Override
    protected void initHeader() {
        super.initHeader();
        if (mHeaderDelegate != null) {
            mHeaderDelegate.initHeader(mNestedDialog, getViewHolder());
        }
    }

    @Override
    protected final int provideBodyLayout() {
        return mBodyDelegate == null ? bodyLayout() : mBodyDelegate.provideBodyLayout();
    }

    protected int bodyLayout() {
        return 0;
    }

    @Override
    protected void initBody() {
        super.initBody();

        if (mBodyDelegate != null) {
            mBodyDelegate.initBody(getNestedDialog(), getViewHolder());
        }
    }

    @Override
    protected final int provideFooterLayout() {
        return mFooterDelegate == null ? footerLayout() : mFooterDelegate.provideFooterLayout();
    }

    protected int footerLayout() {
        return 0;
    }

    @Override
    protected void initFooter() {
        super.initFooter();
        if (mFooterDelegate != null) {
            mFooterDelegate.initFooter(getNestedDialog(), this, getViewHolder());
        }
    }

    @Override
    public void onBuildParamChanged(String paramName) {
        super.onBuildParamChanged(paramName);
        if (mHeaderDelegate != null && mBuildParams.getData(paramName) != null) {
            mHeaderDelegate.onDataChanged(mBuildParams.getData(paramName));
        }

        if (mBodyDelegate != null && mBuildParams.getData(paramName) != null) {
            mBodyDelegate.onDataChanged(mBuildParams.getData(paramName));
        }

        if (mFooterDelegate != null && mBuildParams.getData(paramName) != null) {
            mFooterDelegate.onDataChanged(mBuildParams.getData(paramName));
        }
    }


    @Override
    public boolean onHostDestroyed(String identity) {
        boolean destroyedPermanently = super.onHostDestroyed(identity);

        if (mHeaderDelegate != null) {
            mHeaderDelegate.destroy();
        }

        if (mBodyDelegate != null) {
            mBodyDelegate.destroy();
        }

        if (mFooterDelegate != null) {
            mFooterDelegate.destroy();
        }
        return destroyedPermanently;
    }
}
