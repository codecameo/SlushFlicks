package com.sifat.slushflicks.di.app

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.BuildConfig.API_KEY
import com.sifat.slushflicks.BuildConfig.DATABASE_NAME
import com.sifat.slushflicks.SlushFlicksApplication
import com.sifat.slushflicks.db.AppDatabase
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.di.constant.NAME_DATABASE
import com.sifat.slushflicks.utils.api.NetworkStateManager
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

    @AppScope
    @Provides
    fun provideAppDb(context: Context, @Named(NAME_DATABASE) name : String): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, name)
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        val fireStore = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        fireStore.firestoreSettings = settings
        return fireStore
    }
}