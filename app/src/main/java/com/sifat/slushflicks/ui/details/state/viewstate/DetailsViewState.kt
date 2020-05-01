package com.sifat.slushflicks.ui.details.state.viewstate

import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction
import com.sifat.slushflicks.utils.INVALID_ID

class DetailsViewState : BaseViewState<DetailsViewAction>() {
    var movieId: Long = INVALID_ID.toLong()
    lateinit var movie: MovieModel
}