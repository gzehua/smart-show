package com.coder.zzq.smart_dialog.demo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.smartshow.dialog.loading.LoadingDialog;
import com.coder.zzq.smartshow.dialog.loading.LoadingDialogProvider;
import com.coder.zzq.smartshow.dialog.util.ViewHolder;

public class TestLoadingDialogActivity extends AppCompatActivity {
    private ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_loading_dialog);
        mViewHolder = new ViewHolder(getWindow().getDecorView());
    }

    public void onShowClicked(View view) {
        LoadingDialog.create(this, "loading")
                .message(mViewHolder.getCheckBox(R.id.checkbox_with_msg).isChecked() ? "正在加载" : "")
                .boxSize(mViewHolder.getRadioButton(R.id.radio_button_large_box).isChecked() ? LoadingDialogProvider.BOX_SIZE_LARGE : (mViewHolder.getRadioButton(R.id.radio_button_middle_box).isChecked() ? LoadingDialogProvider.BOX_SIZE_MIDDLE : LoadingDialogProvider.BOX_SIZE_SMALL))
                .show();
    }
}