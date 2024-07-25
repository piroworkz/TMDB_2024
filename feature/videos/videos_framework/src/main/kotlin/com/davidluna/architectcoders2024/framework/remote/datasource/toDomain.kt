package com.davidluna.architectcoders2024.framework.remote.datasource

import com.davidluna.architectcoders2024.framework.remote.model.RemoteVideo
import com.davidluna.architectcoders2024.videos_domain.entities.YoutubeVideo

fun RemoteVideo.toDomain(): YoutubeVideo {
    return YoutubeVideo(
        id = id,
        key = key,
        site = site,
        type = type,
        order = order
    )
}
