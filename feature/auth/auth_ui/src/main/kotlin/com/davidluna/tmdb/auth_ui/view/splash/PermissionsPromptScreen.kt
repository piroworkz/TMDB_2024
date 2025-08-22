package com.davidluna.tmdb.auth_ui.view.splash

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PermissionsPromptScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    launchPermissionsPrompt: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.margins.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.permissions_image),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = Dimens.margins.medium)
        )

        Text(
            text = "Stay in the loop, with your privacy in mind.",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = Dimens.margins.medium)
        )

        Text(
            text = "Turn on notifications and share your approximate location to see releases and recommendations for your region. Location is used only to tailor content and can be adjusted anytime in Settings.",
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(bottom = Dimens.margins.medium)
        )

        with(sharedTransitionScope) {
            Image(
                painter = painterResource(id = R.drawable.logo_v1),
                contentDescription = null,
                modifier = Modifier
                    .alpha(.3f)
                    .fillMaxSize()
                    .padding(Dimens.margins.xLarge)
                    .weight(1F)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState("tmdb_logo"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.margins.xLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { onDismiss() },
                content = {
                    Text("Not now")
                }
            )

            Button(
                onClick = { launchPermissionsPrompt() },
                content = {
                    Text("Enable")
                }
            )
        }
    }
}