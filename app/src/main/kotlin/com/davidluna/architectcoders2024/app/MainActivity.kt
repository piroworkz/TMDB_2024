package com.davidluna.architectcoders2024.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.davidluna.architectcoders2024.app.ui.navigation.MainGraph
import com.davidluna.architectcoders2024.app.ui.navigation.Navigator
import com.davidluna.architectcoders2024.app.ui.navigation.navComposable
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchitectCoders2024Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator {
                        navComposable(MainGraph.Home) {
                            LoginScreen()
                        }
                        navComposable(MainGraph.Detail) { Text(text = "Detail") }
                    }
                }
            }
        }
    }
}

