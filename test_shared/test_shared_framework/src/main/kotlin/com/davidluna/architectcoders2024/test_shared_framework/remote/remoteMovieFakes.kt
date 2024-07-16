package com.davidluna.architectcoders2024.test_shared_framework.remote

import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteImages
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteMediaDetail
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteResults
import com.davidluna.architectcoders2024.test_shared_framework.utils.MockFiles
import com.davidluna.architectcoders2024.test_shared_framework.utils.Reader
import com.davidluna.architectcoders2024.videos_data_framework.remote.model.RemoteVideos

val fakeRemoteMedia: RemoteResults<RemoteMedia> =
    Reader.fromJson<RemoteResults<RemoteMedia>>(MockFiles.MOVIE_LIST)

val fakeRemoteMediaDetail: RemoteMediaDetail =
    Reader.fromJson<RemoteMediaDetail>(MockFiles.MOVIE_DETAIL)

val fakeRemoteCredits: RemoteCredits =
    Reader.fromJson<RemoteCredits>(MockFiles.MOVIE_CREDITS)

val fakeRemoteImages: RemoteImages =
    Reader.fromJson<RemoteImages>(MockFiles.MOVIE_IMAGES)

val fakeRemoteMovieVideos: RemoteVideos =
    Reader.fromJson<RemoteVideos>(MockFiles.MOVIE_VIDEOS)
