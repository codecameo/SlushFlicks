package com.sifat.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentMovieBinding
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.home.adapter.TypeTagListAdapter
import com.sifat.slushflicks.ui.home.movie.viewmodel.MovieViewModel

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