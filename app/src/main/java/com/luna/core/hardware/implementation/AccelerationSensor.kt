package com.luna.core.hardware.implementation

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_MAGNETIC_FIELD
import com.luna.core.hardware.base.AndroidSensor
import javax.inject.Inject

class AccelerationSensor @Inject constructor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorTypes = intArrayOf(Sensor.TYPE_ACCELEROMETER, TYPE_MAGNETIC_FIELD)
)