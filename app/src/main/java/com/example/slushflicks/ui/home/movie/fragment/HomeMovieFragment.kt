package com.example.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.slushflicks.R
import com.example.slushflicks.databinding.FragmentMovieBinding
import com.example.slushflicks.ui.base.BaseFragment
import com.example.slushflicks.ui.home.adapter.TypeTagListAdapter
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel

class HomeMovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModel>(R.layout.fragment_movie) {
    override fun getViewModelClass(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(binding.container.id, TopRatedFragment())
            .commit()

        binding.rvTypeTag.adapter = TypeTagListAdapter()
        binding.rvTypeTag.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

    }
}