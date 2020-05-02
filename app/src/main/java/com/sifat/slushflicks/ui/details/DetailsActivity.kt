package com.sifat.slushflicks.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.sifat.slushflicks.R
import com.sifat.slushflicks.api.ApiEndPoint
import com.sifat.slushflicks.databinding.ActivityDetailsBinding
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.ui.base.FullScreenActivity
import com.sifat.slushflicks.ui.details.adapter.CastAdapter
import com.sifat.slushflicks.ui.details.adapter.RelatedMovieAdapter
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
    View.OnClickListener {
    private val TAG = "DetailsActivity"
    override fun getLayoutRes() = R.layout.activity_details

    override fun getViewModelClass() = DetailsViewModel::class.java
    private var isAlreadyAttempted = false
    private lateinit var castAdapter: CastAdapter
    private lateinit var recommendedAdapter: RelatedMovieAdapter
    private lateinit var similarAdapter: RelatedMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractData()
        initVariable()
        initListener()
        setupList()
        subscribeAction()
        fetchMovieDetails()
    }

    private fun setupList() {
        binding.rvCast.adapter = castAdapter
        binding.rvRecommended.adapter = recommendedAdapter
        binding.rvSimilar.adapter = similarAdapter
    }

    private fun initVariable() {
        castAdapter = CastAdapter()
        recommendedAdapter = RelatedMovieAdapter()
        similarAdapter = RelatedMovieAdapter()
    }

    private fun initListener() {
        binding.ivPoster.setOnClickListener(this)
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
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.ivPoster.id -> {
                showTrailer()
            }
        }
    }

    private fun showTrailer() {
        binding.model?.let { model ->
            if (model.video.isNotEmpty()) {
                openYoutube(model.video)
            }
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

            }
            is ViewState.Success<List<MovieListModel>> -> {
                recommendedAdapter.submitList(viewState.data)
            }
            is ViewState.Error<List<MovieListModel>> -> {
                showToast(viewState.errorMessage ?: getString(R.string.recommended_error_message))
            }
        }
    }

    private fun showSimilarMovies(action: FetchSimilarViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Loading<List<MovieListModel>> -> {

            }
            is ViewState.Success<List<MovieListModel>> -> {
                similarAdapter.submitList(viewState.data)
            }
            is ViewState.Error<List<MovieListModel>> -> {
                showToast(viewState.errorMessage ?: getString(R.string.recommended_error_message))
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

    private fun fetchMovieDetails() {
        Log.d(TAG, ApiEndPoint.MOVIE_RELATED_MOVIE_URL)
        viewModel.handleEvent(DetailsViewEvent.FetchDetailsViewEvent())
        viewModel.handleEvent(DetailsViewEvent.FetchRecommendedMovieViewEvent())
        viewModel.handleEvent(DetailsViewEvent.FetchSimilarMovieViewEvent())
    }

    private fun extractData() {
        val movieId = intent.getLongExtra(KEY_MOVIE_ID, INVALID_ID.toLong())
        Log.d(TAG, "Movie id $movieId")
        viewModel.setMovieId(movieId)
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
        const val YOUTUBE_PACKAGE_NAME = "com.google.android.youtube"
        const val YOUTUBE_VIDEO_LINK = "https://www.youtube.com/watch?v=%s"
    }
}