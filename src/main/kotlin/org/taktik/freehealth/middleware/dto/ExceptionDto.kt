package org.taktik.freehealth.middleware.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class ExceptionDto(
    val timestamp: Date,
    val status: Int,
    val error: String?,
    val message: String?,
    val path: String?
) {
    companion object {
        private const val DEFAULT_EXCEPTION_MESSAGE = "unknown reason"
    }

    constructor(status: HttpStatus, exception: Exception, path: String?) :
        this(
            Date(),
            status.value(),
            status.reasonPhrase,
            exception.message ?: DEFAULT_EXCEPTION_MESSAGE,
            path
        )

    fun toResponseEntity(): ResponseEntity<ExceptionDto> {
        return ResponseEntity
                .status(status)
                .body(this)
    }
}
