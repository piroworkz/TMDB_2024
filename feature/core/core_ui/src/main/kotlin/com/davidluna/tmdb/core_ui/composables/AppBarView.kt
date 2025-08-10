package com.davidluna.tmdb.core_ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.tmdb.core_ui.theme.TmdbTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    topLevel: Boolean,
    onNavigationIconClick: () -> Unit = {},
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = cardElevation(defaultElevation = 8.dp),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            },
            navigationIcon = {
                val icon = if (topLevel) Icons.Default.Menu else Rounded.ArrowBack
                IconButton(
                    onClick = {
                        onNavigationIconClick()
                    },
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = icon.name
                    )
                }
            },
            colors = topAppBarColors(
                containerColor = colorScheme.background,
                titleContentColor = colorScheme.primary,
                navigationIconContentColor = colorScheme.primary,
                actionIconContentColor = colorScheme.primary
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
                    title = "Title",
                ) {}
            },
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues))
        }
    }
}