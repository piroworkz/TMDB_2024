package com.davidluna.tmdb.app.main_ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.app.main_ui.presenter.MainViewModel
import com.davidluna.tmdb.app.main_ui.view.Navigator
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : FragmentActivity() {
    private val viewModel: MainViewModel by viewModel<MainViewModel>()
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