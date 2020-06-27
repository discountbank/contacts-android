package com.vestrel00.contacts.util

import android.content.Context
import com.vestrel00.contacts.ContactsPermissions
import com.vestrel00.contacts.entities.ContactEntity
import com.vestrel00.contacts.entities.MutableOptions
import com.vestrel00.contacts.entities.operation.OptionsOperation

/**
 * Updates this [ContactEntity.options] with the given [options].
 *
 * Note that changes to the options of a RawContact may affect the options of the parent Contact.
 * On the other hand, changes to the options of the parent Contact will be propagated to all child
 * RawContact options.
 *
 * ## Permissions
 *
 * This requires the [ContactsPermissions.WRITE_PERMISSION] and
 * [com.vestrel00.contacts.accounts.AccountsPermissions.GET_ACCOUNTS_PERMISSION].
 *
 * ## Thread Safety
 *
 * This should be called in a background thread to avoid blocking the UI thread.
 */
// [ANDROID X] @WorkerThread (not using annotation to avoid dependency on androidx.annotation)
fun ContactEntity.setOptions(context: Context, options: MutableOptions): Boolean {
    val contactId = id

    if (!ContactsPermissions(context).canInsertUpdateDelete() || contactId == null) {
        return false
    }

    return context.contentResolver.applyBatch(
        OptionsOperation().updateContactOptions(contactId, options)
    ) != null
}

/**
 * Updates this [ContactEntity.options] in [update]. If this contact has null options, a new blank
 * options will be used in [update].
 *
 * Note that changes to the options of a RawContact may affect the options of the parent Contact.
 * On the other hand, changes to the options of the parent Contact will be propagated to all child
 * RawContact options.
 *
 * ## Permissions
 *
 * This requires the [ContactsPermissions.WRITE_PERMISSION] and
 * [com.vestrel00.contacts.accounts.AccountsPermissions.GET_ACCOUNTS_PERMISSION].
 *
 * ## Thread Safety
 *
 * This should be called in a background thread to avoid blocking the UI thread.
 */
// [ANDROID X] @WorkerThread (not using annotation to avoid dependency on androidx.annotation)
fun ContactEntity.updateOptions(context: Context, update: MutableOptions.() -> Unit): Boolean {
    val optionsToUse = options?.toMutableOptions() ?: MutableOptions()
    optionsToUse.update()
    return setOptions(context, optionsToUse)
}