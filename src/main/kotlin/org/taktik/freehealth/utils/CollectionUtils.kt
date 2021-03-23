package org.taktik.freehealth.utils


/**
 * Returns a list containing the results of applying the given [transform] function
 * to each element in the original collection.
 */
inline fun <T, R> Iterable<T>.mapFirstNotNull(transform: (T) -> R): R? {
    for (item in this) transform(item)?.let { return it }
    return null
}
