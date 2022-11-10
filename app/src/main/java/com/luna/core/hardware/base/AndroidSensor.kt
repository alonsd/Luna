package com.luna.core.hardware.base

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

abstract class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    sensorTypes: IntArray
) : MeasurableSensor(sensorTypes), SensorEventListener {

    override val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    private var sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    init {
        startListening()
    }

    final override fun startListening() {
        if (!doesSensorExist) {
            return
        }
        sensorTypes.forEach {
            val sensor = sensorManager.getDefaultSensor(it)
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
                sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_UI
                )
            }
            sensorManager.registerListener(this@AndroidSensor, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }


    override fun stopListening() {
        if (doesSensorExist.not()) return
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (!doesSensorExist) {
            return
        }
        val type = event?.sensor?.type ?: return
        if (sensorTypes.contains(type).not()) return
        onSensorValuesChanged?.invoke(event.values.toList(), event.sensor.type)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
}