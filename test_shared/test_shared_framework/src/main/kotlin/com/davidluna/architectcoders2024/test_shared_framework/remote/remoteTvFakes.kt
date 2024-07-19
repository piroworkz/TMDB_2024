package com.davidluna.architectcoders2024.test_shared_framework.remote

import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteImages
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteMediaDetail
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteResults
import com.davidluna.architectcoders2024.test_shared_framework.utils.MockFiles
import com.davidluna.architectcoders2024.test_shared_framework.utils.Reader
import com.davidluna.architectcoders2024.videos_data_framework.remote.model.RemoteVideos

val fakeRemoteTvShows: RemoteResults<RemoteMedia> =
    Reader.fromJson<RemoteResults<RemoteMedia>>(MockFiles.TV_SHOW_LIST)

val fakeRemoteTvShowDetail: RemoteMediaDetail =
    Reader.fromJson<RemoteMediaDetail>(MockFiles.TV_SHOW_DETAIL)

val fakeRemoteTvShowCredits: RemoteCredits =
    Reader.fromJson<RemoteCredits>(MockFiles.TV_SHOW_CREDITS)

val fakeRemoteTvShowImages: RemoteImages =
    Reader.fromJson<RemoteImages>(MockFiles.TV_SHOW_IMAGES)

val fakeRemoteTvShowVideos: RemoteVideos =
    Reader.fromJson<RemoteVideos>(MockFiles.TV_SHOW_VIDEOS)
