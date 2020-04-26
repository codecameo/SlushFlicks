package com.example.slushflicks.ui.binding.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.slushflicks.R
import com.example.slushflicks.utils.EMPTY_STRING


@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun ImageView.setImageUrl(
    url: String?,
    placeHolder: Drawable?
) {

    Glide.with(context)
        .load(url ?: EMPTY_STRING)
        .placeholder(placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder))
        .error(placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder))
        .dontAnimate()
        .centerCrop()
        .into(this)
}