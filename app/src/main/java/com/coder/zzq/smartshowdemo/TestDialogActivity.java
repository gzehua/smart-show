package com.coder.zzq.smartshowdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.smartshow.dialog.base.ISmartDialog;
import com.coder.zzq.smartshow.dialog.delegate.footer.OnDialogBtnClickedListener;
import com.coder.zzq.smartshow.dialog.input.InputCheckCallback;
import com.coder.zzq.smartshow.dialog.input.InputNumberDialog;
import com.coder.zzq.smartshow.dialog.input.InputTextDialog;
import com.coder.zzq.smartshow.dialog.list.ChooseListDialog;
import com.coder.zzq.smartshow.dialog.list.ChooseListDialogProvider;
import com.coder.zzq.smartshow.dialog.list.ChooseResult;
import com.coder.zzq.smartshow.dialog.list.ClickListDialog;
import com.coder.zzq.smartshow.dialog.list.ClickListDialogProvider;
import com.coder.zzq.smartshow.dialog.loading.LoadingDialog;
import com.coder.zzq.smartshow.dialog.loading.LoadingDialogProvider;
import com.coder.zzq.smartshow.dialog.message.EnsureDialog;
import com.coder.zzq.smartshow.dialog.message.NotificationDialog;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.coder.zzq.toolkit.Utils;

import java.util.Arrays;

public class TestDialogActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
        mListView = findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                onShowNotificationDialog();
                break;
            case 1:
                onShowEnsureDialog();
                break;
            case 2:
                onShowEnsureDelayDialog();
                break;
            case 3:
                onShowInputTextDialog();
                break;

            case 4:
                onShowInputNumDialog();
                break;
            case 5:
                onShowClickListDialog();
                break;
            case 6:
                onShowSingleChooseDialog();
                break;
            case 7:
                onShowMultipleChooseDialog();
                break;
            case 8:
                onShowLargeLoading();
                break;
            case 9:
                onShowMiddleLoading();
                break;
            case 10:
                onShowSmallLoading();
                break;
        }
    }

    private void onShowNotificationDialog() {
        NotificationDialog.create(this, "notify reset pwd result")
                .message("重置成功")
                .show();
    }

    private void onShowEnsureDialog() {
        EnsureDialog.create(this, "not follow someone")
                .message("确定不在关注此人?")
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        SmartToast.success("取消关注成功");
                    }
                })
                .show();
    }

    private void onShowEnsureDelayDialog() {
        EnsureDialog.create(this, "enable develop mode")
                .message("确定启用开发者模式？")
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

    private void onShowInputTextDialog() {
        InputTextDialog.create(this, "input suggestions")
                .title("输入")
                .hint("您的宝贵建议")
                .atMostInput(50)
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        SmartToast.show(data.toString());
                    }
                })
                .show();
    }

    private void onShowInputNumDialog() {
        InputNumberDialog.create(this, "input goods weight")
                .resetWhenShowAgain(true)
                .title("输入货物重量")
                .numberUnit("千克")
                .initialFilledNumber("1")
                .inputCheckCallback(new InputCheckCallback() {
                    @Override
                    public boolean isValid(String input) {
                        if (Utils.isEmpty(input)) {
                            return false;
                        }
                        int value = Integer.parseInt(input);
                        return value > 0 && value < 50;
                    }

                    @Override
                    public String errorInfoWhenInputInvalid() {
                        return "重量必须满足0~50之间，不包含边界";
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

    private void onShowClickListDialog() {
        ClickListDialog.create(this, "click list")
                .items(Arrays.asList("回复", "转发", "私信回复", "复制", "举报"))
                .onItemClickListener(new ClickListDialogProvider.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(ISmartDialog smartDialog, int pos, Object data) {
                        smartDialog.dismiss();
                        SmartToast.show(data.toString());
                    }
                })
                .show();
    }

    private void onShowSingleChooseDialog() {
        ChooseListDialog.create(this, "choose favorite cities")
                .resetWhenShowAgain(true)
                .title("你喜欢哪个城市")
                .items("上海", "北京", "广州", "深圳", "杭州", "青岛", "苏州")
                .defaultChosenItems(0)
                .choiceMode(ChooseListDialogProvider.CHOICE_MODE_SINGLE)
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        ChooseResult chooseResult = (ChooseResult) data;
                        if (chooseResult.isAnyItemChosen()) {
                            SmartToast.show(String.format("pos:%s\nitems:%s", Arrays.toString(chooseResult.getChoosePositions()), Arrays.toString(chooseResult.getChooseItems())));
                        }
                    }
                })
                .show();
    }

    private void onShowMultipleChooseDialog() {
        ChooseListDialog.create(this, "choose favorite cities")
                .resetWhenShowAgain(true)
                .title("你喜欢哪个城市")
                .items("上海", "北京", "广州", "深圳", "杭州", "青岛", "苏州")
                .defaultChosenItems(0,1)
                .choiceMode(ChooseListDialogProvider.CHOICE_MODE_MULTIPLE)
                .onConfirmBtnClickedListener(new OnDialogBtnClickedListener() {
                    @Override
                    public void onDialogBtnClicked(ISmartDialog smartDialog, Object data) {
                        smartDialog.dismiss();
                        ChooseResult chooseResult = (ChooseResult) data;
                        if (chooseResult.isAnyItemChosen()) {
                            SmartToast.show(String.format("pos:%s\nitems:%s", Arrays.toString(chooseResult.getChoosePositions()), Arrays.toString(chooseResult.getChooseItems())));
                        }
                    }
                })
                .show();
    }

    private void onShowLargeLoading() {
        LoadingDialog.create(this, "loading")
                .boxSize(LoadingDialogProvider.BOX_SIZE_LARGE)
                .message("正在加载")
                .show();
    }

    private void onShowMiddleLoading() {
        LoadingDialog.create(this, "loading")
                .boxSize(LoadingDialogProvider.BOX_SIZE_MIDDLE)
                .message("正在加载")
                .show();
    }

    private void onShowSmallLoading() {
        LoadingDialog.create(this, "loading")
                .boxSize(LoadingDialogProvider.BOX_SIZE_SMALL)
                .show();
    }


}
