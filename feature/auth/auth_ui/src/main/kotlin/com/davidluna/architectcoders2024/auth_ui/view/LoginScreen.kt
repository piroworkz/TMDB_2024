package com.davidluna.architectcoders2024.auth_ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.OnUiReady
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginState
import com.davidluna.architectcoders2024.auth_ui.view.composables.IntentView
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.core_ui.theme.locals.Locals.dimensDp

@Composable
fun LoginScreen(
    state: LoginState,
    sendEvent: (event: LoginEvent) -> Unit
) {

    LaunchedEffect(key1 = Unit) { sendEvent(OnUiReady) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_v1),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(colorScheme.onPrimary.copy(alpha = 0.5f))
        )

        if (state.intent) {
            IntentView(state.token)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensDp.xLarge)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { sendEvent(LoginEvent.CreateRequestToken) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(dimensDp.medium)
                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier
                            .padding(horizontal = dimensDp.xLarge)
                    )
                }

                TextButton(
                    onClick = { sendEvent(LoginEvent.CreateGuestSession) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Login as Guest",
                        modifier = Modifier
                            .padding(horizontal = dimensDp.xLarge)
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
        ErrorDialogView(error = state.appError as? AppError.Message) {
            sendEvent(LoginEvent.ResetError)
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
            state = LoginState()
        ) { }
    }
}