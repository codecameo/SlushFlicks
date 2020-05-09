package com.sifat.slushflicks.ui.home.search.state.viewstate

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.paging.PagedList
import com.sifat.slushflicks.BR
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.home.search.state.viewaction.SearchViewAction
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.ShowType

class SearchViewState : BaseViewState<SearchViewAction>() {
    var showList: PagedList<ShowModelMinimal>? = null
    var showType = ShowType.MOVIE
    val queryModel by lazy {
        QueryModel()
    }
}

class QueryModel : BaseObservable() {
    var query: String = EMPTY_STRING
        @Bindable get
        set(value) {
            if (TextUtils.equals(value, field)) return
            field = value
            notifyPropertyChanged(BR.query)
        }
}