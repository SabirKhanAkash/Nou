/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.akash.nou.R

class LoadingDialog {
    private var activity: Activity? = null
    private var builder: Dialog? = null
    private var fragment: Fragment? = null

    constructor(activity: Activity?) {
        this.activity = activity
    }

    constructor(fragment: Fragment?) {
        this.fragment = fragment
    }

    fun startLoading() {
        builder = Dialog(activity!!)
        builder!!.setContentView(R.layout.activity_loading)
        builder!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        builder!!.window!!.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        builder!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder!!.setCancelable(false)
        builder!!.show()
    }

    fun startFragmentLoading() {
        builder = Dialog(fragment!!.requireActivity())
        builder!!.setContentView(R.layout.activity_loading)
        builder!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        builder!!.window!!.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        builder!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder!!.setCancelable(false)
        builder!!.show()
    }

    fun dismissLoading() {
        builder?.dismiss()
    }
}