package com.chat.chatterbox.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class UserStatus {
    ACTIVE, DEACTIVATED
}

@Converter(autoApply = true)
class UserStatusConverter: AttributeConverter<UserStatus, String> {
    override fun convertToDatabaseColumn(attribute: UserStatus): String? = when (attribute) {
        UserStatus.ACTIVE -> "active"
        UserStatus.DEACTIVATED -> "deactivated"
    }

    override fun convertToEntityAttribute(dbData: String?): UserStatus? = when (dbData) {
        "active" -> UserStatus.ACTIVE
        "deactivated" -> UserStatus.DEACTIVATED
        else          -> null
    }

}