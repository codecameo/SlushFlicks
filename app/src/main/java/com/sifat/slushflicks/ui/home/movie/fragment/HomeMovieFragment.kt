package com.sifat.slushflicks.ui.home.movie.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentMovieBinding
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.BaseFragment
import com.sifat.slushflicks.ui.home.adapter.TypeTagListAdapter
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.ui.home.adapter.viewholder.TypeTagViewModel
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieHomeDataAction
import com.sifat.slushflicks.ui.home.movie.state.event.MovieHomeEventState
import com.sifat.slushflicks.ui.home.movie.state.event.MovieHomeEventState.MovieCollectionClickEvent
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieHomeViewAction.CollectionContainerUpdateViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieHomeViewAction.MovieCollectionViewAction
import com.sifat.slushflicks.ui.home.movie.viewmodel.MovieViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.Label

class HomeMovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModel>(R.layout.fragment_movie),
    TypeTagViewModel.OnCollectionClickListener {

    private lateinit var adapter: TypeTagListAdapter

    override fun getViewModelClass(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchData()
    }

    private fun initListener() {
        adapter.onCollectionClickListener = this
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
        viewModel.handleEvent(MovieHomeEventState.MovieCollectionEvent())
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is MovieCollectionViewAction -> {
                    setCollectionList(action)
                }
                is CollectionContainerUpdateViewAction -> {
                    setContainerFragment(action)
                }
            }
        })

        viewModel.observeDataAction().observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is MovieHomeDataAction.MovieCollectionDataAction -> {
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    private fun setContainerFragment(action: CollectionContainerUpdateViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success -> {
                loadContent(viewState.data)
            }
        }
    }

    private fun setCollectionList(action: MovieCollectionViewAction) {
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
        binding.rvTypeTag.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }

    private fun getCollectionFragment(label: String?): Fragment {
        return when (label) {
            Label.TRENDING_LABEL -> {
                childFragmentManager.findFragmentByTag(label) ?: TrendingFragment()
            }
            Label.UPCOMING_LABEL -> {
                childFragmentManager.findFragmentByTag(label) ?: UpcomingFragment()
            }
            Label.TOP_RATED_LABEL -> {
                childFragmentManager.findFragmentByTag(label) ?: TopRatedFragment()
            }
            Label.NOW_PLAYING_LABEL -> {
                childFragmentManager.findFragmentByTag(label) ?: NowPlayingFragment()
            }
            Label.POPULAR_LABEL -> {
                childFragmentManager.findFragmentByTag(label) ?: PopularFragment()
            }
            else -> {
                childFragmentManager.findFragmentByTag(label) ?: TrendingFragment()
            }
        }
    }

    override fun onCollectionClicked(index: Int, collectionModel: CollectionModel) {
        viewModel.handleEvent(MovieCollectionClickEvent(index, collectionModel))
    }

    private fun getFragmentLabel(label: String) = label + Label.MOVIE_LABEL

    override fun onDestroyView() {
        viewModel.observeDataAction().removeObservers(viewLifecycleOwner)
        viewModel.observeViewAction().removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}