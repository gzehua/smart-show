package com.coder.zzq.smartshowdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.coder.zzq.smartshow.dialog.list.ClickListAdapter;
import com.coder.zzq.smartshow.toast.SmartToast;

import java.util.Arrays;

public class TestTypeToastActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_type_toast);
        mListView = findViewById(R.id.list_view);
        ClickListAdapter adapter = new ClickListAdapter();
        adapter.setItemCenter(true);
        adapter.setItems(Arrays.asList("info", "warning", "success", "error", "fail", "complete", "forbid", "waiting"));
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        onInfoClick(view);
                        break;
                    case 1:
                        onWarningClick(view);
                        break;
                    case 2:
                        onSuccessClick(view);
                        break;
                    case 3:
                        onErrorClick(view);
                        break;
                    case 4:
                        onFailClick(view);
                        break;
                    case 5:
                        onCompleteClick(view);
                        break;
                    case 6:
                        onForbidClick(view);
                        break;
                    case 7:
                        onWaitingClick(view);
                        break;
                }
            }
        });
    }

    public void onInfoClick(View view) {
        SmartToast.info(R.string.net_fine);
    }

    public void onSuccessClick(View view) {
        SmartToast.success(R.string.delete_succ);
    }

    public void onErrorClick(View view) {
        SmartToast.error(R.string.data_parse_error);
    }

    public void onWarningClick(View view) {
        SmartToast.warning(R.string.power_low);
    }

    public void onFailClick(View view) {
        SmartToast.fail(R.string.save_fail);
    }

    public void onCompleteClick(View view) {
        SmartToast.complete(R.string.download_complete);
    }


    public void onForbidClick(View view) {
        SmartToast.forbid(R.string.forbid_op);
    }

    public void onWaitingClick(View view) {
        SmartToast.waiting(R.string.wait_to_download);
    }


    public void onNextPageClick(View view) {
        startActivity(new Intent(this, TestTypeToastActivity.class));
    }


}
