package com.interrapidisimo.app.core.database.di

import android.app.Application
import androidx.room.Room
import com.interrapidisimo.app.Tables.data.db.TableDao
import com.interrapidisimo.app.core.database.AppDatabase
import com.interrapidisimo.app.login.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "interrapidisimo_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideTableDao(db: AppDatabase): TableDao {
        return db.tablaDao()
    }
}