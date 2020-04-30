package com.sifat.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentMovieListBinding
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.base.ListViewState.LOADING
import com.sifat.slushflicks.ui.base.ListViewState.VIEW
import com.sifat.slushflicks.ui.home.adapter.MovieListAdapter
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction.FetchCacheMovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction.FetchNetworkMovieListViewAction
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
                is FetchCacheMovieListViewAction -> {
                    setMovieList(action)
                }
                is FetchNetworkMovieListViewAction -> {
                    setMovieList(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is MovieListDataAction.FetchCacheMovieListDataAction -> {
                    viewModel.setDataAction(action)
                }
                is MovieListDataAction.FetchNetworkMovieListDataAction -> {
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    private fun setMovieList(actionNetwork: FetchNetworkMovieListViewAction) {
        when (val viewDataState = actionNetwork.viewState) {
            is ViewState.Error<Int> -> {
                val message = if (viewDataState.errorMessage.isNullOrEmpty()) {
                    // Default error message has been set here to support localization
                    getString(R.string.update_failed_error_message)
                } else {
                    viewDataState.errorMessage
                }
                //TODO should user [ErrorAction] to display error message in proper way
                showToast(message)
            }
        }
    }

    private fun setMovieList(actionCache: FetchCacheMovieListViewAction) {
        when (val viewDataState = actionCache.viewState) {
            is ViewState.Loading<PagedList<MovieModelMinimal>> -> {
                adapter.loadingState = LOADING
            }
            is ViewState.Success<PagedList<MovieModelMinimal>> -> {
                adapter.loadingState = VIEW
                adapter.submitList(viewDataState.data)
                shouldForceUpdate = false
            }
            is ViewState.Error<PagedList<MovieModelMinimal>> -> {
                adapter.loadingState = VIEW
                val message = if (viewDataState.errorMessage.isNullOrEmpty()) {
                    // Default error message has been set here to support localization
                    getString(R.string.error_message)
                } else {
                    viewDataState.errorMessage
                }
                //TODO should user [ErrorAction] to display error message in proper way
                showToast(message)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    private fun initVariable() {
        adapter = MovieListAdapter()
    }

    private fun setupList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }
}