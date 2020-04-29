package com.sifat.slushflicks.ui.home.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel

class MovieDiffUtils : DiffUtil.ItemCallback<MovieListModel>() {

    override fun areItemsTheSame(oldItem: MovieListModel, newItem: MovieListModel): Boolean {
        return oldItem.data?.id == newItem.data?.id
    }

    override fun areContentsTheSame(oldItem: MovieListModel, newItem: MovieListModel): Boolean {
        return oldItem.data == newItem.data
    }
}