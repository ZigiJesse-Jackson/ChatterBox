package com.chat.chatterbox.entities

import com.chat.chatterbox.enums.MessageStatus
import com.chat.chatterbox.enums.MessageType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "message_versions")
data class MessageVersion(
    @Id
    @Generated(event = [EventType.INSERT])
    val Id: UUID? = null,

    @Column(name = "message_root", nullable = false)
    val messageRoot: UUID,

    @Column(name = "chat", nullable = false)
    val chat: UUID,

    @Column(name = "type", nullable = false)
    val type: MessageType,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "status")
    @Generated(event = [EventType.INSERT])
    val status: MessageStatus?,

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    @Generated(event = [EventType.INSERT])
    val createdAt: Instant? = null
)