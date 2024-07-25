package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.framework.remote.datasources.toDomain
import com.davidluna.architectcoders2024.framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.framework.remote.media.RemoteImages
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMediaDetail
import com.davidluna.architectcoders2024.framework.remote.media.RemoteResults
import com.davidluna.architectcoders2024.framework.remote.model.RemoteVideos
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.test_shared.reader.MockFiles
import com.davidluna.architectcoders2024.test_shared.reader.Reader

val fakeRemoteMedia: RemoteResults<RemoteMedia> =
    Reader.fromJson<RemoteResults<RemoteMedia>>(MockFiles.MOVIE_LIST)

val fakeRemoteMediaDetail: RemoteMediaDetail =
    Reader.fromJson<RemoteMediaDetail>(MockFiles.MOVIE_DETAIL)

val fakeRemoteCredits: RemoteCredits =
    Reader.fromJson<RemoteCredits>(MockFiles.MOVIE_CREDITS)

val fakeRemoteImages: RemoteImages =
    Reader.fromJson<RemoteImages>(MockFiles.MOVIE_IMAGES)

val fakeMediaItem: Media = fakeRemoteMedia.results.first().toDomain()

val fakeRemoteMovieVideos =
    Reader.fromJson<RemoteVideos>(MockFiles.MOVIE_VIDEOS)

val fakeRemoteTvShowVideos =
    Reader.fromJson<RemoteVideos>(MockFiles.TV_SHOW_VIDEOS)