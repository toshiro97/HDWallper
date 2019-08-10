package com.toshiro.hdwallper.until

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.design.widget.Snackbar
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.toshiro.hdwallper.R
import java.text.DecimalFormat


class Common( var context: Context) {


    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val info = connectivityManager.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }
}