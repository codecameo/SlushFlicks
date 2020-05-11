package com.sifat.slushflicks.ui.home.movie.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentMovieListBinding
import com.sifat.slushflicks.di.constant.NAME_SHOW_LIST_FACTORY
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.base.ListViewState.LOADING
import com.sifat.slushflicks.ui.base.ListViewState.VIEW
import com.sifat.slushflicks.ui.details.BaseDetailsActivity
import com.sifat.slushflicks.ui.details.MovieDetailsActivity
import com.sifat.slushflicks.ui.home.adapter.ShowListAdapter
import com.sifat.slushflicks.ui.home.adapter.viewholder.ShowViewHolder
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction.FetchCacheMovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction.FetchNetworkMovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState.FetchMovieListEvent
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction.FetchCacheMovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction.FetchNetworkMovieListViewAction
import com.sifat.slushflicks.ui.home.movie.viewmodel.BaseMovieListViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.showToast
import javax.inject.Inject
import javax.inject.Named

abstract class BaseMovieListFragment<VM : BaseMovieListViewModel> :
    BaseFragment<FragmentMovieListBinding, VM>(R.layout.fragment_movie_list),
    ShowViewHolder.OnShowClickListener {

    @Inject
    @field:Named(NAME_SHOW_LIST_FACTORY)
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var adapter: ShowListAdapter
    private var shouldForceUpdate = true

    override fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchData()
    }

    private fun fetchData() {
        viewModel.handleEvent(FetchMovieListEvent(shouldForceUpdate))
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is FetchCacheMovieListViewAction -> {
                    setMovieList(action)
                }
                is FetchNetworkMovieListViewAction -> {
                    // This only show network error message
                    setMovieList(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is FetchCacheMovieListDataAction -> {
                    viewModel.setDataAction(action)
                }
                is FetchNetworkMovieListDataAction -> {
                    // This only set the information of current and total page
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    override fun onShowClicked(model: ShowModelMinimal) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(BaseDetailsActivity.KEY_SHOW_ID, model.id)
        startActivity(intent)
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
                context?.showToast(message)
            }
        }
    }

    private fun setMovieList(actionCache: FetchCacheMovieListViewAction) {
        when (val viewDataState = actionCache.viewState) {
            is ViewState.Loading<PagedList<ShowModelMinimal>> -> {
                adapter.loadingState = LOADING
            }
            is ViewState.Success<PagedList<ShowModelMinimal>> -> {
                adapter.loadingState = VIEW
                adapter.submitList(viewDataState.data)
                shouldForceUpdate = false
            }
            is ViewState.Error<PagedList<ShowModelMinimal>> -> {
                adapter.loadingState = VIEW
                val message = if (viewDataState.errorMessage.isNullOrEmpty()) {
                    // Default error message has been set here to support localization
                    getString(R.string.error_message)
                } else {
                    viewDataState.errorMessage
                }
                //TODO should user [ErrorAction] to display error message in proper way
                context?.showToast(message)
            }
        }
    }

    private fun initVariable() {
        adapter = ShowListAdapter()
    }

    private fun initListener() {
        adapter.onShowClickListener = this
    }

    private fun setupList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        viewModel.observeViewAction().removeObservers(viewLifecycleOwner)
        viewModel.observeDataAction().removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}