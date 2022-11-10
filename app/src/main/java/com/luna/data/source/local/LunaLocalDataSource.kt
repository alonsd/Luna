package com.luna.data.source.local

interface LunaLocalDataSource {

    suspend fun getDeviceTiltAngle(onDeviceTiltAngleChange : (angel : Int) -> Unit)

}
