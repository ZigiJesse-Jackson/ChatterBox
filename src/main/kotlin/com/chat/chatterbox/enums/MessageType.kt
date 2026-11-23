package com.chat.chatterbox.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class MessageType {
    TEXT, IMAGE, VIDEO
}

@Converter(autoApply = true)
class MessageTypeConverter : AttributeConverter<MessageType, String> {
    override fun convertToDatabaseColumn(attribute: MessageType): String? = when (attribute) {
        MessageType.TEXT -> "text"
        MessageType.IMAGE -> "image"
        MessageType.VIDEO -> "video"
    }

    override fun convertToEntityAttribute(dbData: String?): MessageType? = when (dbData) {
        "text" -> MessageType.TEXT
        "image" -> MessageType.IMAGE
        "video" -> MessageType.VIDEO
        else -> null
    }
}