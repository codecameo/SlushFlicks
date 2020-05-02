package com.sifat.slushflicks.ui.base

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


abstract class FullScreenActivity<DB : ViewDataBinding, VM : ViewModel> : BaseActivity<DB, VM>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
    }

    private fun setTransparentStatusBar() {
        if (SDK_INT in 19..20) {
            setWindowFlag(FLAG_TRANSLUCENT_STATUS, true)
        }
        if (SDK_INT >= 19) {
            this.window.decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (SDK_INT >= 21) {
            setWindowFlag(FLAG_TRANSLUCENT_STATUS, false)
            this.window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        }
    }

    private fun setWindowFlag(flag: Int, on: Boolean) {
        val win: Window = window
        val winParams: WindowManager.LayoutParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or flag
        } else {
            winParams.flags = winParams.flags and flag.inv()
        }
        win.attributes = winParams
    }
}