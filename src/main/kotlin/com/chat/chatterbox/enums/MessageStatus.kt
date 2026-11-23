package com.chat.chatterbox.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class MessageStatus {
    ACTIVE, SUPERSEDED, DELETED
}

@Converter(autoApply = true)
class MessageStatusConverter : AttributeConverter<MessageStatus, String> {
    override fun convertToDatabaseColumn(attribute: MessageStatus): String? = when (attribute) {
        MessageStatus.ACTIVE -> "active"
        MessageStatus.SUPERSEDED -> "superseded"
        MessageStatus.DELETED -> "deleted"
    }

    override fun convertToEntityAttribute(dbData: String?): MessageStatus? = when (dbData) {
        "active" -> MessageStatus.ACTIVE
        "superseded"  -> MessageStatus.SUPERSEDED
        "deleted" -> MessageStatus.DELETED
        else       -> null
    }
}