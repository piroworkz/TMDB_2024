package com.davidluna.tmdb.fakes

import androidx.paging.PagingData
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteCredits
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteImages
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteMedia
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteMediaDetail
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteResults
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteVideos
import com.davidluna.tmdb.media_data.framework.remote.model.toDomain
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.test_shared.reader.Reader
import com.davidluna.tmdb.test_shared.reader.TestConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

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

val empty = emptyFlow<Flow<PagingData<Media>>>()