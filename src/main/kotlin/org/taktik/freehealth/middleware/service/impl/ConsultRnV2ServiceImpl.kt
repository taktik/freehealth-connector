package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse
import org.apache.commons.logging.LogFactory
import org.taktik.connector.business.consultrnv2.exception.inscriptionservice.CbssPersonServiceException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonBySsinException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonPhoneticallyException
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnCBSSPersonServiceImpl
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnPersonServiceImpl
import org.taktik.connector.business.consultrnv2.service.impl.support.ConsultrnService
import org.taktik.connector.business.ssinhistory.service.impl.SsinHistoryTokenServiceImpl
import org.taktik.connector.technical.validator.SessionValidator
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.ConsultRnV2Service
import org.taktik.freehealth.middleware.service.STSService
import java.util.*

class ConsultRnV2ServiceImpl(private val stsService: STSService) : ConsultRnV2Service {
    private val log = LogFactory.getLog(this.javaClass)
    val backingPersonService = ConsultrnPersonServiceImpl(SessionValidator(), EhealthReplyValidatorImpl());
    val backingCbssPersonService = ConsultrnCBSSPersonServiceImpl(SessionValidator(), EhealthReplyValidatorImpl());
    val historyService = SsinHistoryTokenServiceImpl(EhealthReplyValidatorImpl())

    override fun searchPersonBySsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): SearchPersonBySsinResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try {
            backingPersonService.searchPersonBySsin(samlToken, SearchPersonBySsinRequest().apply {

            })
        }catch (ex : SearchPersonBySsinException){
            ex.searchPersonBySsinResponse
        }

    }

    override fun searchPersonPhonetically(keystoreId: UUID, tokenId: UUID, passPhrase: String, dateOfBirth: Int, lastName: String, firstName: String?, middleName: String?, gender: String, countryCode: Int, cityCode: Int, tolerance: Int, limit: Int): SearchPersonPhoneticallyResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try{
            backingPersonService.searchPersonPhonetically(samlToken, SearchPersonPhoneticallyRequest().apply {

            })
        }catch (ex: SearchPersonPhoneticallyException){
            ex.searchPersonPhoneticallyResponse
        }
    }

    override fun registerPerson(keystoreId: UUID, tokenId: UUID, passPhrase: String, mid: PersonMid): RegisterPersonResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try{
            backingCbssPersonService.registerPerson(samlToken, RegisterPersonRequest().apply {

            })
        }catch (ex: CbssPersonServiceException){
            ex.registerPersonResponse
        }

    }

    override fun consultCurrentSsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): ConsultCurrentSsinResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")
    }

}
