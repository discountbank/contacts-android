package contacts.async.util

import android.content.Context
import contacts.async.ASYNC_DISPATCHER
import com.vestrel00.contacts.entities.CommonDataEntity
import com.vestrel00.contacts.util.refresh
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Suspends the current coroutine, performs the operation in the given [coroutineContext], then
 * returns the result.
 *
 * Computations automatically stops if the parent coroutine scope / job is cancelled.
 *
 * See [CommonDataEntity.refresh].
 */
suspend fun <T : CommonDataEntity> T.refreshWithContext(
    context: Context, coroutineContext: CoroutineContext = ASYNC_DISPATCHER
): T? = withContext(coroutineContext) { refresh(context) { !isActive } }

/**
 * Creates a [CoroutineScope] with the given [coroutineContext], performs the operation in that
 * scope, then returns the [Deferred] result.
 *
 * Computations automatically stops if the parent coroutine scope / job is cancelled.
 *
 * See [CommonDataEntity.refresh].
 */
fun <T : CommonDataEntity> T.refreshAsync(
    context: Context, coroutineContext: CoroutineContext = ASYNC_DISPATCHER
): Deferred<T?> = CoroutineScope(coroutineContext).async { refresh(context) { !isActive } }