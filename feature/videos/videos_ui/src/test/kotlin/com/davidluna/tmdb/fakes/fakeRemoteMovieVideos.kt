package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.videos_framework.data.remote.model.RemoteVideos
import com.davidluna.tmdb.test_shared.reader.TestConstants
import com.davidluna.tmdb.test_shared.reader.Reader

val fakeRemoteMovieVideos =
    Reader.fromJson<RemoteVideos>(TestConstants.MOVIE_VIDEOS)

val fakeRemoteTvShowVideos =
    Reader.fromJson<RemoteVideos>(TestConstants.TV_SHOW_VIDEOS)