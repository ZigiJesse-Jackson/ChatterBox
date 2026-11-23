package com.chat.chatterbox.repositories

import com.chat.chatterbox.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
}