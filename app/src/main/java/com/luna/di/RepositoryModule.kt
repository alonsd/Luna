package com.luna.di

import com.luna.data.repository.LunaRepository
import com.luna.data.repository.LunaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHeroesRepository(
        lunaRepositoryImpl: LunaRepositoryImpl
    ): LunaRepository

}