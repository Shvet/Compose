package com.shvet.composelogin.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import com.shvet.composelogin.ComposeUIApp
import com.shvet.composelogin.ui.theme.ComposeLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView: SplashScreenViewProvider ->
            ObjectAnimator.ofFloat(
                splashScreenView.view, View.ALPHA, 1F, 0F
            ).apply {
                duration = 750
                doOnEnd {
                    splashScreenView.remove()
                }
            }.also {
                it.start()
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeUIApp()
                }
            }
        }
    }
}
