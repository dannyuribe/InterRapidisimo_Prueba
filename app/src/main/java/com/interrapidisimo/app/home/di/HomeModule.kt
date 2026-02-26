package com.interrapidisimo.app.home.di

import com.interrapidisimo.app.Tables.data.repository.TableRepository
import com.interrapidisimo.app.home.data.repository.HomeRepository
import com.interrapidisimo.app.user.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        userRepository: UserRepository,
        tableRepository: TableRepository
    ): HomeRepository = HomeRepository(userRepository, tableRepository)
}