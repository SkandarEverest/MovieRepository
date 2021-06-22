package com.example.madefikr.core.util.extension

import com.example.madefikr.core.R
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: Any?) {

    Glide.with(this)
        .load(image)
        .error(R.drawable.ic_error_placeholder)
        .into(this)

}