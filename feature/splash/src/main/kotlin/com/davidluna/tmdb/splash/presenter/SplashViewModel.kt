package com.davidluna.tmdb.splash.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.core_domain.usecases.SessionFlowUseCase
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.splash.presenter.SplashEvent.LaunchBioPrompt
import com.davidluna.tmdb.splash.presenter.SplashEvent.NavigateTo
import com.davidluna.tmdb.splash.presenter.SplashEvent.OnBioPromptResult
import com.davidluna.tmdb.splash.presenter.SplashEvent.OnPermissionsGranted
import com.davidluna.tmdb.splash.presenter.SplashEvent.ResetAppError
import com.davidluna.tmdb.splash.view.biometrics.BioResult
import com.davidluna.tmdb.splash.view.biometrics.BioResult.UNDEFINED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SplashViewModel(
    private val sessionFlow: SessionFlowUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    data class State(
        val appError: AppError? = null,
        val launchBioPrompt: Boolean = false,
        val session: Session? = null,
        val permissionGranted: Boolean = false,
        val destination: Destination? = null,
        val bioResult: BioResult = UNDEFINED,
    )

    fun onEvent(event: SplashEvent) {
        when (event) {
            is NavigateTo -> onNavigateTo(event.destination)
            is LaunchBioPrompt -> onLaunchBioPrompt(event.launchBioPrompt)
            is OnBioPromptResult -> onBioPromptResult(event.result)
            ResetAppError -> onResetAppError()
            OnPermissionsGranted -> onPermissionsGranted()
        }
    }

    private fun onBioPromptResult(result: BioResult) =
        _state.update { state -> state.copy(bioResult = result) }

    private fun onResetAppError() =
        _state.update { state -> state.copy(appError = null) }

    private fun onLaunchBioPrompt(launchBioPrompt: Boolean) =
        _state.update { state -> state.copy(launchBioPrompt = launchBioPrompt) }

    private fun onNavigateTo(destination: Destination?) =
        _state.update { state -> state.copy(destination = destination) }

    private fun onPermissionsGranted() {
        _state.update { state -> state.copy(permissionGranted = true) }
        collectSession()
    }

    private fun collectSession() {
        viewModelScope.launch {
            sessionFlow()
                .catch { _state.update { state -> state.copy(appError = it.toAppError()) } }
                .collect { session ->
                    if (session.id.isEmpty()) {
                        onEvent(NavigateTo(AuthNavigation.Login()))
                    } else {
                        onEvent(LaunchBioPrompt(true))
                    }
                    _state.update { state -> state.copy(session = session) }
                }
        }
    }

}
