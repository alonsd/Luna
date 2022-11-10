package com.luna.core.custom_implementations

import android.os.CountDownTimer

class LunaCountDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    private val onTimerFinished :(countDownTimer: CountDownTimer) -> Unit,
    private val onTimerTick :(remainingTime: Long) -> Unit
) : CountDownTimer(millisInFuture, countDownInterval) {
    override fun onTick(p0: Long) {
        onTimerTick(p0)
    }

    override fun onFinish() {
        onTimerFinished(this)
    }
}