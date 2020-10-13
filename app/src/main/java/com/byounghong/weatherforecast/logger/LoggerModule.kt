package com.byounghong.weatherforecast.logger

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * 로그 모듈 설정 (Dagger Hilt 용)
 */
@InstallIn(ApplicationComponent::class)
@Module
class LoggerModule {
    @Provides
    @Singleton
    fun provideLogger(@ApplicationContext context:Context) = Logger(context)
}