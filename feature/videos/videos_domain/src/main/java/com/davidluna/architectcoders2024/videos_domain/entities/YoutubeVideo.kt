package com.davidluna.architectcoders2024.videos_domain.entities

data class YoutubeVideo(
    val id: String,
    val key: String,
    val site: String,
    val type: String,
    val order: Int = 100
)