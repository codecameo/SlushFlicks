package com.sifat.slushflicks.ui.binding.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sifat.slushflicks.R
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.getListImageUrl
import com.sifat.slushflicks.utils.getSmallImageUrl

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

@BindingAdapter(value = ["imageSmallUrl", "placeholder"], requireAll = false)
fun ImageView.setImageSmallUrl(
    url: String?,
    placeHolder: Drawable?
) {
    val imageUrl = getSmallImageUrl(url)
    Glide.with(context)
        .load(imageUrl)
        .placeholder(placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder))
        .error(placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder))
        .dontAnimate()
        .centerCrop()
        .into(this)
}

@BindingAdapter(value = ["image", "placeholder"], requireAll = false)
fun ImageView.setImage(
    image: String?,
    placeHolder: Drawable?
) {
    val imageUrl = getListImageUrl(image)
    Glide.with(context)
        .load(imageUrl ?: EMPTY_STRING)
        .placeholder(placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder))
        .error(placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder))
        .dontAnimate()
        .centerCrop()
        .into(this)
}