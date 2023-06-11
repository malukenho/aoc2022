package Monads

sealed class Maybe<out T> {
    object None: Maybe<Nothing>()
    data class Just<T>(val value: T): Maybe<T>()

    fun <R> map(transform: (T) -> R): Maybe<R> {
        return when (this) {
            None -> None
            is Just -> Just(transform(this.value))
        }
    }

    fun <R> flatMap(transform: (T) -> Maybe<R>): Maybe<R> =
        when (this) {
            None    -> None
            is Just -> transform(this.value)
        }

    fun orElse(defaultValue: @UnsafeVariance T): T {
        return when (this) {
            None -> defaultValue
            is Just -> this.value
        }
    }

    companion object {
        fun <T> just(value: T): Maybe<T> {
            return Just(value)
        }

        fun <T> none(): Maybe<T> {
            return None
        }
    }
}