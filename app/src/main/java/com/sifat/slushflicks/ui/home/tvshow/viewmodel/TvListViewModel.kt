package com.sifat.slushflicks.ui.home.tvshow.viewmodel

import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.repository.tv.AirTodayTvRepository
import com.sifat.slushflicks.repository.tv.PopularTvRepository
import com.sifat.slushflicks.repository.tv.TopRatedTvRepository
import com.sifat.slushflicks.repository.tv.TrendingTvRepository
import javax.inject.Inject

@HomeScope
class TrendingTvViewModel
@Inject constructor(trendingTvRepository: TrendingTvRepository) :
    BaseTvListViewModel(trendingTvRepository)

@HomeScope
class PopularTvViewModel
@Inject constructor(popularTvRepository: PopularTvRepository) :
    BaseTvListViewModel(popularTvRepository)

@HomeScope
class TopRatedTvViewModel
@Inject constructor(topRatedTvRepository: TopRatedTvRepository) :
    BaseTvListViewModel(topRatedTvRepository)

@HomeScope
class AirTodayTvViewModel
@Inject constructor(airTodayTvRepository: AirTodayTvRepository) :
    BaseTvListViewModel(airTodayTvRepository)