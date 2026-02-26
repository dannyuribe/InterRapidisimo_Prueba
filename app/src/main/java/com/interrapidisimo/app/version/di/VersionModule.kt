package com.interrapidisimo.app.version.di

import com.interrapidisimo.app.version.data.api.VersionService
import com.interrapidisimo.app.version.data.repository.VersionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object VersionModule {
    @Provides
    @Singleton
    fun provideVersionRepository(
        service: VersionService,
    ): VersionRepository = VersionRepository(service)
}