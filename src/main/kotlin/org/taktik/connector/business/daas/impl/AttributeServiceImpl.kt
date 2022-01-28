package org.taktik.connector.business.daas.impl

import oasis.names.tc.saml._2_0.protocol.AttributeQueryType
import oasis.names.tc.saml._2_0.protocol.ResponseType
import org.taktik.connector.business.daas.AttributeService
import org.taktik.connector.business.daas.service.ServiceFactory
import org.taktik.connector.technical.service.sts.security.SAMLToken

class AttributeServiceImpl : AttributeService {
    override fun getAttribute(token: SAMLToken, attribute: AttributeQueryType): ResponseType {
        val service = ServiceFactory.getAttributeService(token)
        service.setPayload(attribute)
        service.setSoapAction("urn:be:fgov:ehealth:daas:saml:protocol:v1:AttributeQuery")
        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
        val response = xmlResponse.asObject(ResponseType::class.java) as ResponseType

        return response
    }
}
