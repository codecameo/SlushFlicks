package com.sifat.slushflicks.ui.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityTvDetailsBinding
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.ui.details.adapter.SeasonAdapter
import com.sifat.slushflicks.ui.details.adapter.viewholder.ShowViewHolder
import com.sifat.slushflicks.ui.details.state.dataaction.TvDetailDataAction
import com.sifat.slushflicks.ui.details.state.event.TvDetailsViewEvent.*
import com.sifat.slushflicks.ui.details.state.viewaction.TvDetailsViewAction.*
import com.sifat.slushflicks.ui.details.viewmodel.TvDetailsViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.INVALID_ID

class TvDetailsActivity : BaseDetailsActivity<ActivityTvDetailsBinding, TvDetailsViewModel>(),
    View.OnClickListener, ShowViewHolder.OnShowClickListener, AppBarLayout.OnOffsetChangedListener {
    override fun getLayoutRes() = R.layout.activity_tv_details

    override fun getViewModelClass() = TvDetailsViewModel::class.java
    private lateinit var seasonAdapter: SeasonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractData()
        setupToolbar(binding.toolbarDetails)
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchTvShowDetails()
    }

    private fun extractData() {
        val tvShowId = intent.getLongExtra(KEY_SHOW_ID, INVALID_ID.toLong())
        viewModel.setTvShowId(tvShowId)
    }

    override fun initVariable() {
        super.initVariable()
        seasonAdapter = SeasonAdapter()
    }

    private fun setupList() {
        binding.rvCast.adapter = castAdapter
        binding.rvRecommended.adapter = recommendedAdapter
        binding.rvSimilar.adapter = similarAdapter
        binding.rvReview.adapter = reviewAdapter
        binding.rvSeason.adapter = seasonAdapter
    }

    private fun initListener() {
        binding.ivPoster.setOnClickListener(this)
        binding.appBarPoster.addOnOffsetChangedListener(this)
        recommendedAdapter.onShowClickedListener = this
        similarAdapter.onShowClickedListener = this
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(this, Observer { action ->
            when (action) {
                is FetchTvDetailsViewAction -> {
                    showTvShowDetails(action)
                }
                is FetchRecommendedTvViewAction -> {
                    //showRecommendedMovies(action)
                }
                is FetchSimilarTvViewAction -> {
                    //showSimilarMovies(action)
                }
                is FetchTvReviewViewAction -> {
                    //showReviews(action)
                }
            }
        })

        viewModel.observeDataAction().observe(this, Observer { action ->
            when (action) {
                is TvDetailDataAction.FetchTvDetailsDataAction -> {
                    viewModel.setDataAction(action)
                }
                is TvDetailDataAction.FetchTvSimilarDataAction -> {
                    //viewModel.setDataAction(action)
                }
                is TvDetailDataAction.FetchRecommendedTvDataAction -> {
                    //viewModel.setDataAction(action)
                }
                is TvDetailDataAction.FetchTvReviewDataAction -> {
                    //viewModel.setDataAction(action)
                }
            }
        })
    }

    private fun showTvShowDetails(action: FetchTvDetailsViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success<TvModel> -> {
                binding.model = viewState.data
                seasonAdapter.submitList(viewState.data?.seasons?.reversed())
                castAdapter.submitList(viewState.data?.casts?.toMutableList())
                checkMissingData(viewState.data)
            }
        }
    }

    private fun checkMissingData(data: TvModel?) {
        data?.run {
            if (video.isEmpty()) viewModel.handleEvent(FetchTvVideoViewEvent())
            if (casts.isEmpty()) viewModel.handleEvent(FetchTvCastViewEvent())
        }
    }

    private fun fetchTvShowDetails() {
        viewModel.handleEvent(FetchTvDetailsViewEvent())
        viewModel.handleEvent(FetchRecommendedTvViewEvent())
        viewModel.handleEvent(FetchSimilarTvViewEvent())
        viewModel.handleEvent(FetchTvReviewsViewEvent())
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.ivPoster.id -> {
                showTrailer(binding.model?.video)
            }
        }
    }

    override fun onShowClicked(showModelMinimal: ShowModelMinimal) {

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val scrollRange = appBarLayout?.totalScrollRange ?: 0
        binding.shadow.visibility =
            if ((scrollRange + verticalOffset) == 0) View.VISIBLE else View.GONE
    }
}