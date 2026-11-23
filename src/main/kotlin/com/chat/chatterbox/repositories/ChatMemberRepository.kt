package com.chat.chatterbox.repositories

import com.chat.chatterbox.entities.ChatMember
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatMemberRepository : JpaRepository<ChatMember, UUID> {
}