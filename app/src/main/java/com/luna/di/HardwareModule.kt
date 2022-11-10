package com.luna.di

import com.luna.core.hardware.implementation.AccelerationSensor
import com.luna.core.hardware.base.MeasurableSensor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class HardwareModule {

    @Binds
    @Singleton
    abstract fun bindAccelerometerSensor(
        accelerationSensor: AccelerationSensor
    ): MeasurableSensor
}