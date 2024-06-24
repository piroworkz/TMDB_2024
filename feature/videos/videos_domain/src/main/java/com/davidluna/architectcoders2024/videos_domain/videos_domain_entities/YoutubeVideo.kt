package com.davidluna.architectcoders2024.videos_domain.videos_domain_entities

data class YoutubeVideo(
    val id: String,
    val key: String,
    val site: String,
    val type: String,
    val order: Int = 100
)