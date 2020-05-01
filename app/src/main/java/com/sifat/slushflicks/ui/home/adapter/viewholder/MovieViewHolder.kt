package com.sifat.slushflicks.ui.home.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemMovieBinding
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onMovieClickListener: OnMovieClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    fun bindTo(model: MovieModelMinimal, state: ListViewState) {
        binding.model = model
        binding.state = state
    }

    override fun onClick(view: View?) {
        binding.model?.let { model ->
            onMovieClickListener.onMovieClicked(model)
        }
    }

    interface OnMovieClickListener {
        fun onMovieClicked(model: MovieModelMinimal)
    }
}