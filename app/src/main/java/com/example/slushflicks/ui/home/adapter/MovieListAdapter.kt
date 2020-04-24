package com.example.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.slushflicks.R
import com.example.slushflicks.databinding.ItemMovieBinding
import com.example.slushflicks.ui.home.adapter.viewholder.MovieViewHolder

class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder((binding))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }

}