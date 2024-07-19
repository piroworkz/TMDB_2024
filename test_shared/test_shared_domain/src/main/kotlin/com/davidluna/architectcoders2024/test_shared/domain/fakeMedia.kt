package com.davidluna.architectcoders2024.test_shared.domain

import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Cast
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Genre
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Image
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.MediaDetails
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Results
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo


val fakeMedia: List<Media> = (0..19).map {
    Media(id = it, posterPath = "oporteat", title = "vivamus")
}

val fakeMediaDetail: MediaDetails = MediaDetails(
    genres = listOf(
        Genre(id = 1, name = "commodo"),
        Genre(id = 2, name = "dolore"),
        Genre(id = 3, name = "dolor"),
    ),
    id = 9533,
    overview = "nulla",
    posterPath = "pericula",
    releaseDate = "sadipscing",
    tagline = "porta",
    title = "has",
    voteAverage = 2.3,
    hasVideo = false

)

val fakeCastList: List<Cast> = (0..20).map {
    Cast(
        character = "percipit",
        id = 9174,
        name = "Joanne Hanson",
        profilePath = null

    )
}

val fakeImages: List<Image> = (0..20).map {
    Image(filePath = "partiendo $it")
}

val fakeMovieVideos: List<YoutubeVideo> = (0..20).map {
    YoutubeVideo(
        id = "ultricies", key = "offend $it", site = "enim", type = "antiopam", order = 1845

    )
}

val fakeMediaResults = Results(
    page = 1, results = fakeMedia, totalPages = 1, totalResults = 20
)