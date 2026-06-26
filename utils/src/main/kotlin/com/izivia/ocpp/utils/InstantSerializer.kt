package com.izivia.ocpp.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlin.time.Instant

private const val NANOS_PER_MILLIS = 1_000_000

class InstantSerializer : StdSerializer<Instant>(Instant::class.java) {

    override fun serialize(instant: Instant?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {
        jsonGenerator?.writeString(
            instant.truncateToMillis().toString()
        )
    }

    private fun Instant?.truncateToMillis(): Instant? =
        this?.let {
            Instant.fromEpochSeconds(it.epochSeconds, (it.nanosecondsOfSecond / NANOS_PER_MILLIS) * NANOS_PER_MILLIS)
        }
}
