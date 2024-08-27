package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.videos_framework.data.remote.model.RemoteVideos
import com.davidluna.architectcoders2024.test_shared.reader.TestConstants
import com.davidluna.architectcoders2024.test_shared.reader.Reader

val fakeRemoteMovieVideos =
    Reader.fromJson<RemoteVideos>(TestConstants.MOVIE_VIDEOS)

val fakeRemoteTvShowVideos =
    Reader.fromJson<RemoteVideos>(TestConstants.TV_SHOW_VIDEOS)