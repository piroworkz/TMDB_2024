package com.davidluna.architectcoders2024.app.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.MainActivity
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.theme.DimensDp
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.ui.theme.White
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals
import com.davidluna.architectcoders2024.app.utils.log
import com.davidluna.architectcoders2024.domain.session.UserAccount

@Composable
fun NavDrawerView(
    isGuest: Boolean = false,
    user: UserAccount? = null,
    onSelected: () -> Unit
) {

    val size = LocalConfiguration.current.screenWidthDp.dp / 5

    user?.avatarPath?.log("user avatar")

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75F)
            .background(appGradient()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(Locals.dimensDp.xLarge))

        Card(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(appGradient()),
            shape = CircleShape,
            colors = cardColors(
                containerColor = Color.Transparent,
                contentColor = White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = White
            )
        ) {
            if (isGuest) {
                Image(
                    painter = painterResource(id = R.drawable.demo_thumb),
                    contentDescription = "guest photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center
                )
            } else {
                AsyncImage(
                    model = user?.avatarPath,
                    contentDescription = "user photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(Locals.dimensDp.large))
        Text(
            text = user?.username ?: "Guest User",
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(Locals.dimensDp.large))
        HorizontalDivider(
            thickness = Locals.dimensDp.large,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(Locals.dimensDp.large))

        DrawerDestinations.items.forEach { item ->

            Row(
                modifier = Modifier
                    .padding(Locals.dimensDp.medium)
                    .fillMaxWidth()
                    .clickable { onSelected() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.iconResource,
                    contentDescription = item.iconResource.name,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(Locals.dimensDp.large))
                Text(
                    text = stringResource(id = item.titleResource),
                    modifier = Modifier.padding(vertical = Locals.dimensDp.medium),
                    color = MaterialTheme.colorScheme.onPrimary
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
private fun NavDrawerPreView() {
    MainActivity().Locals(dimensDp = DimensDp()) {
        TmdbTheme {
            Box(
                modifier = Modifier
                    .background(appGradient())
                    .fillMaxSize()
            ) {
                NavDrawerView {}
            }
        }
    }
}