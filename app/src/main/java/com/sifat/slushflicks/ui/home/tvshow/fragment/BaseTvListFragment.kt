package com.sifat.slushflicks.ui.home.tvshow.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentTvListBinding
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.ShowListAdapter
import com.sifat.slushflicks.ui.home.adapter.viewholder.ShowViewHolder
import com.sifat.slushflicks.ui.home.tvshow.state.dataaction.TvListDataAction
import com.sifat.slushflicks.ui.home.tvshow.state.event.TvListEventState
import com.sifat.slushflicks.ui.home.tvshow.state.viewaction.TvListViewAction
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.BaseTvListViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.showToast

abstract class BaseTvListFragment<VM : BaseTvListViewModel> :
    BaseFragment<FragmentTvListBinding, VM>(R.layout.fragment_tv_list),
    ShowViewHolder.OnShowClickListener {

    protected lateinit var adapter: ShowListAdapter
    private var shouldForceUpdate = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchData()
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is TvListViewAction.FetchCacheTvListViewAction -> {
                    setMovieList(action)
                }
                is TvListViewAction.FetchNetworkTvListViewAction -> {
                    setMovieList(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is TvListDataAction.FetchCacheTvListDataAction -> {
                    viewModel.setDataAction(action)
                }
                is TvListDataAction.FetchNetworkTvListDataAction -> {
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    private fun setMovieList(actionNetwork: TvListViewAction.FetchNetworkTvListViewAction) {
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

    private fun setMovieList(actionCache: TvListViewAction.FetchCacheTvListViewAction) {
        when (val viewDataState = actionCache.viewState) {
            is ViewState.Loading<PagedList<ShowModelMinimal>> -> {
                adapter.loadingState = ListViewState.LOADING
            }
            is ViewState.Success<PagedList<ShowModelMinimal>> -> {
                adapter.loadingState = ListViewState.VIEW
                adapter.submitList(viewDataState.data)
                shouldForceUpdate = false
            }
            is ViewState.Error<PagedList<ShowModelMinimal>> -> {
                adapter.loadingState = ListViewState.VIEW
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

    private fun fetchData() {
        viewModel.handleEvent(TvListEventState.FetchTvListEvent(shouldForceUpdate))
    }

    private fun initVariable() {
        adapter = ShowListAdapter()
    }

    private fun initListener() {
        adapter.onShowClickListener = this
    }

    private fun setupList() {
        binding.rvTvList.adapter = adapter
        binding.rvTvList.layoutManager = LinearLayoutManager(context)
    }

    override fun onShowClicked(model: ShowModelMinimal) {
    }
}