package com.chat.chatterbox.entities

import com.chat.chatterbox.entities.id.ChatMemberId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "chat_members")
@IdClass(ChatMemberId::class)
data class ChatMember(

    @Id
    val chat: UUID,

    @Id
    val user: UUID,

    @Column(name = "last_read_message", nullable = false)
    val lastReadMessage: UUID,

    @Column(name = "joined_at")
    @Generated(event = [EventType.INSERT])
    val joinedAt: Instant? = null
)