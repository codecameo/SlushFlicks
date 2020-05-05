package com.sifat.slushflicks.ui.home.tvshow.viewmodel

import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.repository.tv.TrendingTvRepository
import javax.inject.Inject

@HomeScope
class TrendingTvViewModel
@Inject constructor(trendingTvRepository: TrendingTvRepository) :
    BaseTvListViewModel(trendingTvRepository)