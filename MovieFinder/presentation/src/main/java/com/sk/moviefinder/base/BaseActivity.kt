package com.sk.moviefinder.base

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.sk.moviefinder.common.goBack
import com.sk.moviefinder.common.gone
import com.sk.moviefinder.common.snackbar
import com.sk.moviefinder.common.visible
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

  fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)
  
  fun showError(errorMessage: String?, rootView: View) = snackbar(errorMessage ?: "", rootView)
  
  fun showLoading(progressBar: ProgressBar) = progressBar.visible()
  
  fun hideLoading(progressBar: ProgressBar) = progressBar.gone()

    override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount <= 1) finish() else goBack()
  }
}