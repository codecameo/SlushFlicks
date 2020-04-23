package com.example.slushflicks.ui.home.fragment

import com.example.slushflicks.R
import com.example.slushflicks.databinding.FragmentHomeListBinding
import com.example.slushflicks.ui.base.BaseFragment
import com.example.slushflicks.ui.home.viewmodel.MovieViewModel

class HomeMovieListFragment :
    BaseFragment<FragmentHomeListBinding, MovieViewModel>(R.layout.fragment_home_list) {
    override fun getViewModelClass(): Class<MovieViewModel> = MovieViewModel::class.java
}