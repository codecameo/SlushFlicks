package com.example.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.slushflicks.R
import com.example.slushflicks.ui.home.adapter.model.MovieListModel
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction.FetchMovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListEventState
import com.example.slushflicks.ui.home.movie.state.MovieListViewAction.FetchMovieListViewAction
import com.example.slushflicks.ui.home.movie.viewmodel.TrendingViewModel
import com.example.slushflicks.ui.state.ViewState
import com.example.slushflicks.utils.api.NetworkStateManager

class TrendingFragment : BaseMovieListFragment<TrendingViewModel>() {

    override fun getViewModelClass() = TrendingViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(MovieListEventState.FetchMovieListEvent())
        subscribeAction()
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is FetchMovieListViewAction -> {
                    setMovieList(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is FetchMovieListDataAction -> {
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    private fun setMovieList(action: FetchMovieListViewAction) {
        when (val viewDataState = action.viewState) {
            is ViewState.Loading<List<MovieListModel>> -> {
                adapter.submitList(viewDataState.data)
            }
            is ViewState.Success<List<MovieListModel>> -> {
                adapter.submitList(viewDataState.data)
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
}