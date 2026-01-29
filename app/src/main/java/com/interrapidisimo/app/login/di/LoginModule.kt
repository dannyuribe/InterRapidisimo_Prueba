package com.interrapidisimo.app.login.di

import com.interrapidisimo.app.login.data.api.LoginService
import com.interrapidisimo.app.login.data.db.UserDao
import com.interrapidisimo.app.login.data.repository.LoginRepositoryImpl
import com.interrapidisimo.app.login.domain.AuthRepository
import com.interrapidisimo.app.login.domain.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        loginService: LoginService,
        userDao: UserDao
    ): AuthRepository = LoginRepositoryImpl(loginService, userDao)

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase =
        LoginUseCase(repository)
}
