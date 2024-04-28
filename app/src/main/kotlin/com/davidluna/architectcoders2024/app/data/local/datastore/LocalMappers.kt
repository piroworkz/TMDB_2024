package com.davidluna.architectcoders2024.app.data.local.datastore

import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.protodatastore.ProtoSession
import com.davidluna.protodatastore.ProtoSessionId
import com.davidluna.protodatastore.ProtoUserAccount
import com.davidluna.protodatastore.copy

fun ProtoSession.toProto(
    sessionId: String
) = copy { auth = auth.copy { this.sessionId = sessionId } }

fun ProtoSession.toProto(user: UserAccount) = copy {
    this.user = this.user.copy {
        id = user.id.toLong()
        name = user.name
        username = user.username
    }
}

fun ProtoUserAccount.toDomain(): UserAccount {
    return UserAccount(
        id = id.toInt(),
        name = name,
        username = username,
    )
}

fun ProtoSessionId.toDomain(): SessionId = SessionId(sessionId)
