package org.taktik.connector.business.memberdata.builders

import org.taktik.connector.business.memberdata.domain.MemberDataBuilderResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import org.taktik.connector.technical.service.etee.Crypto
import javax.xml.bind.JAXBException
import javax.xml.transform.TransformerConfigurationException

interface ResponseObjectBuilder {
    @Throws(TechnicalConnectorException::class)
    fun handleConsultationResponse(consultResponse: MemberDataConsultationResponse, crypto: Crypto) : MemberDataBuilderResponse?
}
