package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.media_data.framework.remote.model.RemoteCredits
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteImages
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteMedia
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteMediaDetail
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteResults
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
