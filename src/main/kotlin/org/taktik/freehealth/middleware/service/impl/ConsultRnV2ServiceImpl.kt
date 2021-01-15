package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.cbsspersonlegaldata.v1.CbssPersonRequestType
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonDeclarationType
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse
import be.fgov.ehealth.rn.commons.v1.SsinType
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticAddress
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticBirth
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticGender
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticName
import be.fgov.ehealth.rn.personservice.core.v1.SearchPersonBySsinCriteriaType
import be.fgov.ehealth.rn.personservice.core.v1.SearchPersonPhoneticallyCriteriaType
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.consultrnv2.exception.inscriptionservice.CbssPersonServiceException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonBySsinException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonPhoneticallyException
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnCBSSPersonServiceImpl
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnPersonServiceImpl
import org.taktik.connector.business.ssinhistory.service.impl.SsinHistoryTokenServiceImpl
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.ConsultRnV2Service
import org.taktik.freehealth.middleware.service.STSService
import java.util.*

@Service
class ConsultRnV2ServiceImpl(private val stsService: STSService) : ConsultRnV2Service {
    private val log = LogFactory.getLog(this.javaClass)
    val backingPersonService = ConsultrnPersonServiceImpl(EhealthReplyValidatorImpl());
    val backingCbssPersonService = ConsultrnCBSSPersonServiceImpl(EhealthReplyValidatorImpl());
    val historyService = SsinHistoryTokenServiceImpl(EhealthReplyValidatorImpl())

    override fun searchPersonBySsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): SearchPersonBySsinResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try {
            backingPersonService.searchPersonBySsin(samlToken, SearchPersonBySsinRequest().apply {
                applicationId = "Medispring"
                issueInstant = DateTime.now()
                id = IdGeneratorFactory.getIdGenerator("uuid").generateId()
                criteria = SearchPersonBySsinCriteriaType().apply {
                    this.ssin = SsinType().apply { value = ssin }
                }
            })
        }catch (ex : SearchPersonBySsinException){
            ex.searchPersonBySsinResponse
        }

    }

    override fun searchPersonPhonetically(keystoreId: UUID, tokenId: UUID, passPhrase: String, dateOfBirth: Int, lastName: String, firstName: String?, middleName: String?, gender: String, countryCode: Int, cityCode: String?, tolerance: Int, limit: Int): SearchPersonPhoneticallyResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try{
            backingPersonService.searchPersonPhonetically(samlToken, SearchPersonPhoneticallyRequest().apply {
                applicationId = "Medispring"
                issueInstant = DateTime.now()
                id = IdGeneratorFactory.getIdGenerator("uuid").generateId()
                criteria = SearchPersonPhoneticallyCriteriaType().apply {
                    this.name = PhoneticName().apply {
                        this.lastName = lastName
                        this.givenNames //todo
                    }

                    this.birth = PhoneticBirth().apply {
                       this.birthDate = dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                    }

                    this.gender = PhoneticGender().apply {
                        this.genderCode //todo
                    }

                    this.address = PhoneticAddress().apply {
                        this.cityCode = cityCode
                        this.countryCode = countryCode
                    }

                    this.maximumResultCount = limit

                }
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
                id = IdGeneratorFactory.getIdGenerator("uuid").generateId()
                applicationId = "Medispring"
                issueInstant = DateTime.now()
                declaration = RegisterPersonDeclarationType().apply {
                    person = CbssPersonRequestType().apply {
                        name //todo
                        birth //todo
                        this.gender //todo
                    }
                }
            })
        }catch (ex: CbssPersonServiceException){
            ex.registerPersonResponse
        }

    }
    override fun consultCurrentSsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): ConsultCurrentSsinResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try{historyService.consultCurrentSsin(samlToken, ConsultCurrentSsinRequest().apply {
            id = "ID${System.currentTimeMillis()}"
            issueInstant = DateTime.now()
            this.ssin = ssin
        })}catch (ex: ){

        }
    }
}
