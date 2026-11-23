package com.chat.chatterbox.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class ChatType {
    DM, GROUP_CLOSED, GROUP_OPEN
}

@Converter(autoApply = true)
class ChatTypeConverter : AttributeConverter<ChatType, String> {
    override fun convertToDatabaseColumn(attribute: ChatType): String = when (attribute) {
        ChatType.DM -> "dm"
        ChatType.GROUP_CLOSED -> "group_closed"
        ChatType.GROUP_OPEN -> "group_open"
    }

    override fun convertToEntityAttribute(dbData: String?): ChatType? = when (dbData) {
        "dm" -> ChatType.DM
        "group_closed" -> ChatType.GROUP_CLOSED
        "group_open" -> ChatType.GROUP_OPEN
        else -> null
    }
}