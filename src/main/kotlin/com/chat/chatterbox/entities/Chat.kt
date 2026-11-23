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
@Table(name = "chats")
data class Chat(
    @Id
    @Generated(event = [EventType.INSERT])
    val Id: UUID? = null,

    @Column(name = "owner", nullable = false)
    val owner: UUID,

    @Column(name = "type", nullable = false)
    val type: ChatType,

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    @Generated(event = [EventType.INSERT])
    val createdAt: Instant? = null
)