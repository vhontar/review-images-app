package com.vhontar.reviewimagesapp.view.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.vhontar.reviewimagesapp.R

const val OVERRIDE_SIZE_HIGHER_RESOLUTION = 700
const val OVERRIDE_SIZE_LOWER_RESOLUTION = 500

fun ImageView.loadUrl(
    imageUrl: String,
    isRounded: Boolean = false,
    makeResize: Boolean = false
) {
    val requestOptions = RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)

    var requestBuilder = Glide.with(context)
        .load(imageUrl)
        .dontAnimate()
        .apply(requestOptions)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    requestBuilder = requestBuilder.placeholder(ContextCompat.getDrawable(context, R.drawable.ic_loading_animation))
    requestBuilder = requestBuilder.error(ContextCompat.getDrawable(context, R.drawable.ic_broken_image))

    if (isRounded) {
        requestBuilder = requestBuilder.circleCrop()
    }

    if (makeResize) {
        val resolution = if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.N_MR1)
            OVERRIDE_SIZE_LOWER_RESOLUTION
        else
            OVERRIDE_SIZE_HIGHER_RESOLUTION

        requestBuilder
            .override(resolution)
            .into(this)
    } else {
        requestBuilder.into(this)
    }
}