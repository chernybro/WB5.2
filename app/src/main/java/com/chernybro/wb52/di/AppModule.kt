package com.chernybro.wb52.di


import com.chernybro.wb52.data.paging_source.HeroesPagingSource
import com.chernybro.wb52.data.remote.service.HeroListApi
import com.chernybro.wb52.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }



    @Provides
    @Singleton
    fun provideHeroListApi(okHttpClient: OkHttpClient): HeroListApi {
        return Retrofit.Builder()
            .baseUrl(Constants.HEROES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroesPagingSource(heroListApi: HeroListApi): HeroesPagingSource {
        return HeroesPagingSource(heroListApi)
    }
}