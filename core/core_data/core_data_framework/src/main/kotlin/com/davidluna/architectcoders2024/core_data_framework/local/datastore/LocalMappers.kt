package com.davidluna.architectcoders2024.core_data_framework.local.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.GuestSession
import com.davidluna.architectcoders2024.core_domain.core_entities.Session
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.protodatastore.CONTENT_KIND
import com.davidluna.protodatastore.ProtoGuestSessionId
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.protodatastore.ProtoUserAccount
import com.davidluna.protodatastore.copy

fun ProtoPreferences.toDomain(): Session =
    Session(
        id = session.id,
        guestSession = GuestSession(
            id = session.guest.id,
            expiresAt = session.guest.expiresAt,
            isGuest = session.guest.isGuest
        )
    )

fun ProtoPreferences.setSession(sessionId: Session): ProtoPreferences = toBuilder().apply {
    setSession(
        session.toBuilder()
            .setId(sessionId.id)
            .setGuest(
                setGuestSession(sessionId)
            )
            .build()
    )
}.build()

private fun ProtoPreferences.Builder.setGuestSession(sessionId: Session): ProtoGuestSessionId? =
    session.guest.toBuilder()
        .setId(sessionId.guestSession.id)
        .setIsGuest(sessionId.guestSession.isGuest)
        .setExpiresAt(sessionId.guestSession.expiresAt)
        .build()


fun ProtoPreferences.setUserAccount(user: UserAccount): ProtoPreferences =
    toBuilder()
        .apply {
            setUser(this.user.copy {
                id = user.id.toLong()
                name = user.name
                username = user.username
                avatarPath = user.avatarPath
            })
        }.build()

fun ProtoUserAccount.toDomain(): UserAccount {
    return UserAccount(
        id = id.toInt(),
        name = name,
        username = username,
        avatarPath = avatarPath
    )
}

fun ProtoPreferences.setContentKind(contentKind: ContentKind): ProtoPreferences =
    toBuilder().setContentKind(CONTENT_KIND.valueOf(contentKind.name)).build()