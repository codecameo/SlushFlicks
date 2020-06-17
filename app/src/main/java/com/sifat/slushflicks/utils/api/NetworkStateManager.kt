package com.sifat.slushflicks.utils.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.annotation.RequiresApi


class NetworkStateManager(context: Context) {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var isOnline = false

    init {
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            val request = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isOnline = checkNetworkState(network)
                }
            }
            /**
             * This wont cause memory leak as
             * The app continues to receive callbacks until either the app exits or it calls unregisterNetworkCallback().
             * */
            connectivityManager.registerNetworkCallback(request, callback)
        }
    }

    fun isOnline(): Boolean {
        return if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            isOnline
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.let { networkInfo.isConnected } ?: false
        }
    }

    @RequiresApi(LOLLIPOP)
    fun checkNetworkState(activeNetwork: Network?): Boolean {
        val networkCapability = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapability?.let {
            networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } ?: false
    }
}