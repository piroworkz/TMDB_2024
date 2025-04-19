package com.davidluna.tmdb.videos_framework.data.remote.datasource

import com.davidluna.tmdb.videos_domain.entities.YoutubeVideo
import com.davidluna.tmdb.videos_framework.data.remote.model.RemoteVideo

fun RemoteVideo.toDomain(): YoutubeVideo {
    return YoutubeVideo(
        id = id,
        key = key,
        site = site,
        type = type,
        order = order
    )
}
