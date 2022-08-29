package com.vhontar.reviewimagesapp.view.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vhontar.libs.connectionmonitor.ConnectivityMonitor
import com.vhontar.reviewimagesapp.view.utils.showInternetConnectedSnackbar
import com.vhontar.reviewimagesapp.view.utils.showNoInternetSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // subscribe to internet monitoring to provide
        ConnectivityMonitor(requireContext(), viewLifecycleOwner, ::onInternetChangedStatus)
    }

    abstract fun retry()

    private fun onInternetChangedStatus(isConnected: Boolean) {
        if (!isConnected) {
            showNoInternetSnackbar()
        } else {
            showInternetConnectedSnackbar { retry() }
        }
    }
}