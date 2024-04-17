package com.davidluna.architectcoders2024.app.ui.screens.master

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme
import com.davidluna.architectcoders2024.app.ui.theme.Secondary
import com.davidluna.architectcoders2024.app.ui.theme.Tertiary

@Composable
fun MoviesScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Secondary)
    ) {
        item {
            Text(
                text = "Title",
                modifier = Modifier
                    .padding(8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LazyRow(
                modifier = Modifier.wrapContentHeight()
            ) {
                items(10) {
                    var imageSize by remember { mutableStateOf(0.dp) }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Tertiary)
                            .sizeIn(
                                maxWidth = 200.dp,
                                maxHeight = 204.dp
                            )

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.film_mask),
                            contentDescription = "film reel"
                        )
                        Image(
                            painter = painterResource(id = R.drawable.film_mask),
                            contentDescription = "film reel",
                            modifier = Modifier.onGloballyPositioned {

                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MoviesScreenPreview() {
    ArchitectCoders2024Theme {
        MoviesScreen()
    }
}