package com.davidluna.architectcoders2024.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.davidluna.architectcoders2024.app.ui.screens.navigator.Navigator
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerScreen
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TmdbTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    VideoPlayerScreen("Way9Dexny3w")
                    Navigator()
                }
            }
        }
    }
}

