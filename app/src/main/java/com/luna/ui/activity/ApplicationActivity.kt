package com.luna.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.luna.core.extensions.setHeroesContent
import com.luna.ui.screens.dashboard.screen.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class ApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHeroesContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}