package org.taktik.connector.business.daas

import oasis.names.tc.saml._2_0.protocol.AttributeQueryType
import oasis.names.tc.saml._2_0.protocol.ResponseType
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface AttributeService {
    @Throws(ConnectorException::class)
    fun getAttribute(token: SAMLToken, attribute: AttributeQueryType): ResponseType
}
