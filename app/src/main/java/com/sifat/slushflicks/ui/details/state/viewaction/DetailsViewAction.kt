package com.sifat.slushflicks.ui.details.state.viewaction

import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.ui.state.ViewState

sealed class DetailsViewAction {
    data class FetchDetailsViewAction(val viewState: ViewState<MovieModel>) : DetailsViewAction()
}