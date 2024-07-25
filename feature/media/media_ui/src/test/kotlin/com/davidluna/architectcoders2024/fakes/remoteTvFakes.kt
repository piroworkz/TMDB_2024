package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.framework.remote.media.RemoteImages
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMediaDetail
import com.davidluna.architectcoders2024.framework.remote.media.RemoteResults
import com.davidluna.architectcoders2024.test_shared.reader.MockFiles
import com.davidluna.architectcoders2024.test_shared.reader.Reader

val fakeRemoteTvShows: RemoteResults<RemoteMedia> =
    Reader.fromJson<RemoteResults<RemoteMedia>>(MockFiles.TV_SHOW_LIST)

val fakeRemoteTvShowDetail: RemoteMediaDetail =
    Reader.fromJson<RemoteMediaDetail>(MockFiles.TV_SHOW_DETAIL)

val fakeRemoteTvShowCredits: RemoteCredits =
    Reader.fromJson<RemoteCredits>(MockFiles.TV_SHOW_CREDITS)

val fakeRemoteTvShowImages: RemoteImages =
    Reader.fromJson<RemoteImages>(MockFiles.TV_SHOW_IMAGES)
