package com.interrapidisimo.app.Tables.di

import com.interrapidisimo.app.Tables.data.api.TableService
import com.interrapidisimo.app.Tables.data.db.TableDao
import com.interrapidisimo.app.Tables.data.repository.TableRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TableModule {

    @Provides
    @Singleton
    fun provideTableRepository(
        service: TableService,
        tableDao: TableDao
    ): TableRepository = TableRepository(service, tableDao)

}