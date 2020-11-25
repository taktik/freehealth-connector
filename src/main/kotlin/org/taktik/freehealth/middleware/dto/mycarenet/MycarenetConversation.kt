package org.taktik.freehealth.middleware.dto.mycarenet

import org.eclipse.jetty.io.WriterOutputStream
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.StringWriter

class MycarenetConversation(
    soapRequest: String? = null,
    soapResponse: String? = null,
    var transactionRequest: String? = null,
    var transactionResponse: String? = null,
    var decryptedResponseContent: ArrayList<String>? = arrayListOf()
                           ) {

    var soapRequest: String? = soapRequest
        get() = field ?: soapRequestBos?.toByteArray()?.toString(Charsets.UTF_8)
    var soapResponse: String? = soapResponse
        get() = field ?: soapResponseBos?.toByteArray()?.toString(Charsets.UTF_8)

    private var soapRequestBos:ByteArrayOutputStream? = null
    private var soapResponseBos:ByteArrayOutputStream? = null

    fun soapRequestOutputStream(): OutputStream {
        return soapRequestBos ?: ByteArrayOutputStream(10000).apply { this@MycarenetConversation.soapRequestBos = this }
    }

    fun soapResponseOutputStream(): OutputStream {
        return soapResponseBos ?: ByteArrayOutputStream(10000).apply { this@MycarenetConversation.soapResponseBos = this }
    }

}
