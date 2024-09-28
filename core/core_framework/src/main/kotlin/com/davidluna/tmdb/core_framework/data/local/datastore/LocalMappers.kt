package com.davidluna.tmdb.core_framework.data.local.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.protodatastore.CONTENT_KIND
import com.davidluna.protodatastore.ProtoGuestSessionId
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.protodatastore.ProtoUserAccount
import com.davidluna.protodatastore.copy

fun ProtoPreferences.toDomain(): com.davidluna.tmdb.core_domain.entities.Session =
    com.davidluna.tmdb.core_domain.entities.Session(
        id = session.id,
        guestSession = com.davidluna.tmdb.core_domain.entities.GuestSession(
            id = session.guest.id,
            expiresAt = session.guest.expiresAt
        )
    )

fun ProtoPreferences.setSession(sessionId: com.davidluna.tmdb.core_domain.entities.Session): ProtoPreferences = toBuilder().apply {
    setSession(
        session.toBuilder()
            .setId(sessionId.id)
            .setGuest(
                setGuestSession(sessionId)
            )
            .build()
    )
}.build()

private fun ProtoPreferences.Builder.setGuestSession(sessionId: com.davidluna.tmdb.core_domain.entities.Session): ProtoGuestSessionId? =
    session.guest.toBuilder()
        .setId(sessionId.guestSession.id)
        .setExpiresAt(sessionId.guestSession.expiresAt)
        .build()


fun ProtoPreferences.setUserAccount(user: com.davidluna.tmdb.core_domain.entities.UserAccount): ProtoPreferences =
    toBuilder()
        .apply {
            setUser(this.user.copy {
                id = user.id.toLong()
                name = user.name
                username = user.username
                avatarPath = user.avatarPath
            })
        }.build()

fun ProtoUserAccount.toDomain(): com.davidluna.tmdb.core_domain.entities.UserAccount {
    return com.davidluna.tmdb.core_domain.entities.UserAccount(
        id = id.toInt(),
        name = name,
        username = username,
        avatarPath = avatarPath
    )
}

fun ProtoPreferences.setContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind): ProtoPreferences =
    toBuilder().setContentKind(CONTENT_KIND.valueOf(contentKind.name)).build()