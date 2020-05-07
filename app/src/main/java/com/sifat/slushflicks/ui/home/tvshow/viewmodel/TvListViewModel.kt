package com.sifat.slushflicks.ui.home.tvshow.viewmodel

import com.sifat.slushflicks.di.constant.NAME_AIR_TODAY_TV_REPO
import com.sifat.slushflicks.di.constant.NAME_POPULAR_TV_REPO
import com.sifat.slushflicks.di.constant.NAME_TOP_RATED_TV_REPO
import com.sifat.slushflicks.di.constant.NAME_TRENDING_TV_REPO
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.repository.tv.TvListRepository
import javax.inject.Inject
import javax.inject.Named

@HomeScope
class TrendingTvViewModel
@Inject constructor(@Named(NAME_TRENDING_TV_REPO) trendingTvRepository: TvListRepository) :
    BaseTvListViewModel(trendingTvRepository)

@HomeScope
class PopularTvViewModel
@Inject constructor(@Named(NAME_POPULAR_TV_REPO) popularTvRepository: TvListRepository) :
    BaseTvListViewModel(popularTvRepository)

@HomeScope
class TopRatedTvViewModel
@Inject constructor(@Named(NAME_TOP_RATED_TV_REPO) topRatedTvRepository: TvListRepository) :
    BaseTvListViewModel(topRatedTvRepository)

@HomeScope
class AirTodayTvViewModel
@Inject constructor(@Named(NAME_AIR_TODAY_TV_REPO) airTodayTvRepository: TvListRepository) :
    BaseTvListViewModel(airTodayTvRepository)