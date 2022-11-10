package com.luna.core.hardware.base

abstract class MeasurableSensor(
    protected val sensorTypes: IntArray
) {

    protected var onSensorValuesChanged: ((List<Float>, type : Int) -> Unit)? = null

    abstract val doesSensorExist: Boolean

    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (List<Float>, type : Int) -> Unit) {
        onSensorValuesChanged = listener
    }
}