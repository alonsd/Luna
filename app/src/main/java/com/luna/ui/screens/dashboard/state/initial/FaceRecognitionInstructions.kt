package com.luna.ui.screens.dashboard.state.initial

import android.graphics.Bitmap
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luna.core.ui.camera.LunaCameraView
import com.luna.core.ui.dialog.LunaAlertDialog


@Composable
@ExperimentalGetImage
fun FaceRecognitionInstructions(onImageAnalyzed: (bitmap: Bitmap) -> Unit) {
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    LunaAlertDialog(
        title = {
            Surface(
                color = Color.Blue,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Hold Camera On Face",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                )
            }
        },
        text = {
            Column(Modifier.fillMaxWidth()) {
                LunaCameraView(onImageAnalyzed)
//                LunaCameraView(onImageAnalyzed = {
//                    bitmap.value = it
//                })
//                if (bitmap.value != null) {
//                    Image(
//                        modifier = Modifier.size(350.dp, 350.dp),
//                        painter = rememberAsyncImagePainter(bitmap),
//                        contentDescription = ""
//                    )
//                }
            }
        },
        onDismissRequest = { },
        confirmButton = {},
        dismissButton = {}
    )
}


@Preview
@Composable
@ExperimentalGetImage
fun FaceRecognitionInstructionsPreview() {
    FaceRecognitionInstructions {}
}
