package com.davidluna.architectcoders2024.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.app.ui.screens.navigator.Navigator
import com.davidluna.architectcoders2024.app.ui.theme.DimensDp
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            Locals(dimensDp = DimensDp()) {
                TmdbTheme {
                    Navigator(state = state)
                }
            }
        }
    }
}
