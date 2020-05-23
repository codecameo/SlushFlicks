package com.sifat.slushflicks.ui.home.tvshow.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentTvshowBinding
import com.sifat.slushflicks.di.constant.NAME_CONTENT_FACTORY
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.home.adapter.TypeTagListAdapter
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.ui.home.adapter.viewholder.TypeTagViewModel
import com.sifat.slushflicks.ui.home.tvshow.state.event.TvHomeEventState
import com.sifat.slushflicks.ui.home.tvshow.state.viewaction.TvHomeViewAction
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TvShowViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.Label
import javax.inject.Inject
import javax.inject.Named

class HomeTvFragment :
    BaseFragment<FragmentTvshowBinding, TvShowViewModel>(R.layout.fragment_tvshow),
    TypeTagViewModel.OnCollectionClickListener {

    @Inject
    @field:Named(NAME_CONTENT_FACTORY)
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: TypeTagListAdapter

    override fun getViewModelClass(): Class<TvShowViewModel> = TvShowViewModel::class.java
    override fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchData()
    }

    private fun loadContent(label: String?) {
        childFragmentManager.beginTransaction()
            .replace(
                binding.container.id,
                getCollectionFragment(label),
                label?.let { getFragmentLabel(it) } ?: getFragmentLabel(Label.DEFAULT_LABEL)
            )
            .commit()
    }

    private fun fetchData() {
        viewModel.handleEvent(TvHomeEventState.TvCollectionEvent())
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is TvHomeViewAction.TvCollectionViewAction -> {
                    setCollectionList(action)
                }
                is TvHomeViewAction.CollectionContainerUpdateViewAction -> {
                    setContainerFragment(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer {})
    }

    private fun setContainerFragment(action: TvHomeViewAction.CollectionContainerUpdateViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success -> {
                loadContent(viewState.data)
            }
        }
    }

    private fun setCollectionList(action: TvHomeViewAction.TvCollectionViewAction) {
        when (val viewDataState = action.viewState) {
            is ViewState.Loading<List<CollectionListModel>> -> {
                adapter.submitList(viewDataState.data)
            }
            is ViewState.Success<List<CollectionListModel>> -> {
                adapter.submitList(viewDataState.data)
            }
            is ViewState.Error<List<CollectionListModel>> -> {
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
        adapter = TypeTagListAdapter()
    }

    private fun setupList() {
        binding.rvTypeTag.adapter = adapter
        binding.rvTypeTag.itemAnimator = null
        binding.rvTypeTag.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initListener() {
        adapter.onCollectionClickListener = this
    }

    private fun getCollectionFragment(label: String?): Fragment {
        return when (label) {
            Label.TRENDING_LABEL -> {
                childFragmentManager.findFragmentByTag(getFragmentLabel(label))
                    ?: TrendingTvFragment()
            }
            Label.POPULAR_LABEL -> {
                childFragmentManager.findFragmentByTag(getFragmentLabel(label))
                    ?: PopularTvFragment()
            }
            Label.TOP_RATED_LABEL -> {
                childFragmentManager.findFragmentByTag(getFragmentLabel(label))
                    ?: TopRatedTvFragment()
            }
            Label.AIRING_TODAY -> {
                childFragmentManager.findFragmentByTag(getFragmentLabel(label))
                    ?: AirTodayTvFragment()
            }
            else -> {
                childFragmentManager.findFragmentByTag(getFragmentLabel(Label.DEFAULT_LABEL))
                    ?: TrendingTvFragment()
            }
        }
    }

    override fun onCollectionClicked(index: Int, collectionModel: CollectionModel) {
        viewModel.handleEvent(TvHomeEventState.TvCollectionClickEvent(index, collectionModel))
    }

    override fun onDestroyView() {
        viewModel.observeViewAction().removeObservers(viewLifecycleOwner)
        viewModel.observeDataAction().removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }

    private fun getFragmentLabel(label: String) = label + Label.TV_LABEL
}