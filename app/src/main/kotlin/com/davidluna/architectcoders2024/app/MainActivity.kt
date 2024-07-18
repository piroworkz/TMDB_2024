package com.davidluna.architectcoders2024.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.main_ui.presenter.MainViewModel
import com.davidluna.architectcoders2024.main_ui.view.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state: MainViewModel.MainState by viewModel.state.collectAsState()
            closeSession(state.closeSession)
            TmdbTheme {
                Navigator(
                    state = state,
                    sendEvent = { viewModel.sendEvent(it) }
                )
            }
        }
    }

    private fun closeSession(closeSession: Boolean) {
        if (closeSession) {
            finishAffinity()
        }
    }

}
