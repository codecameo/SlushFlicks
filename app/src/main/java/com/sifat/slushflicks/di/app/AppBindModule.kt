package com.sifat.slushflicks.di.app


import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.DatabaseManager
import com.sifat.slushflicks.data.FireStoreManager
import com.sifat.slushflicks.data.LocalDataManager
import com.sifat.slushflicks.data.impl.DataManagerImpl
import com.sifat.slushflicks.data.impl.DatabaseManagerImpl
import com.sifat.slushflicks.data.impl.FireStoreManagerImpl
import com.sifat.slushflicks.data.impl.LocalDataManagerImpl
import com.sifat.slushflicks.utils.api.LiveDataCallAdapterFactory
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

    @Binds
    abstract fun bindFireStoreDataManager(fireStoreManagerImpl: FireStoreManagerImpl): FireStoreManager
}