package com.vestrel00.contacts.entities.operation

import android.accounts.Account
import android.content.ContentProviderOperation
import com.vestrel00.contacts.RawContactsFields
import com.vestrel00.contacts.`in`
import com.vestrel00.contacts.entities.table.Table
import com.vestrel00.contacts.equalTo

private val TABLE = Table.RawContacts

/**
 * Builds [ContentProviderOperation]s for [Table.RawContacts].
 */
internal object RawContactsOperation {

    fun insert(rawContactAccount: Account?): ContentProviderOperation = newInsert(TABLE)
        /*
         * Passing in null account name and type is valid. It is the same behavior as the native
         * Android Contacts app when creating contacts when there are no available accounts. When an
         * account becomes available (or is already available), Android will automatically update
         * the RawContact name and type to an existing Account.
         */
        .withValue(RawContactsFields.AccountName, rawContactAccount?.name)
        .withValue(RawContactsFields.AccountType, rawContactAccount?.type)
        .build()

    fun deleteRawContact(rawContactId: Long): ContentProviderOperation = newDelete(TABLE)
        .withSelection(RawContactsFields.Id equalTo rawContactId)
        .build()

    fun deleteRawContacts(rawContactIds: Collection<Long>): ContentProviderOperation =
        newDelete(TABLE)
            .withSelection(RawContactsFields.Id `in` rawContactIds)
            .build()

    /*
     * Deleting all of the RawContact rows matching the Contacts._ID will result in the automatic
     * deletion of the Contacts row and associated Data rows.
     */
    fun deleteRawContactsWithContactId(contactId: Long): ContentProviderOperation =
        newDelete(TABLE)
            .withSelection(RawContactsFields.ContactId equalTo contactId)
            .build()

    fun deleteRawContactsWithContactIds(contactIds: Collection<Long>): ContentProviderOperation =
        newDelete(TABLE)
            .withSelection(RawContactsFields.ContactId `in` contactIds)
            .build()
}