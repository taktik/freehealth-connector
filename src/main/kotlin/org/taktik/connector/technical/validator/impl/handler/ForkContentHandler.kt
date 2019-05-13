package org.taktik.connector.technical.validator.impl.handler

import org.xml.sax.Attributes
import org.xml.sax.ContentHandler
import org.xml.sax.Locator
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class ForkContentHandler(vararg handlers: ContentHandler) : DefaultHandler() {
    private val handlers: Array<out ContentHandler> = handlers

    override fun setDocumentLocator(locator: Locator?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.setDocumentLocator(locator)
        }

    }

    @Throws(SAXException::class)
    override fun startDocument() {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.startDocument()
        }

    }

    @Throws(SAXException::class)
    override fun endDocument() {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.endDocument()
        }

    }

    @Throws(SAXException::class)
    override fun startPrefixMapping(prefix: String?, uri: String?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.startPrefixMapping(prefix, uri)
        }

    }

    @Throws(SAXException::class)
    override fun endPrefixMapping(prefix: String?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.endPrefixMapping(prefix)
        }

    }

    @Throws(SAXException::class)
    override fun startElement(uri: String?, localName: String?, qName: String?, atts: Attributes?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.startElement(uri, localName, qName, atts)
        }

    }

    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.endElement(uri, localName, qName)
        }

    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.characters(ch, start, length)
        }

    }

    @Throws(SAXException::class)
    override fun ignorableWhitespace(ch: CharArray?, start: Int, length: Int) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.ignorableWhitespace(ch, start, length)
        }

    }

    @Throws(SAXException::class)
    override fun processingInstruction(target: String?, data: String?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.processingInstruction(target, data)
        }

    }

    @Throws(SAXException::class)
    override fun skippedEntity(name: String?) {
        val `arr$` = this.handlers
        val `len$` = `arr$`.size

        for (`i$` in 0 until `len$`) {
            val handler = `arr$`[`i$`]
            handler.skippedEntity(name)
        }

    }
}
