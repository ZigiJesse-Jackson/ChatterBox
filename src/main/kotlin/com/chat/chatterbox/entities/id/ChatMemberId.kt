package com.chat.chatterbox.entities.id

import java.io.Serializable
import java.util.UUID

data class ChatMemberId (
    val chat: UUID,
    val user: UUID,
) : Serializable