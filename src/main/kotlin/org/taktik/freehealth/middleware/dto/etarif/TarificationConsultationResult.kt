package org.taktik.freehealth.middleware.dto.etarif

import org.taktik.freehealth.middleware.dto.MycarenetError
import java.io.Serializable
import java.util.ArrayList
import java.util.Date

class TarificationConsultationResult {
    var birthdate: Date? = null
    var CT1: String? = null
    var CT2: String? = null
    var date: Date? = null
    var deceased: Date? = null
    var codes: MutableList<String> = ArrayList()
    var errors: MutableList<MycarenetError> = ArrayList()
    var fees: MutableList<Payment> = ArrayList()
    var financialContracts: MutableList<String> = ArrayList()
    var firstName: String? = null
    var insurancePeriodEnd: Date? = null
    var insurancePeriodStart: Date? = null
    var justification: Int = 0
    var lastName: String? = null
    var niss: String? = null
    var patientFees: MutableList<Payment> = ArrayList()
    var sex: Sex? = null
    var reimbursements: MutableList<Payment> = ArrayList()
    var retrieveTransactionRequest:String? = null
    var commonInputResponse:String? = null

    enum class Sex : Serializable {
        MALE, FEMALE
    }

    class Payment : Serializable {
        var amount: Double = 0.toDouble()
        var currencyUnit: String? = null
    }

}
