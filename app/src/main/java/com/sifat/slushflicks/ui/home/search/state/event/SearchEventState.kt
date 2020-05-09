package com.sifat.slushflicks.ui.home.search.state.event

import com.sifat.slushflicks.utils.ShowType

sealed class SearchEventState {
    class ShowSearchEvent : SearchEventState()
    class UpdateShowTypeEvent(val showType: ShowType) : SearchEventState()
    class SetSearchQueryEvent() : SearchEventState()
}