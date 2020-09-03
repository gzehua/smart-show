package com.coder.zzq.smartshow.dialog.message;

import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.delegate.body.BodyDelegate;
import com.coder.zzq.smartshow.dialog.delegate.body.MessageDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.FooterDelegate;
import com.coder.zzq.smartshow.dialog.delegate.footer.SingleBtnDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.HeaderDelegate;
import com.coder.zzq.smartshow.dialog.delegate.header.TitleDelegate;

public class NotificationDialogProvider extends DelegateScaffoldDialog<NotificationDialog.BuildParams> {
    @Override
    protected HeaderDelegate createHeaderDelegate() {
        return new TitleDelegate(this);
    }

    @Override
    protected BodyDelegate createBodyDelegate() {
        return new MessageDelegate(this);
    }

    @Override
    protected FooterDelegate createFooterDelegate() {
        return new SingleBtnDelegate(this);
    }
}
