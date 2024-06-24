package com.davidluna.architectcoders2024.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.theme.DimensDp
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.core_ui.theme.locals.Locals
import com.davidluna.architectcoders2024.main_ui.presenter.MainViewModel
import com.davidluna.architectcoders2024.main_ui.view.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setContentKind(ContentKind.MOVIE)
        setContent {
            val state by viewModel.state.collectAsState()
            Locals(dimensDp = DimensDp()) {
                TmdbTheme {
                    Navigator(state = state, sendEvent = viewModel::setContentKind)
                }
            }
        }
    }
}
