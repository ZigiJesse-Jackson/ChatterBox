package com.chat.chatterbox.repositories

import com.chat.chatterbox.entities.Chat
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRepository : JpaRepository<Chat, UUID> {

}