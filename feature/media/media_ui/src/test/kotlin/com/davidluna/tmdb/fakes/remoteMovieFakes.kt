package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.media_framework.data.remote.datasources.toDomain
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteCredits
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteImages
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMediaDetail
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteResults
import com.davidluna.tmdb.videos_framework.data.remote.model.RemoteVideos
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.test_shared.reader.TestConstants
import com.davidluna.tmdb.test_shared.reader.Reader

val fakeRemoteMedia: RemoteResults<RemoteMedia> =
    Reader.fromJson<RemoteResults<RemoteMedia>>(TestConstants.MOVIE_LIST)

val fakeRemoteMediaDetail: RemoteMediaDetail =
    Reader.fromJson<RemoteMediaDetail>(TestConstants.MOVIE_DETAIL)

val fakeRemoteCredits: RemoteCredits =
    Reader.fromJson<RemoteCredits>(TestConstants.MOVIE_CREDITS)

val fakeRemoteImages: RemoteImages =
    Reader.fromJson<RemoteImages>(TestConstants.MOVIE_IMAGES)

val fakeMediaItem: Media = fakeRemoteMedia.results.first().toDomain()

val fakeRemoteMovieVideos =
    Reader.fromJson<RemoteVideos>(TestConstants.MOVIE_VIDEOS)

val fakeRemoteTvShowVideos =
    Reader.fromJson<RemoteVideos>(TestConstants.TV_SHOW_VIDEOS)