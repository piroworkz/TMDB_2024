package com.davidluna.tmdb.core_ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun ErrorDialogView(
    error: AppError.Message?,
    onDismissRequest: () -> Unit,
) {
    if (error == null) return

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(Dimens.margins.large)
            .shadow(elevation = Dimens.margins.small, shape = MaterialTheme.shapes.large),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.click_closed),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth,
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.error_dialog_title),
                modifier = Modifier.padding(Dimens.margins.medium),
                color = colorScheme.error,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.padding(Dimens.margins.medium))

            Text(
                text = error.description,
                modifier = Modifier.padding(Dimens.margins.large),
                color = colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.padding(Dimens.margins.large))

            TextButton(onClick = { onDismissRequest() }) {
                Text(
                    text = stringResource(id = R.string.btn_dismiss_message),
                    modifier = Modifier
                        .padding(Dimens.margins.large),
                    color = colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End
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
    com.davidluna.tmdb.core_ui.theme.TmdbTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ErrorDialogView(
                error = AppError.Message(
                    AppErrorCode.BAD_REQUEST,
                    "Invalid API key: You must be granted a valid key."
                ),
                onDismissRequest = {},
            )
        }
    }
}