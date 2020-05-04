package com.sifat.slushflicks.ui.details.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemRelatedMovieBinding
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel

class MovieViewHolder(
    private val binding: ItemRelatedMovieBinding,
    private val onMovieClickListener: OnMovieClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    fun bindTo(model: MovieListModel) {
        binding.model = model.data
        binding.state = model.state
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movieModelMinimal: MovieModelMinimal)
    }

    override fun onClick(view: View?) {
        binding.model?.run {
            onMovieClickListener.onMovieClicked(this)
        }
    }
}