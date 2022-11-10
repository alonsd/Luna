package com.luna.data.repository

import com.luna.data.source.local.LunaLocalDataSource
import javax.inject.Inject

class LunaRepositoryImpl @Inject constructor(
    private val lunaLocalDataSourceImpl: LunaLocalDataSource
) : LunaRepository {

    override suspend fun getDeviceTiltAngle(onDeviceAngelChange: (angel: Int) -> Unit) =
        lunaLocalDataSourceImpl.getDeviceTiltAngle(onDeviceAngelChange)

}

