package org.taktik.connector.business.recipe.utils

class ThrowIfSetTwice<T>(var value: T) {
    var assigned = false
        private set

    fun set(value: T) {
        if (assigned) {
            throw IllegalStateException("double assignment: current: ${this.value}; new: $value")
        }
        assigned = true
        this.value = value
    }

    fun get(): T {
        return value
    }
}
