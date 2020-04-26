package com.example.slushflicks.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.slushflicks.databinding.ItemMovieBinding
import com.example.slushflicks.ui.home.adapter.model.MovieListModel

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(model: MovieListModel) {
        binding.model = model.data
        binding.state = model.state
    }
}