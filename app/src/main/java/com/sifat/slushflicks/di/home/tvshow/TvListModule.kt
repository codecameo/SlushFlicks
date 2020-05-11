package com.sifat.slushflicks.di.home.tvshow

import com.sifat.slushflicks.di.constant.NAME_AIR_TODAY_TV_REPO
import com.sifat.slushflicks.di.constant.NAME_POPULAR_TV_REPO
import com.sifat.slushflicks.di.constant.NAME_TOP_RATED_TV_REPO
import com.sifat.slushflicks.di.constant.NAME_TRENDING_TV_REPO
import com.sifat.slushflicks.repository.tv.TvListRepository
import com.sifat.slushflicks.repository.tv.impl.AirTodayTvRepositoryImpl
import com.sifat.slushflicks.repository.tv.impl.PopularTvRepositoryImpl
import com.sifat.slushflicks.repository.tv.impl.TopRatedTvRepositoryImpl
import com.sifat.slushflicks.repository.tv.impl.TrendingTvRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class TvListModule {

    @TvShowListScope
    @Binds
    @Named(NAME_TRENDING_TV_REPO)
    abstract fun provideTrendingTvRepository(trendingTvRepositoryImpl: TrendingTvRepositoryImpl): TvListRepository

    @TvShowListScope
    @Binds
    @Named(NAME_POPULAR_TV_REPO)
    abstract fun providePopularTvRepository(popularTvRepositoryImpl: PopularTvRepositoryImpl): TvListRepository

    @TvShowListScope
    @Binds
    @Named(NAME_AIR_TODAY_TV_REPO)
    abstract fun provideAirTodayTvRepository(airTodayTvRepositoryImpl: AirTodayTvRepositoryImpl): TvListRepository

    @TvShowListScope
    @Binds
    @Named(NAME_TOP_RATED_TV_REPO)
    abstract fun provideTopRatedTvRepository(topRatedTvRepositoryImpl: TopRatedTvRepositoryImpl): TvListRepository
}