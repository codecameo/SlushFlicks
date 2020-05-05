package com.sifat.slushflicks.ui.home.tvshow.state.event

/**
 * This events will be fired from the view to viewmodel;
 * based on the event, viewmodel will take necessary action
 * */
sealed class TvListEventState {
    class FetchTvListEvent(val forceUpdate: Boolean = true) : TvListEventState()
}