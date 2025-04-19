package com.davidluna.tmdb.media_framework.data.remote.datasources

import com.davidluna.tmdb.core_domain.entities.buildModel
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Genre
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_domain.entities.Results
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteCast
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteGenre
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteImage
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMediaDetail
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteResults


fun RemoteResults<RemoteMedia>.toDomain(): Results<Media> = Results(
    page = page,
    results = results
        .filter { it.posterPath != null }
        .map(com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia::toDomain),
    totalPages = totalPages,
    totalResults = totalResults
)

fun RemoteMedia.toDomain(): Media = Media(
    id = id,
    title = title ?: name ?: "",
    posterPath = posterPath?.buildModel() ?: "",
)

fun RemoteMediaDetail.toDomain(): MediaDetails = MediaDetails(
    genres = genres.map { it.toDomain() },
    id = id,
    overview = overview,
    posterPath = posterPath?.buildModel("w500") ?: "",
    releaseDate = releaseDate ?: firstAirDate ?: "",
    tagline = tagline,
    title = title ?: name ?: "",
    voteAverage = voteAverage,
    hasVideo = video
)

fun RemoteGenre.toDomain(): Genre = Genre(id = id, name = name)

fun RemoteCast.toDomain(): Cast = Cast(
    character = character,
    id = id,
    name = name,
    profilePath = profilePath?.buildModel()
)

fun RemoteImage.toDomain(): Image = Image(
    filePath = filePath.buildModel("w500"),
)

