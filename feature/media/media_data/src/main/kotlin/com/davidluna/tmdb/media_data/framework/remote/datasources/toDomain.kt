package com.davidluna.tmdb.media_data.framework.remote.datasources

import com.davidluna.tmdb.media_data.framework.remote.model.RemoteVideo
import com.davidluna.tmdb.media_domain.entities.Video

fun RemoteVideo.toDomain(): Video {
    return Video(
        id = id,
        key = key,
        site = site,
        type = type,
        order = order
    )
}