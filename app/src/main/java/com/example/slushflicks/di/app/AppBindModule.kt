package com.example.slushflicks.di.app


import com.example.slushflicks.data.DataManager
import com.example.slushflicks.data.DatabaseManager
import com.example.slushflicks.data.LocalDataManager
import com.example.slushflicks.data.impl.DataManagerImpl
import com.example.slushflicks.data.impl.DatabaseManagerImpl
import com.example.slushflicks.data.impl.LocalDataManagerImpl
import com.example.slushflicks.utils.api.LiveDataCallAdapterFactory
import dagger.Binds
import dagger.Module
import retrofit2.CallAdapter.Factory

@Module
abstract class AppBindModule {

    /**
     * Since it's faster than @Provides annotation
     * */
    @Binds
    abstract fun bindCallAdapterFactory(liveDataCallAdapterFactory: LiveDataCallAdapterFactory) : Factory

    @Binds
    abstract fun bindDataManager(dataManagerImpl: DataManagerImpl) : DataManager

    @Binds
    abstract fun bindLocalDataManager(localDataManagerImpl: LocalDataManagerImpl) : LocalDataManager

    @Binds
    abstract fun bindDatabaseManager(databaseManagerImpl: DatabaseManagerImpl) : DatabaseManager
}