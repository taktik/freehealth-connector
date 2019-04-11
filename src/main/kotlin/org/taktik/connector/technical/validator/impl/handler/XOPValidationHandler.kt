package org.taktik.connector.technical.validator.impl.handler

import org.apache.commons.lang.StringUtils
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class XOPValidationHandler(private val enabled: Boolean) : DefaultHandler() {
    var isXop: Boolean = false
        private set
    private var endElementAfterXOP: Int = 0

    @Throws(SAXException::class)
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        if (this.enabled) {
            this.resetXOP()
            if ("Include" == localName && "http://www.w3.org/2004/08/xop/include" == uri && attributes!!.getValue("href") != null) {
                this.isXop = true
            }
        }

    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        if (this.isXop) {
            val content = StringUtils.substring(String(ch!!), start, start + length)
            if (StringUtils.isNotBlank(content)) {
                this.isXop = false
            }
        }

    }

    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if (this.isXop) {
            ++this.endElementAfterXOP
        }

    }

    private fun resetXOP() {
        if (this.endElementAfterXOP == 2) {
            this.isXop = false
            this.endElementAfterXOP = 0
        }

    }
}
