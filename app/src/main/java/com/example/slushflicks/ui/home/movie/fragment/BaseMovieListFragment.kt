package com.example.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slushflicks.R
import com.example.slushflicks.databinding.FragmentMovieListBinding
import com.example.slushflicks.ui.base.BaseFragment
import com.example.slushflicks.ui.home.adapter.MovieListAdapter
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel
import com.example.slushflicks.ui.home.movie.viewmodel.TrendingViewModel

abstract class BaseMovieListFragment<VM : ViewModel> : BaseFragment<FragmentMovieListBinding, VM>(R.layout.fragment_movie_list) {

    protected lateinit var adapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        setupList()
    }

    private fun initVariable() {
        adapter = MovieListAdapter()
    }

    private fun setupList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }
}