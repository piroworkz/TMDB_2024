package com.davidluna.architectcoders2024.app.ui.screens.detail.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieCredits
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.screens.master.views.ReelTitleView
import com.davidluna.architectcoders2024.app.ui.screens.master.views.buildModel

@Composable
fun MovieCastView(
    movieCredits: RemoteMovieCredits?,
) {
    val width = LocalConfiguration.current.screenWidthDp.dp

    ReelTitleView("CAST")
    movieCredits?.let {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {

            items(movieCredits.cast.filter { it.profilePath.isNullOrEmpty().not() }) { cast ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(width / 2.3F)
                        .clip(MaterialTheme.shapes.large)
                        .background(appGradient()),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = cast.profilePath!!.buildModel(),
                        contentDescription = "${cast.name} Picture",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = "${cast.name} as ${cast.character}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

