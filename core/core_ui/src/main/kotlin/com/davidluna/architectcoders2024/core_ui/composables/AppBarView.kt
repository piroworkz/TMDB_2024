package com.davidluna.architectcoders2024.core_ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Rounded
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.core_domain.entities.tags.CoreTag
import com.davidluna.architectcoders2024.core_domain.entities.tags.CoreTag.APP_BAR_ICON
import com.davidluna.architectcoders2024.core_domain.entities.tags.CoreTag.APP_BAR_ICON_BUTTON
import com.davidluna.architectcoders2024.core_domain.entities.tags.CoreTag.APP_BAR_TITLE
import com.davidluna.architectcoders2024.core_domain.entities.tags.CoreTag.TOP_APP_BAR
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    modifier: Modifier = Modifier,
    title: String?,
    topLevel: Boolean,
    hideAppBar: Boolean,
    onNavigationIconClick: () -> Unit,
) {
    if (hideAppBar) return
    Card(
        shape = RectangleShape,
        elevation = cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .testTag(CoreTag.APP_BAR_VIEW)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title ?: stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .testTag(APP_BAR_TITLE),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            },
            modifier = Modifier
                .testTag(TOP_APP_BAR),
            navigationIcon = {
                val icon = if (topLevel) Icons.Default.Menu else Rounded.ArrowBack
                IconButton(
                    onClick = {
                        onNavigationIconClick()
                    },
                    modifier = Modifier.testTag(APP_BAR_ICON_BUTTON)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = icon.name,
                        modifier = Modifier.testTag(APP_BAR_ICON)
                    )
                }
            },
            colors = topAppBarColors(
                containerColor = colorScheme.primary,
                titleContentColor = colorScheme.onPrimary,
                navigationIconContentColor = colorScheme.onPrimary,
                actionIconContentColor = colorScheme.onPrimary
            ),
            scrollBehavior = pinnedScrollBehavior()
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AppBarPreView() {
    TmdbTheme {
        Scaffold(
            topBar = {
                AppBarView(
                    topLevel = true,
                    hideAppBar = false,
                    title = "Title",
                ) {}
            },
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues))
        }
    }
}