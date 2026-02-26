package com.interrapidisimo.app.core.system.di

import com.interrapidisimo.app.core.system.AppInfoProvider
import com.interrapidisimo.app.core.system.AppInfoProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
abstract class AppInfoModule {
    @Binds
    @Singleton
    abstract fun bindAppInfoProvider(
        impl : AppInfoProviderImpl
    ): AppInfoProvider
}