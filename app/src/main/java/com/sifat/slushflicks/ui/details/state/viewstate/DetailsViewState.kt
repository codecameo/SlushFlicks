package com.sifat.slushflicks.ui.details.state.viewstate

import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.utils.INVALID_ID

class DetailsViewState : BaseViewState<DetailsViewAction>() {
    var movieId: Long = INVALID_ID.toLong()
        set(value) {
            field = value
            reset()
        }

    lateinit var movie: MovieModel
    var recommendedMovies = emptyList<MovieListModel>()
    var similarMovies = emptyList<MovieListModel>()
    var reviews: PagedList<ReviewModel>? = null
    var isAlreadyCastAttempted = false
    var isAlreadyVideoAttempted = false

    private fun reset() {
        isAlreadyCastAttempted = false
        isAlreadyVideoAttempted = false
        reviews = null
        recommendedMovies = emptyList()
        similarMovies = emptyList()
    }
}