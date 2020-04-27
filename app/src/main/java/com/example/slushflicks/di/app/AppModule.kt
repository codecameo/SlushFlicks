package com.example.slushflicks.di.app

import android.content.Context
import androidx.room.Room
import com.example.slushflicks.BuildConfig
import com.example.slushflicks.BuildConfig.API_KEY
import com.example.slushflicks.BuildConfig.DATABASE_NAME
import com.example.slushflicks.SlushFlicksApplication
import com.example.slushflicks.db.AppDatabase
import com.example.slushflicks.di.constant.NAME_API_KEY
import com.example.slushflicks.di.constant.NAME_DATABASE
import com.example.slushflicks.utils.api.NetworkStateManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named


@Module
class AppModule {

    @AppScope
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @AppScope
    @Provides
    fun provideApiClient(gson: Gson, factory: Factory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL.plus(BuildConfig.API_VERSION))
            .client(okHttpClient)
            .addCallAdapterFactory(factory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @AppScope
    @Provides
    @Named(NAME_API_KEY)
    fun provideApiKey(): String {
        return API_KEY
    }

    @AppScope
    @Provides
    @Named(NAME_DATABASE)
    fun provideDatabaseName(): String {
        return DATABASE_NAME
    }

    @AppScope
    @Provides
    fun provideContext(slushFlicksApplication: SlushFlicksApplication): Context {
        return slushFlicksApplication
    }

    @AppScope
    @Provides
    fun provideNetworkConnectivityManager(context: Context): NetworkStateManager {
        return NetworkStateManager(context)
    }

    @Provides
    @AppScope
    fun provideAppDb(context: Context, @Named(NAME_DATABASE) name : String): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, name)
            .fallbackToDestructiveMigration()
            .build()
    }
}