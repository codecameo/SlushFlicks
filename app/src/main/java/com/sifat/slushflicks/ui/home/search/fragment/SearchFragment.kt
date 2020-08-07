package com.sifat.slushflicks.ui.home.search.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentSearchBinding
import com.sifat.slushflicks.di.constant.NAME_CONTENT_FACTORY
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.details.BaseDetailsActivity
import com.sifat.slushflicks.ui.details.MovieDetailsActivity
import com.sifat.slushflicks.ui.details.TvDetailsActivity
import com.sifat.slushflicks.ui.home.adapter.ShowListAdapter
import com.sifat.slushflicks.ui.home.adapter.viewholder.ShowViewHolder
import com.sifat.slushflicks.ui.home.search.state.event.SearchEventState.*
import com.sifat.slushflicks.ui.home.search.state.viewaction.SearchViewAction.*
import com.sifat.slushflicks.ui.home.search.viewmodel.SearchViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.ui.widget.FilterWidget
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.ShowType
import com.sifat.slushflicks.utils.hideKeyboard
import javax.inject.Inject
import javax.inject.Named

class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search),
    ShowViewHolder.OnShowClickListener, View.OnClickListener {

    @Inject
    @field:Named(NAME_CONTENT_FACTORY)
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var showListAdapter: ShowListAdapter
    private val searchDelay = 600L
    private var showType = ShowType.MOVIE

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
    override fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchInitialData()
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is SearchShowViewAction -> {
                    setSearchList(action)
                }
                is UpdateInitialViewAction -> {
                    setInitialData(action)
                }
                is UpdateShowTypeViewAction -> {
                    setShowType(action)
                }
                is ResultFoundViewAction -> {
                    showResult(action)
                }
            }
        })
    }

    private fun fetchInitialData() {
        viewModel.handleEvent(SetInitialEvent())
    }

    private fun setShowType(action: UpdateShowTypeViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success<ShowType> -> {
                viewState.data?.run {
                    showType = this
                    searchShow()
                }
            }
        }
    }

    private fun setInitialData(action: UpdateInitialViewAction) {
        searchShow()
        when (val viewState = action.viewState) {
            is ViewState.Success<SearchViewModel.InitialData> -> {
                viewState.data?.let { init ->
                    binding.model = init.queryModel
                    showType = init.showType
                }
            }
        }
    }

    private fun setSearchList(action: SearchShowViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success<PagedList<ShowModelMinimal>> -> {
                showListAdapter.loadingState = ListViewState.VIEW
                showListAdapter.submitList(viewState.data)
            }
            is ViewState.Loading<PagedList<ShowModelMinimal>> -> {
                showLoading()
            }
        }
    }

    private fun searchShow() {
        viewModel.handleEvent(ShowSearchEvent())
    }

    override fun onShowClicked(model: ShowModelMinimal) {
        val intent = when (showType) {
            ShowType.MOVIE -> {
                Intent(context, MovieDetailsActivity::class.java)
            }
            ShowType.TV_SERIES -> {
                Intent(context, TvDetailsActivity::class.java)
            }
        }
        intent.putExtra(BaseDetailsActivity.KEY_SHOW_ID, model.id)
        context?.startActivity(intent)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.fbFilter.id -> {
                showFilter()
            }
            binding.ivClearSearch.id -> {
                binding.etSearch.setText(EMPTY_STRING)
            }
        }
    }

    private fun showFilter() {
        context?.let {
            val filterView = FilterWidget(it)
            filterView.setInitShowType(showType)
            val bottomSheetDialog = BottomSheetDialog(it)
            bottomSheetDialog.setContentView(filterView)
            filterView.completeListener = object : FilterWidget.OnCompleteListener {
                override fun onComplete(showType: ShowType) {
                    bottomSheetDialog.dismiss()
                    viewModel.handleEvent(UpdateShowTypeEvent(showType))
                }
            }
            bottomSheetDialog.setOnShowListener { dialog ->
                val container =
                    (dialog as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                container?.setBackgroundResource(android.R.color.transparent)
            }
            bottomSheetDialog.show()
        }
    }

    private fun initListener() {
        binding.fbFilter.setOnClickListener(this)
        binding.ivClearSearch.setOnClickListener(this)
        binding.rvSearch.addOnScrollListener(listScrollListener)
        showListAdapter.onShowClickListener = this
        binding.etSearch.addTextChangedListener(watcher)
    }

    private fun setupList() {
        binding.rvSearch.adapter = showListAdapter
    }

    private fun initVariable() {
        showListAdapter = ShowListAdapter()
    }

    private fun showLoading() {
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer.startShimmer()
        binding.rvSearch.visibility = View.INVISIBLE
        binding.tvNoResult.visibility = View.GONE
    }

    private fun showResult(action: ResultFoundViewAction) {
        binding.shimmer.hideShimmer()
        binding.shimmer.visibility = View.INVISIBLE
        when (val viewState = action.viewState) {
            is ViewState.Success<Boolean> -> {
                setResultView(viewState.data)
            }
        }
    }

    private fun setResultView(data: Boolean?) {
        data?.let { isFound ->
            if (isFound) {
                binding.rvSearch.visibility = View.VISIBLE
            } else {
                binding.tvNoResult.text = getNoResultText()
                binding.tvNoResult.visibility = View.VISIBLE
            }
        }
    }

    private fun getNoResultText(): String {
        return binding.model?.run {
            if (TextUtils.isEmpty(query)) {
                String.format(
                    getString(R.string.text_no_recent_result_found),
                    showType.value
                )
            } else {
                String.format(
                    getString(R.string.text_no_result_found),
                    showType.value,
                    query
                )
            }
        } ?: getString(R.string.text_not_found)
    }

    private val searchShow = Runnable {
        searchShow()
    }
    private val handler: Handler by lazy {
        Handler()
    }
    private val watcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            handler.removeCallbacks(searchShow)
            handler.postDelayed(searchShow, searchDelay)
        }

        // Empty implementation
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        // Empty implementation
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private val listScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                binding.fbFilter.show()
            } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                hideKeyboard()
            }
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0 || dy < 0 && binding.fbFilter.isShown) {
                binding.fbFilter.hide()
            }
        }
    }
}