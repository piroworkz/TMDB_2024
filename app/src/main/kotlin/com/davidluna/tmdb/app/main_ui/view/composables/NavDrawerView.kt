package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag.DRAWER_VIEW
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.appGradient
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.core_ui.navigation.destination.DrawerItem

@Composable
fun NavDrawerView(
    isGuest: Boolean = false,
    user: com.davidluna.tmdb.core_domain.entities.UserAccount? = null,
    onSelected: (DrawerItem?) -> Unit,
) {

    val size = LocalConfiguration.current.screenWidthDp.dp / 5

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75F)
            .background(appGradient())
            .testTag(DRAWER_VIEW),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(Dimens.margins.xLarge))

        Card(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(appGradient())
                .testTag(CoreTag.USER_IMAGE_CARD),
            shape = CircleShape,
            colors = cardColors(
                containerColor = Color.Transparent,
                contentColor = com.davidluna.tmdb.core_ui.theme.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = com.davidluna.tmdb.core_ui.theme.White
            )
        ) {
            if (isGuest) {
                Image(
                    painter = painterResource(id = R.drawable.demo_thumb),
                    contentDescription = "guest photo",
                    modifier = Modifier.fillMaxSize()
                        .testTag(CoreTag.GUEST_IMAGE),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center
                )
            } else {
                AsyncImage(
                    model = user?.avatarPath,
                    contentDescription = "user photo",
                    modifier = Modifier.fillMaxSize()
                        .testTag(CoreTag.USER_IMAGE),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.margins.large))

        Text(
            text = user?.username ?: "Guest User",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.testTag(CoreTag.USER_NAME)
        )

        Spacer(modifier = Modifier.height(Dimens.margins.large))

        HorizontalDivider(
            thickness = Dimens.margins.large,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(Dimens.margins.large))

        DrawerItem.items.forEach { item ->

            Row(
                modifier = Modifier
                    .padding(Dimens.margins.medium)
                    .fillMaxWidth()
                    .clickable { onSelected(item) }
                    .testTag(CoreTag.DRAWER_ITEM),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.iconResource,
                    contentDescription = item.iconResource.name,
                    modifier = Modifier.testTag(CoreTag.DRAWER_ITEM_ICON),
                    tint = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.width(Dimens.margins.large))

                Text(
                    text = stringResource(id = item.titleResource),
                    modifier = Modifier
                        .padding(vertical = Dimens.margins.medium)
                        .testTag(CoreTag.DRAWER_ITEM_TITLE),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }

    }
}