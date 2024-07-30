package com.davidluna.architectcoders2024.framework.remote.datasources

import com.davidluna.architectcoders2024.core_domain.entities.buildModel
import com.davidluna.architectcoders2024.framework.remote.media.RemoteCast
import com.davidluna.architectcoders2024.framework.remote.media.RemoteGenre
import com.davidluna.architectcoders2024.framework.remote.media.RemoteImage
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMediaDetail
import com.davidluna.architectcoders2024.framework.remote.media.RemoteResults
import com.davidluna.architectcoders2024.media_domain.entities.Cast
import com.davidluna.architectcoders2024.media_domain.entities.Genre
import com.davidluna.architectcoders2024.media_domain.entities.Image
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.media_domain.entities.MediaDetails
import com.davidluna.architectcoders2024.media_domain.entities.Results


fun RemoteResults<RemoteMedia>.toDomain(): Results<Media> = Results(
    page = page,
    results = results
        .filter { it.posterPath != null }
        .map(RemoteMedia::toDomain),
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

