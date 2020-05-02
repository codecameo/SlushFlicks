package com.sifat.slushflicks.ui.details.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemRelatedMovieBinding
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel

class MovieViewHolder(private val binding: ItemRelatedMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(model: MovieListModel) {
        binding.model = model.data
        binding.state = model.state
    }
}