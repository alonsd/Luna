package com.luna.data.repository

interface LunaRepository {

    suspend fun getDeviceTiltAngle(onDeviceAngelChange : (angel : Int) -> Unit)
}