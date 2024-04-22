package com.davidluna.architectcoders2024.app.ui.screens.login

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteAvatar
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteGravatar
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph.Login
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.Auth
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.data.AuthenticationRepository
import com.davidluna.architectcoders2024.data.SessionRepository
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.protodatastore.AuthenticationValues
import com.davidluna.protodatastore.Avatar
import com.davidluna.protodatastore.Gravatar
import com.davidluna.protodatastore.UserAccount
import com.davidluna.protodatastore.avatar
import com.davidluna.protodatastore.gravatar
import com.davidluna.protodatastore.userAccount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val args: String,
    private val repository: AuthenticationRepository,
    private val local: SessionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getArguments()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val token: String? = null,
        val authSession: AuthenticationValues? = null,
        val intent: Boolean = false,
        val isLoggedIn: Boolean = false
    )

    fun sendEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.AskForPermission -> askForPermission()
            LoginEvent.CreateRequestToken -> createRequestToken()
            is LoginEvent.CreateSessionId -> createSessionId(event.requestToken)
            LoginEvent.GetAccount -> getAccount()
            LoginEvent.ResetError -> resetError()
            LoginEvent.IsLoggedIn -> setIsLoggedIn()
        }
    }

    private fun setIsLoggedIn() {
        _state.update { it.copy(isLoggedIn = !it.isLoggedIn) }
    }

    private fun resetError() {
        _state.update { it.copy(appError = null) }
    }

    private fun createRequestToken() = run {
        repository.createRequestToken().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { s -> s.copy(token = r.requestToken, intent = true) } }
        )
    }

    private fun askForPermission() =
        _state.update { it.copy(intent = !it.intent) }

    private fun createSessionId(requestToken: String) = run {
        repository.createSessionId(requestToken.request()).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r ->
                local.saveSessionId(r.sessionId)
                sendEvent(LoginEvent.GetAccount)
            }
        )
    }

    private fun getAccount() = run {
        repository.getAccount().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r ->
                local.saveUser(r.toUser())
                sendEvent(LoginEvent.IsLoggedIn)
            }
        )
    }

    private fun getArguments() {
        if (args.isEmpty()) return
        val uri =
            Uri.parse(Login.deepLinks[0].uriPattern?.replace("{${Auth.name}}", args))
        val approved = uri.getBooleanQueryParameter("approved", false)
        val requestToken = uri.getQueryParameter("request_token")
        if (approved && requestToken != null) {
            sendEvent(LoginEvent.CreateSessionId(requestToken))
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

private fun RemoteUserAccountDetail.toUser(): UserAccount {
    return userAccount {
        avatar = this@toUser.avatar.toProtobuf()
        id = this@toUser.id.toLong()
        includeAdult = this@toUser.includeAdult
        name = this@toUser.name
        username = this@toUser.username
    }
}

private fun RemoteAvatar.toProtobuf(): Avatar {
    return avatar {
        gravatar = this@toProtobuf.gravatar.toProtobuf()
    }
}

private fun RemoteGravatar.toProtobuf(): Gravatar {
    return gravatar {
        hash = this@toProtobuf.hash
    }
}


