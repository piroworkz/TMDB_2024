package com.davidluna.architectcoders2024.app.data.remote.model.tv

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteGenre
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteProductionCompany
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteProductionCountry
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteSpokenLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTvShowDetail(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("created_by")
    val createdBy: List<RemoteCreatedBy>,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerialName("first_air_date")
    val firstAirDate: String,
    @SerialName("genres")
    val genres: List<RemoteGenre>,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("id")
    val id: Int,
    @SerialName("in_production")
    val inProduction: Boolean,
    @SerialName("languages")
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: RemoteLastEpisodeToAir,
    @SerialName("name")
    val name: String,
    @SerialName("networks")
    val networks: List<RemoteTvNetwork>,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: String,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<RemoteProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<RemoteProductionCountry>,
    @SerialName("seasons")
    val seasons: List<RemoteSeason>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<RemoteSpokenLanguage>,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("type")
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)