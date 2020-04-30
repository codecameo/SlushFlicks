package com.sifat.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemMovieBinding
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.diffutils.MovieDiffUtils
import com.sifat.slushflicks.ui.home.adapter.viewholder.MovieViewHolder

class MovieListAdapter : PagedListAdapter<MovieModelMinimal, MovieViewHolder>(MovieDiffUtils()) {

    var loadingState = ListViewState.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder((binding))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bindTo(
                model = movie,
                state = loadingState
            )
        }
    }
}