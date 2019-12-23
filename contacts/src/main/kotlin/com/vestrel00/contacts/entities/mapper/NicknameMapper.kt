package com.vestrel00.contacts.entities.mapper

import com.vestrel00.contacts.entities.MutableNickname
import com.vestrel00.contacts.entities.cursor.NicknameCursor

internal class NicknameMapper(private val nicknameCursor: NicknameCursor) {

    val nickname: MutableNickname
        get() = MutableNickname(
            id = nicknameCursor.id,
            rawContactId = nicknameCursor.rawContactId,
            contactId = nicknameCursor.contactId,

            name = nicknameCursor.name
        )
}