package com.luna.di

import com.luna.data.source.local.LunaLocalDataSource
import com.luna.data.source.local.LunaLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLunaLocalDataSource(
        lunaLocalDataSourceImpl: LunaLocalDataSourceImpl
    ): LunaLocalDataSource
}