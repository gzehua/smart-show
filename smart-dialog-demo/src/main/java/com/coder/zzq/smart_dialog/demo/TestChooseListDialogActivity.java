package com.coder.zzq.smart_dialog.demo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.smartshow.dialog.list.ChooseListDialog;
import com.coder.zzq.smartshow.dialog.list.ChooseListDialogProvider;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;

public class TestChooseListDialogActivity extends AppCompatActivity {
    private ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_choose_list_dialog);
        mViewHolder = new ViewHolder(getWindow().getDecorView());
    }


    public void onShowClicked(View view) {
        ChooseListDialog.create(this, "choose cities")
                .defaultChosenItems(new int[]{0, 1})
                .items("上海", "北京", "广州", "深圳", "杭州", "青岛", "苏州")
                .resetWhenShowAgain(true)
                .checkMarkType(mViewHolder.getRadioButton(R.id.radio_button_circle_mark).isChecked() ? ChooseListDialogProvider.CHECK_MARK_TYPE_CIRCLE : ChooseListDialogProvider.CHECK_MARK_TYPE_CUBE)
                .checkMarkPos(mViewHolder.getRadioButton(R.id.radio_button_mark_pos_left).isChecked() ? ChooseListDialogProvider.CHECK_MARK_POS_LEFT : ChooseListDialogProvider.CHECK_MARK_POS_RIGHT)
                .choiceMode(mViewHolder.getRadioButton(R.id.radio_button_single_choice).isChecked() ? ChooseListDialogProvider.CHOICE_MODE_SINGLE : ChooseListDialogProvider.CHOICE_MODE_MULTIPLE)
                .show();
    }
}