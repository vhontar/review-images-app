package com.vhontar.reviewimagesapp.view.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.vhontar.reviewimagesapp.R

fun Fragment.showNoInternetSnackbar() {
    Snackbar.make(requireView(), R.string.no_internet_connection, Snackbar.LENGTH_LONG).show()
}

fun Fragment.showInternetConnectedSnackbar(retry: (View) -> Unit) {
    Snackbar.make(requireView(), R.string.internet_connected, Snackbar.LENGTH_LONG)
        .setAction(R.string.retry, retry)
        .show()
}