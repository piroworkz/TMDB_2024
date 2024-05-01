package com.davidluna.architectcoders2024.app.data.remote.datasources

import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteGuestSession
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteCast
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteGenre
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImage
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieDetail
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteVideo
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Genre
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.MovieDetail
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo
import com.davidluna.architectcoders2024.domain.session.GuestSession
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.TokenResponse
import com.davidluna.architectcoders2024.domain.session.UserAccount

fun RemoteResults<RemoteMovie>.toDomain(): Results<Movie> = Results(
    page = page,
    results = results.map { it.toDomain() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun RemoteMovie.toDomain(): Movie = Movie(
    id = id,
    title = title,
    posterPath = posterPath?.buildModel() ?: "",
)


fun RemoteUserAccountDetail.toDomain(): UserAccount = UserAccount(
    id = id,
    name = name,
    username = username,
    avatarPath = avatar.tmdb.avatarPath.buildModel()
)

fun RemoteSessionIdResponse.toDomain(): SessionId =
    SessionId(sessionId = sessionId)

fun LoginRequest.toRemote(): RemoteLoginRequest =
    RemoteLoginRequest(requestToken = requestToken)

fun RemoteTokenResponse.toDomain(): TokenResponse = TokenResponse(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success
)

fun RemoteVideo.toDomain() =
    YoutubeVideo(
        id = id,
        key = key,
        site = site,
        type = type,
        order = when (type.uppercase()) {
            "Trailer".uppercase() -> 1
            "Teaser".uppercase() -> 2
            else -> 3
        }
    )

fun RemoteMovieDetail.toDomain(): MovieDetail = MovieDetail(
    genres = genres.map { it.toDomain() },
    id = id,
    overview = overview,
    posterPath = posterPath.buildModel("w500"),
    releaseDate = releaseDate,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage
)

fun RemoteGenre.toDomain(): Genre = Genre(id = id, name = name)

fun RemoteCast.toDomain(): Cast = Cast(
    character = character,
    id = id,
    name = name,
    profilePath = profilePath?.buildModel()
)

fun RemoteImage.toDomain(): Image = Image(
    filePath = filePath.buildModel("w500"),
)

fun RemoteGuestSession.toDomain(): GuestSession = GuestSession(
    expiresAt = expiresAt,
    guestSessionId = guestSessionId
)

fun String.buildModel(width: String = "w185"): String = "https://image.tmdb.org/t/p/$width$this"
