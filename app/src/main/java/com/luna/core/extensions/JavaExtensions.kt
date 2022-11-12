package com.luna.core.extensions

import android.graphics.*
import android.media.Image
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer


fun ByteBuffer.toBitmap(): Bitmap? {
    val imageBytes = ByteArray(this.remaining())
    this.get(imageBytes)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun Image.toBitmap(): Bitmap {
    val yBuffer = planes[0].buffer
    val vuBuffer = planes[2].buffer

    val ySize = yBuffer.remaining()
    val vuSize = vuBuffer.remaining()

    val nv21 = ByteArray(ySize + vuSize)

    yBuffer.get(nv21, 0, ySize)
    vuBuffer.get(nv21, ySize, vuSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 50, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}