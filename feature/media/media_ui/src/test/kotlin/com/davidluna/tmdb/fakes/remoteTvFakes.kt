package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.media_framework.data.remote.media.RemoteCredits
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteImages
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMediaDetail
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteResults
import com.davidluna.tmdb.test_shared.reader.TestConstants
import com.davidluna.tmdb.test_shared.reader.Reader

val fakeRemoteTvShows: RemoteResults<RemoteMedia> =
    Reader.fromJson<RemoteResults<RemoteMedia>>(TestConstants.TV_SHOW_LIST)

val fakeRemoteTvShowDetail: RemoteMediaDetail =
    Reader.fromJson<RemoteMediaDetail>(TestConstants.TV_SHOW_DETAIL)

val fakeRemoteTvShowCredits: RemoteCredits =
    Reader.fromJson<RemoteCredits>(TestConstants.TV_SHOW_CREDITS)

val fakeRemoteTvShowImages: RemoteImages =
    Reader.fromJson<RemoteImages>(TestConstants.TV_SHOW_IMAGES)
