package org.taktik.connector.business.memberdata.domain

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.Response

class MemberDataBuilderResponse(val assertions: List<Assertion>, val consultationResponse: MemberDataConsultationResponse, val response: Response, val signatureVerificationResult: Map<String, SignatureVerificationResult>)
