package com.sifat.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentMovieListBinding
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.home.adapter.MovieListAdapter
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction
import com.sifat.slushflicks.ui.home.movie.viewmodel.BaseMovieListViewModel
import com.sifat.slushflicks.ui.state.ViewState

abstract class BaseMovieListFragment<VM : BaseMovieListViewModel> :
    BaseFragment<FragmentMovieListBinding, VM>(R.layout.fragment_movie_list) {

    protected lateinit var adapter: MovieListAdapter
    private var shouldForceUpdate = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        setupList()
        subscribeAction()
        fetchData()
    }

    private fun fetchData() {
        viewModel.handleEvent(MovieListEventState.FetchMovieListEvent(shouldForceUpdate))
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is MovieListViewAction.FetchMovieListViewAction -> {
                    setMovieList(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is MovieListDataAction.FetchMovieListDataAction -> {
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    private fun setMovieList(action: MovieListViewAction.FetchMovieListViewAction) {
        when (val viewDataState = action.viewState) {
            is ViewState.Loading<List<MovieListModel>> -> {
                adapter.submitList(viewDataState.data)
            }
            is ViewState.Success<List<MovieListModel>> -> {
                adapter.submitList(viewDataState.data)
                shouldForceUpdate = false
            }
            is ViewState.Error<List<MovieListModel>> -> {
                val message = if (viewDataState.errorMessage.isNullOrEmpty()) {
                    // Default error message has been set here to support localization
                    getString(R.string.error_message)
                } else {
                    viewDataState.errorMessage
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun initVariable() {
        adapter = MovieListAdapter()
    }

    private fun setupList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }
}