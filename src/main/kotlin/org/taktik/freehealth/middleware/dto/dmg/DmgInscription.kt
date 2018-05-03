package org.taktik.freehealth.middleware.dto.dmg

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType

import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
class DmgInscription : DmgMessageWithPatient(), Serializable {
    var from: Date? = null
    var to: Date? = null

    var payment1Amount: Double? = null
    var payment1Currency: String? = null
    var payment1Date: Date? = null
    var payment1Ref: String? = null

    var payment2Amount: Double? = null
    var payment2Currency: String? = null
    var payment2Date: Date? = null
    var payment2Ref: String? = null

    var hcParty: HcpartyType? = null

    fun setPaymentAmount(paymentIdx: Int, v: Double) {
        if (paymentIdx == 1) {
            payment1Amount = v
        } else {
            payment2Amount = v
        }
    }

    fun setPaymentRef(paymentIdx: Int, v: String) {
        if (paymentIdx == 1) {
            payment1Ref = v
        } else {
            payment2Ref = v
        }
    }

    fun setPaymentDate(paymentIdx: Int, v: Date) {
        if (paymentIdx == 1) {
            payment1Date = v
        } else {
            payment2Date = v
        }
    }

    fun setPaymentCurrency(paymentIdx: Int, v: String) {
        if (paymentIdx == 1) {
            payment1Currency = v
        } else {
            payment2Currency = v
        }
    }
}
