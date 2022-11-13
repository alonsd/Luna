package com.luna.data.source.local

import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.Sensor.TYPE_MAGNETIC_FIELD
import android.hardware.SensorManager
import com.luna.core.hardware.base.MeasurableSensor
import javax.inject.Inject
import kotlin.math.absoluteValue

class LunaLocalDataSourceImpl @Inject constructor(
    private val accelerationSensor: MeasurableSensor,
) : LunaLocalDataSource {

    override suspend fun getDeviceTiltAngle(onDeviceTiltAngleChange: (angel: Int) -> Unit) {


        var accelerometerReading = floatArrayOf()
        var magnetometerReading = floatArrayOf()

        accelerationSensor.setOnSensorValuesChangedListener { value, type ->

            when (type) {
                TYPE_ACCELEROMETER -> {
                    accelerometerReading = floatArrayOf(value[0], value[1], value[2])
                }
                TYPE_MAGNETIC_FIELD -> {
                    magnetometerReading = floatArrayOf(value[0], value[1], value[2])
                }
            }
            val rotationMatrix = FloatArray(9)
            if (magnetometerReading.isEmpty() || accelerometerReading.isEmpty()) return@setOnSensorValuesChangedListener
            val xAxisOrientationInDegrees = calculateXAxisOrientationInDegrees(rotationMatrix, accelerometerReading, magnetometerReading)
            onDeviceTiltAngleChange(xAxisOrientationInDegrees.toInt().absoluteValue)
        }
    }

    private fun calculateXAxisOrientationInDegrees(rotationMatrix: FloatArray, accelerometerReading: FloatArray, magnetometerReading: FloatArray): Double {
        SensorManager.getRotationMatrix(rotationMatrix, FloatArray(9), accelerometerReading, magnetometerReading)
        val orientationAngles = FloatArray(3)
        SensorManager.getOrientation(rotationMatrix, orientationAngles)
        return (orientationAngles[1] / Math.PI) * 180
    }
}