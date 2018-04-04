package org.taktik.connector.business.wsconsent.builders

import org.taktik.connector.business.wsconsent.exception.WsConsentBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType
import be.fgov.ehealth.hubservices.core.v2.ConsentType
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType

interface RequestObjectBuilder {
	@Throws(TechnicalConnectorException::class, WsConsentBusinessConnectorException::class, InstantiationException::class)
	fun createPutRequest(author: AuthorWithPatientAndPersonType?, consent: ConsentType?): PutPatientConsentRequest

	@Throws(TechnicalConnectorException::class, WsConsentBusinessConnectorException::class, InstantiationException::class)
	fun createGetRequest(author: AuthorWithPatientAndPersonType?, consent: SelectGetPatientConsentType?): GetPatientConsentRequest

	@Throws(TechnicalConnectorException::class, WsConsentBusinessConnectorException::class, InstantiationException::class)
	fun createRevokeRequest(author: AuthorWithPatientAndPersonType?, consent: ConsentType?): RevokePatientConsentRequest
}
