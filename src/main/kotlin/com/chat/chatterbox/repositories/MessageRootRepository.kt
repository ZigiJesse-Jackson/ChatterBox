package com.chat.chatterbox.repositories

import com.chat.chatterbox.entities.MessageRoot
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MessageRootRepository: JpaRepository<MessageRoot, UUID> {
}