package com.davidluna.architectcoders2024.app.ui.common

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    modifier: Modifier = Modifier,
    topLevel: Boolean,
    hideAppBar: Boolean,
    title: String = stringResource(id = R.string.app_name),
    onNavigationIconClick: () -> Unit
) {
    if (hideAppBar) return
    Card(
        shape = RectangleShape,
        elevation = cardElevation(defaultElevation = 8.dp)
    ) {
        TopAppBar(
            title = { Text(text = title) },
            modifier = modifier,
            navigationIcon = {
                if (!topLevel) {
                    val icon = /*if (topLevel) Icons.Default.Menu else*/ Rounded.ArrowBack
                    IconButton(onClick = {
                        onNavigationIconClick()
                    }) {
                        Icon(imageVector = icon, contentDescription = icon.name)
                    }
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
                ) {}
            },
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues))
        }
    }
}