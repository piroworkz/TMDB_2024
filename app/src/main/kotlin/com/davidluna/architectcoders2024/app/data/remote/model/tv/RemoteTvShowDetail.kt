package com.davidluna.architectcoders2024.app.data.remote.model.tv

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteGenre
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteProductionCompany
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteProductionCountry
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteSpokenLanguage
import com.google.gson.annotations.SerializedName

data class RemoteTvShowDetail(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("created_by")
    val createdBy: List<RemoteCreatedBy>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    val genres: List<RemoteGenre>,
    val homepage: String,
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: RemoteLastEpisodeToAir,
    val name: String,
    val networks: List<RemoteTvNetwork>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<RemoteProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<RemoteProductionCountry>,
    @SerializedName("seasons")
    val seasons: List<RemoteSeason>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<RemoteSpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
)