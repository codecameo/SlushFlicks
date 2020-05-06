package com.sifat.slushflicks.ui.details.state.viewstate

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.details.state.viewaction.TvDetailsViewAction
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.utils.INVALID_ID

class TvDetailsViewState : BaseViewState<TvDetailsViewAction>() {
    var tvShowId: Long = INVALID_ID.toLong()
        set(value) {
            field = value
            reset()
        }

    lateinit var tvModel: TvModel
    var recommendedTvShows = emptyList<ShowListModel>()
    var similarTvShows = emptyList<ShowListModel>()
    var reviews: PagedList<ReviewModel>? = null
    var isAlreadyCastAttempted = false
    var isAlreadyVideoAttempted = false

    private fun reset() {
        isAlreadyCastAttempted = false
        isAlreadyVideoAttempted = false
        reviews = null
        recommendedTvShows = emptyList()
        similarTvShows = emptyList()
    }
}