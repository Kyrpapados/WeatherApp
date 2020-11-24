package com.kyrpapados.weatherapp.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.kyrpapados.weatherapp.util.misc.LoadingLiveData
import com.kyrpapados.weatherapp.R
import com.kyrpapados.weatherapp.util.misc.ErrorData
import com.kyrpapados.weatherapp.util.misc.NoInternetData

abstract class BaseActivity: AppCompatActivity() {

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        LoadingLiveData.observe(this, {
            it.getContentIfNotHandled()?.let { shouldShow ->
                if (shouldShow){
                    disableUserInteraction()
                } else {
                    enableUserInteraction()
                }
            }
        })

        NoInternetData.observe(this, {
            it.getContentIfNotHandled()?.let { shouldShow ->
                if (shouldShow){
                    showInternetDialog()
                }
            }
        })

        ErrorData.observe(this, {
            it.getContentIfNotHandled()?.let { shouldShow ->
                if (shouldShow){
                    showErrorDialog()
                }
            }
        })
    }

    private fun disableUserInteraction() {
        window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun enableUserInteraction() {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun showInternetDialog() {
        MaterialDialog(this)
            .title(R.string.no_internet)
            .positiveButton(R.string.ok)
            .show()
    }

    private fun showErrorDialog() {
        MaterialDialog(this)
            .title(R.string.try_again)
            .positiveButton(R.string.ok)
            .show()
    }

}