package com.vhontar.libs.connectionmonitor

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

private const val TAG = "ConnectivityMonitor"

class ConnectivityMonitor(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    private val callback: (Boolean) -> Unit
) : DefaultLifecycleObserver {
    private var connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private val pingMonitor = PingMonitor()

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
        .build()

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        toggleConnectionState(pingMonitor.isNetworkConnected)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun toggleConnectionState(isConnected: Boolean) {
        when {
            !isConnected -> {
                callback.invoke(false)
                wasOfflineBefore = true
            }
            wasOfflineBefore -> {
                callback.invoke(true)
                wasOfflineBefore = false
            }
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            Log.d(TAG, "onCapabilitiesChanged")
            lastInternetConnectionCheck(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            Log.d(TAG, "OnUnavailable")
            lastInternetConnectionCheck()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(TAG, "onLost")
            lastInternetConnectionCheck()
        }

        // onCapabilitiesChanged sometimes runs too many times, and we want to check only positive results
        // onUnavailable, onLost -> will cover cases of losing network
        private fun lastInternetConnectionCheck(onlyOnline: Boolean = false) {
            connectivityManager.allNetworks.forEach { network ->
                network?.let {
                    connectivityManager.getNetworkCapabilities(it)?.let { networkCapabilities ->
                        val netInternet =
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        val netValidated =
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

                        val isConnected = netInternet && netValidated

                        if (!isConnected && onlyOnline)
                            return

                        toggleConnectionState(isConnected)
                    }
                }
            }

            if (connectivityManager.allNetworks.isEmpty() && !onlyOnline)
                toggleConnectionState(false)
        }
    }

    companion object {
        private var wasOfflineBefore: Boolean = false
    }
}