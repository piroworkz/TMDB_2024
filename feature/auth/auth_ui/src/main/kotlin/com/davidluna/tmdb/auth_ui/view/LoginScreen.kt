package com.davidluna.tmdb.auth_ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.tmdb.auth_ui.biometrics.rememberBiometricAuth
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent
import com.davidluna.tmdb.auth_ui.presenter.LoginViewModel
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.ErrorDialogView
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun LoginScreen(
    state: LoginViewModel.LoginState,
    sendEvent: (event: LoginEvent) -> Unit,
) {
    val bioState = rememberBiometricAuth()

    bioState.CanAuthenticateEffect(state.session) { sendEvent(it) }
    bioState.BioResultEffect { sendEvent(it) }
    bioState.BiometricsLaunchedEffect(state.launchBioPrompt)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_v1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(colorScheme.onPrimary.copy(alpha = 0.5f))
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(Dimens.margins.xLarge)
                .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { sendEvent(LoginEvent.LoginButtonClicked) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.isLoading.not(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(R.string.btn_login),
                    modifier = Modifier.padding(horizontal = Dimens.margins.xLarge)
                )
            }

            TextButton(
                onClick = { sendEvent(LoginEvent.GuestButtonCLicked) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.isLoading.not()
            ) {
                Text(
                    text = stringResource(id = R.string.btn_login_as_guest),
                    modifier = Modifier.padding(horizontal = Dimens.margins.xLarge)
                )
            }

            if (state.session?.id?.isNotEmpty() == true) {
                TextButton(
                    onClick = { sendEvent(LoginEvent.LaunchBioPrompt(true)) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isLoading.not(),
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_launch_biometrics),
                        modifier = Modifier.padding(horizontal = Dimens.margins.xLarge)
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        ErrorDialogView(error = state.appError as? AppError.Message) {
            if (state.launchBioPrompt) {
                sendEvent(LoginEvent.LaunchBioPrompt(false))
            }
            sendEvent(LoginEvent.SetAppError(null))
        }
    }
}


@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
private fun LoginScreenPreview() {
    TmdbTheme {
        LoginScreen(
            state = LoginViewModel.LoginState()
        ) { }
    }
}