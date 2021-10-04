package contacts.async

import contacts.core.Update
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Suspends the current coroutine, performs the operation in the given [context], then returns the
 * result.
 *
 * Computations automatically stops if the parent coroutine scope / job is cancelled.
 *
 * See [Update.commit].
 */
suspend fun Update.commitWithContext(context: CoroutineContext = ASYNC_DISPATCHER): Update.Result =
    withContext(context) { commit { !isActive } }

/**
 * Creates a [CoroutineScope] with the given [context], performs the operation in that scope, then
 * returns the [Deferred] result.
 *
 * Computations automatically stops if the parent coroutine scope / job is cancelled.
 *
 * See [Update.commit].
 */
fun Update.commitAsync(context: CoroutineContext = ASYNC_DISPATCHER): Deferred<Update.Result> =
    CoroutineScope(context).async { commit { !isActive } }