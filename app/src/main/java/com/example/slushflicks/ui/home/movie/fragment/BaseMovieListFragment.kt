package com.example.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slushflicks.R
import com.example.slushflicks.databinding.FragmentHomeListBinding
import com.example.slushflicks.databinding.FragmentMovieListBinding
import com.example.slushflicks.ui.base.BaseFragment
import com.example.slushflicks.ui.home.adapter.MovieListAdapter
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel

class BaseMovieListFragment : BaseFragment<FragmentMovieListBinding, MovieViewModel>(R.layout.fragment_movie_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        binding.rvMovieList.adapter = MovieListAdapter()
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }

    override fun getViewModelClass() = MovieViewModel::class.java


}