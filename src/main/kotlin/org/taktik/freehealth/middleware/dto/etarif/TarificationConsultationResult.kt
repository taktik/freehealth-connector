package org.taktik.freehealth.middleware.dto.etarif

import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import java.io.Serializable
import java.util.ArrayList
import java.util.Date

class TarificationConsultationResult {
    var birthdate: Date? = null
    var CT1: String? = null
    var CT2: String? = null
    var date: Date? = null
    var deceased: Date? = null
    var errors: MutableList<MycarenetError> = ArrayList()
    var firstName: String? = null
    var insurancePeriodEnd: Date? = null
    var insurancePeriodStart: Date? = null
    var lastName: String? = null
    var niss: String? = null
    var sex: Sex? = null
    var retrieveTransactionRequest:String? = null
    var commonInputResponse:String? = null
    var tarificationConsultationResponse:String? = null
    var tarificationConsultationResponseJSON:String? = null
    var codeResults: MutableList<CodeResult> = ArrayList()
    var infoRequestDto: InfoRequestDto? = null

    enum class Sex : Serializable {
        MALE, FEMALE
    }

    class Payment : Serializable {
        var amount: Double = 0.toDouble()
        var currencyUnit: String? = null
    }

    class CodeResult : Serializable {
        var code: String? = null
        var fee: Payment? = null
        var reimbursement: Payment? = null
        var patientFee: Payment? = null
        var contract: String? = null
        var justification: Int = 0
    }

    class OutputReferences : Serializable {
        var inputReference: String? = null
        var outputReference: String? = null
        var nipReference: String? = null
    }
}
