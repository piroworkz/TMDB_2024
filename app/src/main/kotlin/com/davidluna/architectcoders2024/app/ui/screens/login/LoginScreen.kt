package com.davidluna.architectcoders2024.app.ui.screens.login

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.common.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

@Composable
fun LoginScreen(
    state: LoginViewModel.State,
    sendEvent: (event: LoginEvent) -> Unit
) {

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
            WebView(state.token) { sendEvent(LoginEvent.AskForPermission) }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { sendEvent(LoginEvent.CreateRequestToken) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                    )
                }

            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
        ErrorDialogView(error = state.appError) {
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
    ArchitectCoders2024Theme {
        LoginScreen(
            state = LoginViewModel.State()
        ) {

        }
    }
}