package com.example.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.example.slushflicks.R
import com.example.slushflicks.databinding.FragmentHomeListBinding
import com.example.slushflicks.ui.base.BaseFragment
import com.example.slushflicks.ui.home.adapter.TypeTagListAdapter
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel

class HomeMovieListFragment :
    BaseFragment<FragmentHomeListBinding, MovieViewModel>(R.layout.fragment_home_list) {
    override fun getViewModelClass(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(binding.container.id, BaseMovieListFragment())
            .commit()

        binding.rvTypeTag.adapter = TypeTagListAdapter()
        binding.rvTypeTag.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

    }
}