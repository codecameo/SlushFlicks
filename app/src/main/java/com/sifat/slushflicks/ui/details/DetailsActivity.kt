package com.sifat.slushflicks.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityDetailsBinding
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.FullScreenActivity
import com.sifat.slushflicks.ui.details.adapter.CastAdapter
import com.sifat.slushflicks.ui.details.adapter.RelatedMovieAdapter
import com.sifat.slushflicks.ui.details.adapter.ReviewAdapter
import com.sifat.slushflicks.ui.details.adapter.viewholder.MovieViewHolder
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction.*
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.FetchCastViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.FetchVideoViewEvent
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction.*
import com.sifat.slushflicks.ui.details.viewmodel.DetailsViewModel
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.INVALID_ID
import com.sifat.slushflicks.utils.showToast


class DetailsActivity : FullScreenActivity<ActivityDetailsBinding, DetailsViewModel>(),
    View.OnClickListener, MovieViewHolder.OnMovieClickListener {
    private val TAG = "DetailsActivity"
    override fun getLayoutRes() = R.layout.activity_details

    override fun getViewModelClass() = DetailsViewModel::class.java
    private var isAlreadyAttempted = false
    private lateinit var castAdapter: CastAdapter
    private lateinit var recommendedAdapter: RelatedMovieAdapter
    private lateinit var similarAdapter: RelatedMovieAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractData()
        setupToolbar()
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchMovieDetails()
    }

    /**
     * Set toolbar into actionbar.
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarDetails)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun extractData() {
        val movieId = intent.getLongExtra(KEY_MOVIE_ID, INVALID_ID.toLong())
        Log.d(TAG, "Movie id $movieId")
        viewModel.setMovieId(movieId)
    }

    private fun setupList() {
        binding.rvCast.adapter = castAdapter
        binding.rvRecommended.adapter = recommendedAdapter
        binding.rvSimilar.adapter = similarAdapter
        binding.rvReview.adapter = reviewAdapter
    }

    private fun initVariable() {
        castAdapter = CastAdapter()
        recommendedAdapter = RelatedMovieAdapter()
        similarAdapter = RelatedMovieAdapter()
        reviewAdapter = ReviewAdapter()
    }

    private fun initListener() {
        binding.ivPoster.setOnClickListener(this)
        recommendedAdapter.onMovieClickedListener = this
        similarAdapter.onMovieClickedListener = this
    }

    private fun subscribeAction() {
        viewModel.observeViewAction().observe(this, Observer { action ->
            when (action) {
                is FetchDetailsViewAction -> {
                    showMovieDetails(action)
                }
                is FetchRecommendationViewAction -> {
                    showRecommendedMovies(action)
                }
                is FetchSimilarViewAction -> {
                    showSimilarMovies(action)
                }
                is FetchReviewViewAction -> {
                    showReviews(action)
                }
            }
        })

        viewModel.observeDataAction().observe(this, Observer { action ->
            when (action) {
                is FetchMovieDetailsAction -> {
                    viewModel.setDataAction(action)
                }
                is FetchSimilarDataAction -> {
                    viewModel.setDataAction(action)
                }
                is FetchRecommendationDataAction -> {
                    viewModel.setDataAction(action)
                }
                is FetchReviewDataAction -> {
                    viewModel.setDataAction(action)
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchMovieDetails() {
        viewModel.handleEvent(DetailsViewEvent.FetchDetailsViewEvent())
        viewModel.handleEvent(DetailsViewEvent.FetchRecommendedMovieViewEvent())
        viewModel.handleEvent(DetailsViewEvent.FetchSimilarMovieViewEvent())
        viewModel.handleEvent(DetailsViewEvent.FetchReviewsViewEvent())
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.ivPoster.id -> {
                showTrailer()
            }
        }
    }

    override fun onMovieClicked(showModelMinimal: ShowModelMinimal) {
        refreshWith(showModelMinimal.id)
    }

    private fun refreshWith(movieId: Long) {
        viewModel.setMovieId(movieId = movieId)
        isAlreadyAttempted = false
        fetchMovieDetails()
        binding.nsvContent.smoothScrollTo(0, 0)
        binding.appBarPoster.setExpanded(true, true)
    }

    private fun showTrailer() {
        binding.model?.let { model ->
            if (model.video.isNotEmpty()) {
                openYoutube(model.video)
            }
        }
    }

    private fun showMovieDetails(action: FetchDetailsViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success<MovieModel> -> {
                binding.model = viewState.data
                castAdapter.submitList(viewState.data?.casts?.toMutableList())
                checkMissingData(viewState.data)
            }
        }
    }

    private fun showRecommendedMovies(action: FetchRecommendationViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Loading<List<MovieListModel>> -> {
                recommendedAdapter.submitList(viewState.data)
            }
            is ViewState.Success<List<MovieListModel>> -> {
                recommendedAdapter.submitList(viewState.data)
                if (viewState.data.isNullOrEmpty()) hideRecommendedSection()
            }
            is ViewState.Error<List<MovieListModel>> -> {
                showToast(viewState.errorMessage ?: getString(R.string.similar_error_message))
                hideRecommendedSection()
            }
        }
    }

    private fun showSimilarMovies(action: FetchSimilarViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Loading<List<MovieListModel>> -> {
                similarAdapter.submitList(viewState.data)
            }
            is ViewState.Success<List<MovieListModel>> -> {
                similarAdapter.submitList(viewState.data)
                if (viewState.data.isNullOrEmpty()) hideSimilarSection()
            }
            is ViewState.Error<List<MovieListModel>> -> {
                showToast(viewState.errorMessage ?: getString(R.string.similar_error_message))
                hideSimilarSection()
            }
        }
    }

    private fun showReviews(action: FetchReviewViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success<PagedList<ReviewModel>> -> {
                reviewAdapter.submitList(viewState.data)
                if (viewState.data.isNullOrEmpty()) hideReviewList()
            }
        }
    }

    private fun checkMissingData(data: MovieModel?) {
        if (isAlreadyAttempted) return
        isAlreadyAttempted = true
        data?.run {
            if (video.isEmpty()) viewModel.handleEvent(FetchVideoViewEvent())
            if (casts.isEmpty()) viewModel.handleEvent(FetchCastViewEvent())
        }
    }

    private fun openYoutube(videoId: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(String.format(YOUTUBE_VIDEO_LINK, videoId))
        )
        intent.`package` = YOUTUBE_PACKAGE_NAME
        val activeApp = packageManager.queryIntentActivities(intent, 0)
        if (activeApp.isNotEmpty()) {
            startActivity(intent)
        } else {
            showToast(getString(R.string.install_youtube))
        }
    }

    private fun hideRecommendedSection() {
        binding.tvTitleRecommended.visibility = GONE
        binding.rvRecommended.visibility = GONE
    }

    private fun hideSimilarSection() {
        binding.tvTitleSimilar.visibility = GONE
        binding.rvSimilar.visibility = GONE
    }

    private fun hideReviewList() {
        //binding.rvReview.visibility = INVISIBLE
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
        const val YOUTUBE_PACKAGE_NAME = "com.google.android.youtube"
        const val YOUTUBE_VIDEO_LINK = "https://www.youtube.com/watch?v=%s"
    }
}