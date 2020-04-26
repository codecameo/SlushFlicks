package com.example.slushflicks.utils.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi


class NetworkStateManager(private val context: Context) {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isOnline(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkNetworkState(connectivityManager.activeNetwork)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.let { networkInfo.isConnected } ?: false
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun checkNetworkState(activeNetwork: Network?): Boolean {
        val networkCapability = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapability?.let {
            networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } ?: false
    }
}