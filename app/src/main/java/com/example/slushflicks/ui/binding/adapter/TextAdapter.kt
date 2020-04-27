package com.example.slushflicks.ui.binding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.slushflicks.R
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.utils.BULLET_SIGN
import com.example.slushflicks.utils.EMPTY_STRING
import com.example.slushflicks.utils.SPACE
import java.lang.StringBuilder

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