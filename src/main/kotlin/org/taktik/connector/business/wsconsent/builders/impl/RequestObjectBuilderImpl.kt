package org.taktik.connector.business.wsconsent.builders.impl

import org.taktik.connector.business.kmehrcommons.HcPartyUtil
import org.taktik.connector.business.wsconsent.builders.RequestObjectBuilder
import org.taktik.connector.business.wsconsent.exception.WsConsentBusinessConnectorException
import org.taktik.connector.business.wsconsent.exception.WsConsentBusinessConnectorExceptionValues
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType
import be.fgov.ehealth.hubservices.core.v2.ConsentType
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest
import be.fgov.ehealth.hubservices.core.v2.RequestType
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import java.math.BigDecimal
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

class RequestObjectBuilderImpl @Throws(
    WsConsentBusinessConnectorException::class,
    TechnicalConnectorException::class,
    InstantiationException::class
) constructor() : RequestObjectBuilder {
    private val log = LoggerFactory.getLogger(RequestObjectBuilderImpl::class.java)

    override fun createPutRequest(
        author: AuthorWithPatientAndPersonType?,
        consent: ConsentType?
    ): PutPatientConsentRequest {
        if (author != null && consent != null) {
            val req = PutPatientConsentRequest()
            req.consent = consent
            val request = this.createRequestType(author)
            req.request = request
            return req
        } else {
            log.error("author and Consent type are required to create a PutPatientConsentRequest")
            throw WsConsentBusinessConnectorException(
                WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL,
                "author and consent type  are required to create a PutPatientConsentRequest"
            )
        }
    }

    override fun createGetRequest(
        author: AuthorWithPatientAndPersonType?,
        consent: SelectGetPatientConsentType?
    ): GetPatientConsentRequest {
        if (author != null && consent != null) {
            val request = GetPatientConsentRequest()
            val req = this.createRequestType(author)
            val maxRows = ConfigFactory.getConfigValidator().getProperty("wsconsent.maxrows")
            if (maxRows != null) {
                req.maxrows = BigDecimal(maxRows)
            }

            request.request = req
            request.select = consent
            return request
        } else {
            log.error("author and consent type are required to create a GetPatientConsentRequest")
            throw WsConsentBusinessConnectorException(
                WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL,
                "author and consent type are required to create a GetPatientConsentRequest"
            )
        }
    }

    override fun createRevokeRequest(
        author: AuthorWithPatientAndPersonType?,
        consent: ConsentType?
    ): RevokePatientConsentRequest {
        if (author != null && consent != null) {
            val request = RevokePatientConsentRequest()
            request.consent = consent
            val req = this.createRequestType(author)
            request.request = req
            return request
        } else {
            log.error("author and Consent type are required to create a RevokePatientConsentRequest")
            throw WsConsentBusinessConnectorException(
                WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL,
                "author and consent type  are required to create a RevokePatientConsentRequest"
            )
        }
    }

    private fun createRequestType(author: AuthorWithPatientAndPersonType) = RequestType().apply {
        this.id = IDKMEHR().apply {
            s = IDKMEHRschemes.ID_KMEHR
            sv = "1.0"
            value = createKmehrID(getFirstHcPartyIdFromAuthor(author)!!)
        }
        this.author = author
        this.date = DateTime()
        this.time = DateTime()
    }

    private fun getFirstHcPartyIdFromAuthor(author: AuthorWithPatientAndPersonType): String? =
        author.hcparties.firstOrNull()?.ids?.firstOrNull()?.value

    fun createKmehrID(firstHcPartyIdOfAuthor: String): String {
        return firstHcPartyIdOfAuthor + "." + HcPartyUtil.createKmehrIdSuffix()
    }
}
