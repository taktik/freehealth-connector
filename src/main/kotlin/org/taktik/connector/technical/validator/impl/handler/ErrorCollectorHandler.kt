package org.taktik.connector.technical.validator.impl.handler

import java.util.ArrayList
import org.apache.commons.lang.ArrayUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.xml.sax.ErrorHandler
import org.xml.sax.SAXException
import org.xml.sax.SAXParseException

open class ErrorCollectorHandler(val xopHandler: XOPValidationHandler? = null) : ErrorHandler {
    private val exceptionWarningList = ArrayList<String>()
    private val exceptionErrorList = ArrayList<String>()
    private val exceptionFatalList = ArrayList<String>()

    @Throws(SAXException::class)
    override fun warning(exception: SAXParseException) {
        val msg = "WARNING " + this.toString(exception)
        this.exceptionWarningList.add(msg)
    }

    @Throws(SAXException::class)
    override fun error(exception: SAXParseException) {
        if (this.accept(exception)) {
            val msg = "ERROR " + this.toString(exception)
            this.exceptionErrorList.add(msg)
        }

    }

    @Throws(SAXException::class)
    override fun fatalError(exception: SAXParseException) {
        if (this.accept(exception)) {
            val msg = "FATAL " + this.toString(exception)
            this.exceptionFatalList.add(msg)
        }

    }

    private fun toString(exception: SAXParseException): String {
        return exception.message ?: ""
    }

    fun getExceptionList(vararg errorType: String): List<String> {
        val exceptionList = ArrayList<String>()
        if (ArrayUtils.contains(errorType, "WARN")) {
            exceptionList.addAll(this.exceptionWarningList)
        }

        if (ArrayUtils.contains(errorType, "ERROR")) {
            exceptionList.addAll(this.exceptionErrorList)
        }

        if (ArrayUtils.contains(errorType, "FATAL")) {
            exceptionList.addAll(this.exceptionFatalList)
        }

        return exceptionList
    }

    fun hasExceptions(vararg errorType: String): Boolean {
        return if (ArrayUtils.contains(errorType, "WARN") && !this.isEmpty(this.exceptionWarningList)) {
            true
        } else if (ArrayUtils.contains(errorType, "ERROR") && !this.isEmpty(this.exceptionErrorList)) {
            true
        } else {
            ArrayUtils.contains(errorType, "FATAL") && !this.isEmpty(this.exceptionFatalList)
        }
    }

    private fun accept(ex: SAXParseException): Boolean {
        if (this.xopHandler != null && this.xopHandler.isXop) {
            LOG.debug("XOP element detected, skipping error [" + ex.message + "]")
            return false
        } else {
            return true
        }
    }

    private fun isEmpty(inputList: List<*>): Boolean {
        return inputList.size <= 0
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ErrorCollectorHandler::class.java)
        val WARNING = "WARN"
        val ERROR = "ERROR"
        val FATAL = "FATAL"
    }
}
