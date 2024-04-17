package com.davidluna.architectcoders2024.app.ui.navigation.graphs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.MainGraph
import com.davidluna.architectcoders2024.app.ui.navigation.navComposable
import com.davidluna.architectcoders2024.app.ui.navigation.route

fun NavGraphBuilder.mainNavGraph() {
    navigation(
        route = MainGraph.Init.route(),
        startDestination = MainGraph.Home.route()
    ) {

        navComposable(MainGraph.Home) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

    }
}