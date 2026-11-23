package com.chat.chatterbox.entities

import com.chat.chatterbox.enums.ChatType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "message_roots")
data class MessageRoot(
    @Id
    @Generated(event = [EventType.INSERT])
    val Id: UUID? = null,

    @Column(name = "chat", nullable = false)
    val chat: UUID,

    @Column(name = "sender", nullable = false)
    val sender: UUID,

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    @Generated(event = [EventType.INSERT])
    val createdAt: Instant? = null
)