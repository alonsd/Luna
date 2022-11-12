package com.luna.core.ui.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer

class CameraAnalyzer(private val listener: (bytes: ByteArray) -> Unit) : ImageAnalysis.Analyzer {

   override fun analyze(image: ImageProxy) {

       val buffer = image.planes[0].buffer
       listener(buffer.toByteArray())
       image.close()
   }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        val data = ByteArray(remaining())
        get(data)
        return data
    }
}