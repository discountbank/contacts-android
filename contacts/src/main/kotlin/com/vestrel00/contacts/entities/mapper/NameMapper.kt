package com.vestrel00.contacts.entities.mapper

import com.vestrel00.contacts.entities.MutableName
import com.vestrel00.contacts.entities.cursor.NameCursor

internal class NameMapper(private val nameCursor: NameCursor) {

    val name: MutableName
        get() = MutableName(
            id = nameCursor.id,
            rawContactId = nameCursor.rawContactId,
            contactId = nameCursor.contactId,

            displayName = nameCursor.displayName,

            givenName = nameCursor.givenName,
            middleName = nameCursor.middleName,
            familyName = nameCursor.familyName,

            prefix = nameCursor.prefix,
            suffix = nameCursor.suffix,

            phoneticGivenName = nameCursor.phoneticGivenName,
            phoneticMiddleName = nameCursor.phoneticMiddleName,
            phoneticFamilyName = nameCursor.phoneticFamilyName
        )
}