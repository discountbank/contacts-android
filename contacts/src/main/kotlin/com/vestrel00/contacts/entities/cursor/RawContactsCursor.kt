package com.vestrel00.contacts.entities.cursor

import android.database.Cursor
import com.vestrel00.contacts.Fields
import com.vestrel00.contacts.entities.INVALID_ID

/**
 * Retrieves [Fields.RawContacts] data from the given [cursor].
 *
 * This does not modify the [cursor] position. Moving the cursor may result in different attribute
 * values.
 */
internal class RawContactsCursor(private val cursor: Cursor) : RawContactIdCursor {

    override val contactId: Long
        get() = cursor.getLong(Fields.RawContacts.ContactId) ?: INVALID_ID

    override val rawContactId: Long
        get() = cursor.getLong(Fields.RawContacts.Id) ?: INVALID_ID

    val accountName: String?
        get() = cursor.getString(Fields.RawContacts.AccountName)

    val accountType: String?
        get() = cursor.getString(Fields.RawContacts.AccountType)
}