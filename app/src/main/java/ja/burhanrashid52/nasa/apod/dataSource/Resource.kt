package ja.burhanrashid52.nasa.apod.dataSource

/**
 * Represents a data bound resource. Each subclass represents the resource's   state:
 * - [Loading]: the resource is being retrieved from data source.
 * - [Success]: the resource has been retrieved (available in [Success.data] field)
 * - [Failure]: the resource retrieving has failed (throwable available in [Failure.throwable]
 * field)
*/
sealed class Resource<out T> {
 class Loading<out T> : Resource<T>()
 data class Success<out T>(val data: T) : Resource<T>()
 data class Failure<out T>(val throwable: Throwable) : Resource<T>()
}