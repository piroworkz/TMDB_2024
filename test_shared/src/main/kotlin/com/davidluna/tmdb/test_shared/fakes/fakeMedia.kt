package com.davidluna.tmdb.test_shared.fakes

import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Genre
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_domain.entities.Results
import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo

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
    releaseDate = "01 January, 2022",
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