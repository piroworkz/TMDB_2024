package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.videos_framework.data.remote.model.RemoteVideos
import com.davidluna.architectcoders2024.test_shared.reader.MockFiles
import com.davidluna.architectcoders2024.test_shared.reader.Reader

val fakeRemoteMovieVideos =
    Reader.fromJson<RemoteVideos>(MockFiles.MOVIE_VIDEOS)

val fakeRemoteTvShowVideos =
    Reader.fromJson<RemoteVideos>(MockFiles.TV_SHOW_VIDEOS)