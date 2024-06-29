package com.davidluna.architectcoders2024.media_data_framework.remote.datasources

import com.davidluna.architectcoders2024.core_domain.core_entities.buildModel
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteCast
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteContentDetail
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteGenre
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteImage
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteResults
import com.davidluna.media_domain.media_domain_entities.Cast
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Genre
import com.davidluna.media_domain.media_domain_entities.Image
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_entities.Results


fun RemoteResults<RemoteMedia>.toDomain(): Results<Media> = Results(
    page = page,
    results = results.map { it.toDomain() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun RemoteMedia.toDomain(): Media = Media(
    id = id,
    title = title ?: name ?: "",
    posterPath = posterPath?.buildModel() ?: "",
)

fun RemoteContentDetail.toDomain(): Details = Details(
    genres = genres.map { it.toDomain() },
    id = id,
    overview = overview,
    posterPath = posterPath.buildModel("w500"),
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

