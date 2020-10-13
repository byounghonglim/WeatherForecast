package com.byounghong.weatherforecast.data.remote.module

import com.byounghong.weatherforecast.BuildConfig
import com.byounghong.weatherforecast.data.remote.api.WeatherForecastApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Retrofit & OkHttp 세팅
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private const val CONNECT_TIMEOUT = 100L
    private const val READ_TIMEOUT = 100L

    @Provides
    fun provideBaseUrl()  = BuildConfig.SERVER_URL        //서버 기본 주소

    /**
     * OkHttp 세팅
     */
    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder().
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).
            retryOnConnectionFailure(true).apply {
            if(BuildConfig.DEBUG) {
                this.addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()

    /**
     * Retrofit 세팅
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BaseUrl: String): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMetaWeatherService(retrofit: Retrofit):WeatherForecastApi
            = retrofit.create(WeatherForecastApi::class.java)
}