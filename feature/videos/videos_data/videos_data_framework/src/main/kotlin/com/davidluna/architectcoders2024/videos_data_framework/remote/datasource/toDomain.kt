package com.davidluna.architectcoders2024.videos_data_framework.remote.datasource

import com.davidluna.architectcoders2024.videos_data_framework.remote.model.RemoteVideo
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo

fun RemoteVideo.toDomain(): YoutubeVideo {
    return YoutubeVideo(
        id = id,
        key = key,
        site = site,
        type = type,
        order = order
    )
}
