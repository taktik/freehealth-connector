package org.taktik.connector.business.memberdatav2.domain

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult

class MemberDataBuilderResponse(val consultationResponse: MemberDataConsultationResponse,
    val response: ByteArray?,
    val signatureVerificationResult: Map<String?, SignatureVerificationResult?>) 
