package org.taktik.connector.business.domain.chapter4

import com.fasterxml.jackson.databind.ObjectMapper

import java.io.IOException
import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 11/11/13
 * Time: 08:04
 * To change this template use File | Settings | File Templates.
 */
class AgreementTransaction : Serializable {
    var paragraph: String? = null
    var isAccepted: Boolean = false
    var isInTreatment: Boolean = false
    var careProviderReference: String? = null
    var decisionReference: String? = null
    var start: Date? = null
    var end: Date? = null
    var unitNumber: Double? = null
    private val quantityValue: Double? = null
    private val quantityUnit: String? = null
    var ioRequestReference: String? = null
    var content: ByteArray? = null
    var responseType: String? = null
}
