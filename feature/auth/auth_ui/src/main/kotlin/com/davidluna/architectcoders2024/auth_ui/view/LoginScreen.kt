package com.davidluna.architectcoders2024.auth_ui.view

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
import com.davidluna.architectcoders2024.auth_ui.biometrics.BiometricsLaunchedEffect
import com.davidluna.architectcoders2024.auth_ui.biometrics.rememberBiometricAuth
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginViewModel
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens

@Composable
fun LoginScreen(
    state: LoginViewModel.LoginState,
    sendEvent: (event: LoginEvent) -> Unit
) {

    val bioState = rememberBiometricAuth()

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.margins.xLarge)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { sendEvent(LoginEvent.CreateRequestToken) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(R.string.btn_login),
                    modifier = Modifier.padding(horizontal = Dimens.margins.xLarge)
                )
            }

            TextButton(
                onClick = { sendEvent(LoginEvent.CreateGuestSession) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.btn_login_as_guest),
                    modifier = Modifier.padding(horizontal = Dimens.margins.xLarge)
                )
            }

            if (state.sessionExists && bioState.canAuthenticate.value && !state.bioSuccess) {
                BiometricsLaunchedEffect(biometricAuthState = bioState) {
                    sendEvent(it)
                }

                TextButton(
                    onClick = { bioState.launchPrompt() },
                    modifier = Modifier.fillMaxWidth()
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
            sendEvent(LoginEvent.SetError(null))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginScreenPreview() {
    TmdbTheme {
        LoginScreen(
            state = LoginViewModel.LoginState()
        ) { }
    }
}