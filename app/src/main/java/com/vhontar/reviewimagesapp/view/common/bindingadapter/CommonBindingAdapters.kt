package com.vhontar.reviewimagesapp.view.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.vhontar.reviewimagesapp.view.utils.loadUrl

@BindingAdapter("layoutHeight")
fun ImageView.setLayoutHeight(dimen: Float) {
    layoutParams.height = dimen.toInt()
}

@BindingAdapter(value = ["imageUrl", "circled", "makeResize"], requireAll = true)
fun ImageView.setImageUrl(url: String?, circled: Boolean?, makeResize: Boolean?) {
    if (url == null)
        return

    loadUrl(imageUrl = url, isRounded = circled ?: false, makeResize = makeResize ?: false)
}