package org.taktik.connector.business.chapterIV.exception

import be.fgov.ehealth.errors.soa.v1.SOAErrorType
import org.taktik.connector.technical.exception.ConnectorException
import java.text.MessageFormat

class ChapterIVBusinessConnectorException : ConnectorException {
    var soaError: SOAErrorType? = null
        private set

    constructor(errorCodeValue: ChapterIVBusinessConnectorExceptionValues,
                causeError: SOAErrorType?,
                vararg params: Any) : super(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode) {
        this.soaError = causeError
    }

    constructor(errorCodeValue: ChapterIVBusinessConnectorExceptionValues,
                e: Throwable,
                causeError: SOAErrorType?,
                vararg params: Any) : super(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode, e) {
        this.soaError = causeError
    }

    constructor(errorCodeValue: ChapterIVBusinessConnectorExceptionValues,
                causeError: SOAErrorType?) : super(errorCodeValue.message, errorCodeValue.errorCode) {
        this.soaError = causeError
    }

    constructor(errorCodeValue: ChapterIVBusinessConnectorExceptionValues,
                e: Throwable,
                vararg params: Any) : super(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode, e)

    constructor(error: ChapterIVBusinessConnectorExceptionValues,
                param: String) : this(error, null as SOAErrorType?, param)

}
