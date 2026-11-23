package com.chat.chatterbox.repositories

import com.chat.chatterbox.entities.MessageVersion
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MessageVersionRepository: JpaRepository<MessageVersion, UUID> {
}