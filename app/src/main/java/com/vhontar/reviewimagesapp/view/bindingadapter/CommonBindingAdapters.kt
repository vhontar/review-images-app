package com.vhontar.reviewimagesapp.view.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.vhontar.reviewimagesapp.view.utils.loadUrl

@BindingAdapter("layoutHeight")
fun ImageView.setLayoutHeight(dimen: Float) {
    layoutParams.height = dimen.toInt()
}

@BindingAdapter(value = ["imageUrl", "circled"], requireAll = true)
fun ImageView.setImageUrl(url: String, circled: Boolean) {
    loadUrl(imageUrl = url, isRounded = circled)
}