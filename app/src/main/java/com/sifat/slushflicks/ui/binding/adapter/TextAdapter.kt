package com.sifat.slushflicks.ui.binding.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sifat.slushflicks.R
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
        val voteCountStr = if (voteCount == 0) NA else voteCount.toString()
        text = "$voteAvg ($voteCountStr)"
    }
}

@BindingAdapter(value = ["seasonCount", "episodeCount"], requireAll = true)
fun TextView.setSeasonEpisode(seasonCount: Int?, episodeCount: Int?) {
    val builder = StringBuilder(context.getString(R.string.seasons))
        .append(SPACE)
        .append(seasonCount)
        .append(SPACE)
        .append(BULLET_SIGN)
        .append(SPACE)
        .append(context.getString(R.string.episodes))
        .append(SPACE)
        .append(episodeCount)
    text = builder.toString()
}

@BindingAdapter("runtime")
fun TextView.setRuntime(runtime: Int?) {
    runtime?.let { time ->
        text = if (time == 0) {
            NA
        } else {
            val hour: Int = time / 60
            val min = time % 60
            "${hour}h ${min}min"
        }
    }
}

@BindingAdapter("directedBy")
fun TextView.setRuntime(directors: String?) {
    val prefix = context.getString(R.string.text_directed_by)
    val content = prefix + SPACE + directors
    val spannable = SpannableString(content)
    val start = prefix.length + 1
    val end = content.length
    spannable.setSpan(
        ForegroundColorSpan(Color.WHITE),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannable.setSpan(
        RelativeSizeSpan(1.167f),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannable
}

//1999-03-30
@BindingAdapter("date")
fun TextView.setReleaseDate(releaseDate: String?) {
    text = releaseDate?.let { dateStr ->
        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateStr)
            date?.let {
                val finalDate = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH).format(date)
                finalDate
            } ?: NA
        } catch (ex: Exception) {
            NA
        }
    } ?: NA
}

@BindingAdapter("episode")
fun TextView.setEpisode(episodeCount: Int) {
    val rem = episodeCount % 10
    text = when (rem) {
        1 -> {
            String.format(context.getString(R.string.text_st), episodeCount)
        }
        2 -> {
            String.format(context.getString(R.string.text_nd), episodeCount)
        }
        else -> {
            String.format(context.getString(R.string.text_th), episodeCount)
        }
    }
}


@BindingAdapter("rating")
fun TextView.setRating(rating: Double) {
    text = String.format("%.1f", rating)
}