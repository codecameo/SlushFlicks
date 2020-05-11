package com.sifat.slushflicks.ui.home.tvshow.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentTvListBinding
import com.sifat.slushflicks.di.constant.NAME_SHOW_LIST_FACTORY
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.details.BaseDetailsActivity
import com.sifat.slushflicks.ui.details.TvDetailsActivity
import com.sifat.slushflicks.ui.home.adapter.ShowListAdapter
import com.sifat.slushflicks.ui.home.adapter.viewholder.ShowViewHolder
import com.sifat.slushflicks.ui.home.tvshow.state.dataaction.TvListDataAction
import com.sifat.slushflicks.ui.home.tvshow.state.event.TvListEventState
import com.sifat.slushflicks.ui.home.tvshow.state.viewaction.TvListViewAction
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.BaseTvListViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.showToast
import javax.inject.Inject
import javax.inject.Named

abstract class BaseTvListFragment<VM : BaseTvListViewModel> :
    BaseFragment<FragmentTvListBinding, VM>(R.layout.fragment_tv_list),
    ShowViewHolder.OnShowClickListener {

    @Inject
    @field:Named(NAME_SHOW_LIST_FACTORY)
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var adapter: ShowListAdapter
    private var shouldForceUpdate = false

    override fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

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
        val intent = Intent(context, TvDetailsActivity::class.java)
        intent.putExtra(BaseDetailsActivity.KEY_SHOW_ID, model.id)
        startActivity(intent)
    }
}