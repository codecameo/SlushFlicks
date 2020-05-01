package com.sifat.slushflicks.ui.binding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.utils.BULLET_SIGN
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.NA
import com.sifat.slushflicks.utils.SPACE
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter(value = ["voteAvg", "voteCount"], requireAll = true)
fun TextView.setRating(voteAvg: Double?, voteCount: Int?) {
    if (voteCount != null && voteAvg != null) {
        text = "$voteAvg ($voteCount)"
    }
}

@BindingAdapter("runtime")
fun TextView.setRuntime(runtime: Int?) {
    runtime?.let { time ->
        val hour: Int = time / 60
        val min = time % 60
        text = "${hour}h ${min}min"
    }
}

//1999-03-30
@BindingAdapter("releaseDate")
fun TextView.setReleaseDate(releaseDate: String?) {
    releaseDate?.let { dateStr ->
        text = try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateStr)
            date?.let {
                val finalDate = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH).format(date)
                finalDate
            } ?: NA
        } catch (ex: Exception) {
            NA
        }
    }
}