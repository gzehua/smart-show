package com.coder.zzq.smartshow.dialog.delegate.body;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import com.coder.zzq.smartshow.dialog.R;
import com.coder.zzq.smartshow.dialog.base.DelegateScaffoldDialog;
import com.coder.zzq.smartshow.dialog.data_item.DataItem;
import com.coder.zzq.smartshow.dialog.delegate.BaseDelegate;
import com.coder.zzq.smartshow.dialog.util.TextStyle;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.example.annotations.BuildDelegate;
import com.example.annotations.BuildParam;

@BuildDelegate(buildParams = {
        @BuildParam(
                name = MessageDelegate.PARAM_MESSAGE,
                type = String.class,
                defaultValue = "一条通知"
        ),
        @BuildParam(
                name = MessageDelegate.PARAM_MESSAGE_STYLE,
                type = TextStyle.class
        )
})
public class MessageDelegate extends BaseDelegate implements BodyDelegate {
    public static final String PARAM_MESSAGE = "message";
    public static final String PARAM_MESSAGE_STYLE = "messageStyle";


    public MessageDelegate(DelegateScaffoldDialog delegateDialog) {
        super(delegateDialog);
    }


    @Override
    public int provideBodyLayout() {
        return R.layout.smart_show_message_content;
    }

    @Override
    public void initBody(AppCompatDialog dialog, ViewHolder viewHolder) {
        viewHolder.getView(R.id.smart_show_dialog_message_view).toView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                TextView msgView = (TextView) v;
                if (msgView.getLineCount() > 1) {
                    msgView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                } else {
                    msgView.setGravity(Gravity.CENTER);
                }
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
    }

    @Override
    public void onDataChanged(DataItem dataItem) {
        switch (dataItem.getName()) {
            case PARAM_MESSAGE:

                mDelegateDialog.getViewHolder().setText(R.id.smart_show_dialog_message_view, dataItem.toText());
                break;
            case PARAM_MESSAGE_STYLE:
                dataItem.toData(TextStyle.class).apply(mDelegateDialog.getViewHolder().getTextView(R.id.smart_show_dialog_message_view));
                break;
        }
    }

}
