package com.chat.chatterbox.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class UserRole {
    EMPLOYEE, MANAGER
}

@Converter(autoApply = true)
class UserRoleConverter : AttributeConverter<UserRole, String> {
    override fun convertToDatabaseColumn(attribute: UserRole): String? = when (attribute) {
        UserRole.EMPLOYEE -> "employee"
        UserRole.MANAGER    -> "manager"
    }

    override fun convertToEntityAttribute(dbData: String?): UserRole? = when (dbData) {
        "employee" -> UserRole.EMPLOYEE
        "manager"  -> UserRole.MANAGER
        else       -> null
    }
}