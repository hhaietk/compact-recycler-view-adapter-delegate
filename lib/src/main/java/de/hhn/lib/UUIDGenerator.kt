package de.hhn.lib

import java.util.concurrent.atomic.AtomicInteger

internal object UUIDGenerator {

    private const val START_UUID = 0

    /**
     * https://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/
     */
    private val nextUUID = AtomicInteger(START_UUID)

    fun newUUID(): Int {
        if (!isValid(nextUUID.get())) {
            throw IllegalStateException("UID pool depleted")
        }
        return nextUUID.incrementAndGet()
    }

    private fun isValid(uid: Int): Boolean = uid >= START_UUID
}