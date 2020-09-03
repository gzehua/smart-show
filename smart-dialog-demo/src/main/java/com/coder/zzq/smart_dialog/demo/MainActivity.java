package com.coder.zzq.smart_dialog.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.smartshow.core.SmartShow;
import com.coder.zzq.smartshow.dialog.base.ISmartDialog;
import com.coder.zzq.smartshow.dialog.delegate.footer.OnDialogBtnClickedListener;
import com.coder.zzq.smartshow.dialog.input.InputCheckCallback;
import com.coder.zzq.smartshow.dialog.input.InputNumberDialog;
import com.coder.zzq.smartshow.dialog.input.InputTextDialog;
import com.coder.zzq.smartshow.dialog.list.ClickListDialog;
import com.coder.zzq.smartshow.dialog.list.ClickListDialogProvider;
import com.coder.zzq.smartshow.dialog.message.EnsureDialog;
import com.coder.zzq.smartshow.dialog.message.NotificationDialog;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.coder.zzq.toolkit.Toolkit;
import com.coder.zzq.toolkit.Utils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmartShow.init(getApplication());
        Toolkit.setEnablePrintLog(true);
        mViewHolder = new ViewHolder(getWindow().getDecorView());
        mViewHolder.getListView(R.id.list_view)
                .setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                onNotificationDialogClicked();
                break;
            case 1:
                onEnsureDialogClicked();
                break;
            case 2:
                onEnsureDelayDialogClicked();
                break;
            case 3:
                onInputTextDialogClicked();
                break;
            case 4:
                onInputNumberDialogClicked();
                break;
            case 5:
                onClickListDialogClicked();
                break;
            case 6:
                onChooseListDialogClicked();
                break;
            case 7:
                onLoadingDialogClicked();
                break;
        }
    }

    private void onLoadingDialogClicked() {
        startActivity(new Intent(this, TestLoadingDialogActivity.class));
    }

    private void onChooseListDialogClicked() {
        startActivity(new Intent(this, TestChooseListDialogActivity.class));
    }

    private void onClickListDialogClicked() {
        ClickListDialog.create(this, "select comment operation")
                .items("回复", "转发", "私信回复", "复制", "举报")
                .onItemClickListener(new ClickListDialogProvider.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(ISmartDialog smartDialog, int pos, Object data) {
                        smartDialog.dismiss();
                        SmartToast.show(data.toString());
                    }
                })
                .show();
    }

    private void onInputNumberDialogClicked() {
        InputNumberDialog.create(this, "input weight")
                .initialFilledNumber("1")
                .title("输入货物重量")
                .numberUnit("千克")
                .resetWhenShowAgain(true)
                .inputCheckCallback(new InputCheckCallback() {
                    @Override
                    public boolean isValid(String input) {
                        if (Utils.isEmpty(input)) {
                            return false;
                        }

                        int number = Integer.parseInt(input);

                        return number > 0 && number < 51;

                    }

                    @Override
                    public String errorInfoWhenInputInvalid() {
                        return "货物重量需大于0且不超过50千克";
                    }
                })
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        SmartToast.show(data.toString());
                    }
                })
                .show();

    }

    private void onInputTextDialogClicked() {
        InputTextDialog.create(this, "input suggestion")
                .resetWhenShowAgain(false)
                .title("输入")
                .atMostInput(50)
                .hint("您的宝贵建议")
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        SmartToast.show(data.toString());
                    }
                })
                .show();
    }

    private void onEnsureDelayDialogClicked() {

        EnsureDialog.create(this, "enable develop mode")
                .message("确定开启开发者模式？")
                .delayToConfirm(5)
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        SmartToast.success("开启成功");
                    }
                })
                .show();
    }

    private void onEnsureDialogClicked() {
        EnsureDialog.create(this, "ensure")
                .message("确定不再关注此人？")
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        SmartToast.success("取消关注成功");
                    }
                })
                .show();
    }

    private void onNotificationDialogClicked() {
        NotificationDialog.create(this, "notify")
                .message("重置成功")
                .show();
    }
}
