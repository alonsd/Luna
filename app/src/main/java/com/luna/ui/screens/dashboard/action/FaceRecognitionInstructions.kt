package com.luna.ui.screens.dashboard.action

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luna.R
import com.luna.core.ui.camera.LunaCameraView
import com.luna.core.ui.dialog.LunaAlertDialog


@Composable
@ExperimentalGetImage
fun FaceRecognitionInstructions(onFaceRecognized: () -> Unit) {
    LunaAlertDialog(
        title = {
            Surface(
                color = Color.Blue,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = stringResource(R.string.dashboard_face_recognition_instructions_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                )
            }
        },
        text = {
            Column(Modifier.fillMaxWidth()) {
                LunaCameraView(onFaceRecognized)
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
