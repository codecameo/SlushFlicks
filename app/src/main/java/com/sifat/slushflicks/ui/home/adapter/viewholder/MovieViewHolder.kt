package com.sifat.slushflicks.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemMovieBinding
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(model: MovieModelMinimal, state: ListViewState) {
        binding.model = model
        binding.state = state
    }
}