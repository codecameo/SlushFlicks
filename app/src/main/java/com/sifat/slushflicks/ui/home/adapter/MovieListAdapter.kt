package com.sifat.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemMovieBinding
import com.sifat.slushflicks.ui.home.adapter.diffutils.MovieDiffUtils
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.home.adapter.viewholder.MovieViewHolder

class MovieListAdapter : ListAdapter<MovieListModel, MovieViewHolder>(MovieDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder((binding))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun submitList(list: List<MovieListModel>?) {
        super.submitList(list?.toMutableList())
    }
}