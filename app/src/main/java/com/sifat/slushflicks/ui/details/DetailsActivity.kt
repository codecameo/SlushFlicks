package com.sifat.slushflicks.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityDetailsBinding
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.ui.base.FullScreenActivity
import com.sifat.slushflicks.ui.details.adapter.CastAdapter
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction.FetchMovieDetailsAction
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.FetchCastViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.FetchVideoViewEvent
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction.FetchDetailsViewAction
import com.sifat.slushflicks.ui.details.viewmodel.DetailsViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.INVALID_ID
import com.sifat.slushflicks.utils.showToast

class DetailsActivity : FullScreenActivity<ActivityDetailsBinding, DetailsViewModel>(),
    View.OnClickListener {
    private val TAG = "DetailsActivity"
    override fun getLayoutRes() = R.layout.activity_details

    override fun getViewModelClass() = DetailsViewModel::class.java
    private var isAlreadyAttempted = false
    private lateinit var adapter: CastAdapter

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
        binding.rvCast.adapter = adapter
    }

    private fun initVariable() {
        adapter = CastAdapter()
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
            }
        })

        viewModel.observeDataAction().observe(this, Observer { action ->
            when (action) {
                is FetchMovieDetailsAction -> {
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
                adapter.submitList(viewState.data?.casts?.toMutableList())
                checkMissingData(viewState.data)
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
        viewModel.handleEvent(DetailsViewEvent.FetchDetailsViewEvent())
    }

    private fun extractData() {
        viewModel.setMovieId(intent.getLongExtra(KEY_MOVIE_ID, INVALID_ID.toLong()))
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
        const val YOUTUBE_PACKAGE_NAME = "com.google.android.youtube"
        const val YOUTUBE_PLAYER_ACTIVITY_PACKAGE = "com.google.android.youtube.PlayerActivity"
        const val YOUTUBE_VIDEO_LINK = "https://www.youtube.com/watch?v=%s"
    }
}