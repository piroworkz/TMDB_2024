package com.davidluna.architectcoders2024.app.ui.screens.login

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.remote.AuthenticationRepository
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.app.ui.navigation.AuthGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val args: String?) : ViewModel() {

    private val repository: AuthenticationRepository = AuthenticationRepository()

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getArguments()
    }

    data class State(
        val userAccountDetail: RemoteUserAccountDetail? = null,
        val authToken: String? = null,
        val sessionId: String? = null,
        val isLoading: Boolean = false,
        val launchIntent: Boolean = false
    )

    fun sendEvent(event: LoginEvent) {
        event.toString().log("sendEvent")
        when (event) {
            LoginEvent.AskForPermission -> askForPermission()
            LoginEvent.CreateRequestToken -> createRequestToken()
            is LoginEvent.CreateSessionId -> createSessionId(event.requestToken)
            LoginEvent.GetAccount -> getAccount()
        }
    }

    private fun createRequestToken() = run {
        _state.update { s ->
            s.copy(
                authToken = repository.createRequestToken().requestToken,
                launchIntent = true
            )
        }
    }

    private fun askForPermission() =
        _state.update { it.copy(launchIntent = !it.launchIntent) }

    private fun createSessionId(requestToken: String) = run {
        _state.update { it.copy(sessionId = repository.createSessionId(requestToken.request()).sessionId) }
        sendEvent(LoginEvent.GetAccount)
    }


    private fun getAccount() = run {
        _state.update { it.copy(userAccountDetail = repository.getAccount()) }
    }

    private fun getArguments() {
        args?.let {
            val uri =
                Uri.parse(AuthGraph.Login.deepLinks[0].uriPattern?.replace("{approved}", args))
            val approved = uri.getBooleanQueryParameter("approved", false)
            val requestToken = uri.getQueryParameter("request_token")
            if (approved && requestToken != null) {
                sendEvent(LoginEvent.CreateSessionId(requestToken))
            }
        }
    }

    private fun String.request() = RemoteLoginRequest(this)

    private fun run(action: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                action()
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                e.printStackTrace()
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

}


fun String.log(name: String = javaClass.simpleName) = Log.d("<-- $name", this)