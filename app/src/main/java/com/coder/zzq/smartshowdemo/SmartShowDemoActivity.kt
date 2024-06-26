package com.coder.zzq.smartshowdemo

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.coder.vincent.smart_dialog.click_list.ClickListAdapter
import com.coder.zzq.smartshowdemo.databinding.ActivitySmartShowDemoBinding


class SmartShowDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivitySmartShowDemoBinding.inflate(layoutInflater).apply {
            val adapter = ClickListAdapter()
            adapter.setItems(
                listOf(
                    "classic toast",
                    "emotion toast",
                    "snackbar",
                    "dialog"
                )
            )
            listView.adapter = adapter
            listView.onItemClickListener =
                OnItemClickListener { _, _, position, _ ->
                    when (position) {
                        0 -> onToastClick()
                        1 -> onTypeToastClick()
                        2 -> onSnackBarClick()
                        3 -> onDialogClick()
                    }
                }
        }.root)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SYSTEM_ALERT_WINDOW),0)
    }

    fun onToastClick() {
        startActivity(Intent(this, ClassicToastDemoActivity::class.java))
    }


    private fun onSnackBarClick() {
        startActivity(Intent(this, SnackbarDemoActivity::class.java))
    }

    fun onTypeToastClick() {
        startActivity(Intent(this, EmotionToastDemoActivity::class.java))
    }

    private fun onDialogClick() {
        startActivity(Intent(this, SmartDialogDemoActivity::class.java))
    }
}