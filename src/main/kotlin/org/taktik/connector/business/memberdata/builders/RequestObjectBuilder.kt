package org.taktik.connector.business.memberdata.builders

import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import oasis.names.tc.saml._2_0.protocol.AttributeQuery
import org.taktik.connector.business.memberdata.exception.MemberDataBusinessConnectorException
import org.taktik.connector.business.mycarenetdomaincommons.domain.InputReference

interface RequestObjectBuilder {
    @Throws(TechnicalConnectorException::class, MemberDataBusinessConnectorException::class)
    fun buildConsultationRequest(var1: Boolean, var2: InputReference, var3: AttributeQuery): MemberDataConsultationRequest
}
