package com.sifat.slushflicks.ui.home.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.model.MovieModelMinimal

class MovieDiffUtils : DiffUtil.ItemCallback<MovieModelMinimal>() {

    override fun areItemsTheSame(oldItem: MovieModelMinimal, newItem: MovieModelMinimal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieModelMinimal,
        newItem: MovieModelMinimal
    ): Boolean {
        return oldItem == newItem
    }
}