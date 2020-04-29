package com.sifat.slushflicks.ui.binding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.utils.BULLET_SIGN
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.SPACE

@BindingAdapter("genres")
fun TextView.setGenres(genres: List<GenreModel>?) {
    val message = if (!genres.isNullOrEmpty()) {
        val builder = StringBuilder(genres[0].name)
        for (index in 1 until genres.size) {
            builder.append(SPACE)
                .append(BULLET_SIGN)
                .append(SPACE)
                .append(genres[index].name)
        }
        builder.toString()
    } else EMPTY_STRING
    text = message
}