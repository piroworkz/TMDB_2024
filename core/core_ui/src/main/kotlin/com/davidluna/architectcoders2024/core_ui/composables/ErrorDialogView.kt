package com.davidluna.architectcoders2024.core_ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens

@Composable
fun ErrorDialogView(
    error: AppError.Message?,
    onDismissRequest: () -> Unit,
) {
    if (error == null) return
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(Dimens.margins.medium),
            colors = CardColors(
                containerColor = colorScheme.background,
                contentColor = colorScheme.error,
                disabledContainerColor = colorScheme.background,
                disabledContentColor = colorScheme.error
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.error, RectangleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Something went wrong",
                    modifier = Modifier.padding(Dimens.margins.medium),
                    color = colorScheme.onError,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black
                )
            }

            Text(
                text = error.description,
                modifier = Modifier.padding(Dimens.margins.large),
                color = colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Justify
            )

            Button(
                onClick = { onDismissRequest() },
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.secondary,
                )
            ) {
                Text(
                    text = "Press to dismiss",
                    modifier = Modifier.padding(Dimens.margins.medium),
                    color = colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ErrorDialogPreView() {
    com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ErrorDialogView(
                error = AppError.Message(
                    0,
                    "Invalid API key: You must be granted a valid key."
                ),
                onDismissRequest = {},
            )
        }
    }
}