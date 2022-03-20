package com.coder.vincent.smart_snackbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.coder.vincent.series.common_lib.actionBarHeight
import com.coder.vincent.series.common_lib.statusBarHeight
import com.coder.vincent.smart_snackbar.schedule.SnackBarScheduler
object SmartSnackBar {
    @JvmStatic
    fun top(activity: AppCompatActivity): SnackBarFacade.Overall =
        (activity.window.decorView as ViewGroup).let {
            var container =
                it.findViewById<ViewGroup>(R.id.top_snack_bar_container_id)
            if (container == null) {
                container = LayoutInflater.from(it.context)
                    .inflate(R.layout.top_snackbar_container, it, false) as ViewGroup
                val height = statusBarHeight() + actionBarHeight()
                val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
                ViewCompat.setElevation(container, 21f)
                it.addView(container, lp)
            }
            SnackBarInvoker(container, SNACK_BAR_POSITION_TOP)
        }

    @JvmStatic
    fun bottom(coordinatorLayout: CoordinatorLayout): SnackBarFacade.Overall =
        SnackBarInvoker(coordinatorLayout, SNACK_BAR_POSITION_BOTTOM)

    @JvmStatic
    fun bottom(activity: AppCompatActivity): SnackBarFacade.Overall =
        SnackBarInvoker(activity.findViewById(android.R.id.content), SNACK_BAR_POSITION_BOTTOM)

    @JvmStatic
    fun dismiss() {
        SnackBarScheduler.dismiss()
    }

    @JvmStatic
    fun dismissTop() {
        SnackBarScheduler.dismissTop()
    }

    @JvmStatic
    fun dismissBottom() {
        SnackBarScheduler.dismissBottom()
    }
}