package com.davidluna.architectcoders2024.core_data_framework.local.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.protodatastore.CONTENT_KIND
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.protodatastore.ProtoUserAccount
import com.davidluna.protodatastore.copy

fun ProtoPreferences.toDomain(): String =
    if (session.isGuest) {
        session.guest.guestSessionId
    } else {
        session.sessionId
    }

fun ProtoPreferences.setSessionId(sessionId: String): ProtoPreferences = toBuilder().apply {
    if (!session.isGuest) {
        setSession(
            session.toBuilder()
                .setSessionId(sessionId)
                .build()
        )
    } else {
        setSession(
            session.toBuilder()
                .setGuest(session.guest.toBuilder().setGuestSessionId(sessionId).build())
                .build()
        )
    }
}.build()

fun ProtoPreferences.setIsGuest(isGuest: Boolean): ProtoPreferences = toBuilder().apply {
    setSession(session.copy { this.isGuest = isGuest })
}.build()

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