package com.chat.chatterbox.entities

import com.chat.chatterbox.enums.UserRole
import com.chat.chatterbox.enums.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    @Generated(event = [EventType.INSERT])
    val id: UUID? = null,

    @Email
    @Column(name = "email", nullable = false)
    val email:String,

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = false)
    val lastName: String,

    @Column(name = "role", nullable = false)
    val role: UserRole,

    @Column(name = "status", nullable = false)
    val status: UserStatus = UserStatus.ACTIVE,

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    @Generated(event = [EventType.INSERT])
    val createdAt: Instant? = null,
)