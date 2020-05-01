package com.sifat.slushflicks.ui.details

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityDetailsBinding
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.ui.base.BaseActivity
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction.FetchMovieDetailsAction
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction.FetchDetailsViewAction
import com.sifat.slushflicks.ui.details.viewmodel.DetailsViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.INVALID_ID

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {
    private val TAG = "DetailsActivity"
    override fun getLayoutRes() = R.layout.activity_details

    override fun getViewModelClass() = DetailsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractData()
        subscribeAction()
        fetchMovieDetails()
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

    private fun showMovieDetails(action: FetchDetailsViewAction) {
        when (val viewState = action.viewState) {
            is ViewState.Success<MovieModel> -> {
                binding.model = viewState.data
                /*tvTagline.visibility = if(TextUtils.isEmpty(viewState.data?.tagline)) {
                    Log.d(TAG, "GONE $GONE")
                    GONE
                } else {
                    Log.d(TAG, "VISIBLE $VISIBLE")
                    VISIBLE
                }
                Log.d(TAG, "After ${tvTagline.visibility}")*/
            }
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
    }
}