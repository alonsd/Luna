package com.luna.core.ui.camera

import android.graphics.Bitmap
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.luna.core.extensions.toBitmap

@ExperimentalGetImage class CameraImageAnalyzer(private val listener: (imageBitmap: Bitmap) -> Unit) : ImageAnalysis.Analyzer {

   override fun analyze(imageProxy: ImageProxy) {
       val image = imageProxy.image ?: return
       listener(image.toBitmap())
       imageProxy.close()
   }
}