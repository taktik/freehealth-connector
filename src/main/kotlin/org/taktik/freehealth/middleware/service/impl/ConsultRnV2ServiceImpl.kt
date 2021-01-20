package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.baselegaldata.v1.GivenNameType
import be.fgov.ehealth.rn.cbsspersonlegaldata.v1.CbssPersonRequestType
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonDeclarationType
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticAddress
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticBirth
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticGender
import be.fgov.ehealth.rn.personservice.core.v1.PhoneticName
import be.fgov.ehealth.rn.personservice.core.v1.SearchPersonBySsinCriteriaType
import be.fgov.ehealth.rn.personservice.core.v1.SearchPersonPhoneticallyCriteriaType
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest
import be.fgov.ehealth.rn.registries.commons.v1.GivenNameMatchingType
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.consultrnv2.exception.inscriptionservice.CbssPersonServiceException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonBySsinException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonPhoneticallyException
import org.taktik.connector.business.consultrnv2.exception.ssinInformationservice.ConsultCurrentSsinException
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnCBSSPersonServiceImpl
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnPersonServiceImpl
import org.taktik.connector.business.ssinhistory.service.impl.SsinHistoryTokenServiceImpl
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnConversationDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnPersonDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchByNissResultDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.ConsultRnV2Service
import org.taktik.freehealth.middleware.service.STSService
import java.util.*
import kotlin.collections.ArrayList

@Service
class ConsultRnV2ServiceImpl(private val stsService: STSService) : ConsultRnV2Service {
    private val log = LogFactory.getLog(this.javaClass)
    val backingPersonService = ConsultrnPersonServiceImpl(EhealthReplyValidatorImpl());
    val backingCbssPersonService = ConsultrnCBSSPersonServiceImpl(EhealthReplyValidatorImpl());
    val historyService = SsinHistoryTokenServiceImpl(EhealthReplyValidatorImpl())

    override fun searchPersonBySsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): ConsultRnSearchPersonBySsinResponseDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try {
           val searchPersonBySsinResponse = backingPersonService.searchPersonBySsin(samlToken, SearchPersonBySsinRequest().apply {
                applicationId = "0"
                issueInstant = DateTime.now()
                id = "ID${System.currentTimeMillis()}"
                criteria = SearchPersonBySsinCriteriaType().apply {
                    this.ssin = ssin
                }
            })

            log.info("SearchPersonBySsin response: "+ ConnectorXmlUtils.toString(searchPersonBySsinResponse))
            
            ConsultRnSearchPersonBySsinResponseDto(
                ssin = searchPersonBySsinResponse?.ssin,
                result = ConsultRnSearchByNissResultDto(
                    person = ConsultRnPersonDto(
                        ssin = searchPersonBySsinResponse?.result?.person?.ssin,
                        nobilityTitle = searchPersonBySsinResponse?.result?.person?.nobilityTitle,
                        name = searchPersonBySsinResponse?.result?.person?.name,
                        nationalities = searchPersonBySsinResponse?.result?.person?.nationalities,
                        birth = searchPersonBySsinResponse?.result?.person?.birth,
                        decease = searchPersonBySsinResponse?.result?.person?.decease,
                        gender = searchPersonBySsinResponse?.result?.person?.gender,
                        civilStates = searchPersonBySsinResponse?.result?.person?.civilStates,
                        address = searchPersonBySsinResponse?.result?.person?.address,
                        contactAddress = searchPersonBySsinResponse?.result?.person?.contactAddress,
                        administrator = searchPersonBySsinResponse?.result?.person?.administrator,
                        subregister = searchPersonBySsinResponse?.result?.person?.subregister,
                        legalCohabitation = searchPersonBySsinResponse?.result?.person?.legalCohabitation,
                        anomalies = searchPersonBySsinResponse?.result?.person?.anomalies,
                        register = searchPersonBySsinResponse?.result?.person?.register,
                        registerInceptionDate = searchPersonBySsinResponse?.result?.person?.registerInceptionDate
                    )
                ),
                status = searchPersonBySsinResponse?.status,
                id = searchPersonBySsinResponse?.id,
                inResponseTo = searchPersonBySsinResponse?.inResponseTo,
                issueInstant = searchPersonBySsinResponse?.issueInstant,
                xmlConversations = ConsultRnConversationDto(
                    request = null,
                    response = ConnectorXmlUtils.toString(searchPersonBySsinResponse)
                )
            )

        }catch (ex : SearchPersonBySsinException){
            log.info("SearchPersonBySsin error response: "+ ConnectorXmlUtils.toString(ex))
            ConsultRnSearchPersonBySsinResponseDto(
                ssin = null,
                result = null,
                status = ex?.searchPersonBySsinResponse?.status,
                id = ex?.searchPersonBySsinResponse?.id,
                inResponseTo = ex?.searchPersonBySsinResponse?.inResponseTo,
                issueInstant = ex?.searchPersonBySsinResponse?.issueInstant,
                xmlConversations = ConsultRnConversationDto(
                    request = null,
                    response = ConnectorXmlUtils.toString(ex.searchPersonBySsinResponse)
                )
            )
        }

    }

    override fun searchPersonPhonetically(keystoreId: UUID, tokenId: UUID, passPhrase: String, dateOfBirth: Int, lastName: String, firstName: String?, middleName: String?, gender: String?, countryCode: Int?, cityCode: String?, tolerance: Int?, limit: Int?): ConsultRnSearchPersonPhoneticallyResponseDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try{
            val givenName = mutableListOf<String?>(firstName)

            val searchPersonPhoneticallyResponse = backingPersonService.searchPersonPhonetically(samlToken, SearchPersonPhoneticallyRequest().apply {
                applicationId = "0"
                issueInstant = DateTime.now()
                id = "ID${System.currentTimeMillis()}"
                criteria = SearchPersonPhoneticallyCriteriaType().apply {
                    this.name = PhoneticName().apply {
                        this.lastName = lastName
                        givenNames.addAll(listOf(
                            firstName
                        ))
                        /*mutableListOf<GivenNameType?>(GivenNameType().apply {
                            value = firstName
                            sequence = 1
                        })

                         */
                        this.givenNameMatching = GivenNameMatchingType.ALL_GIVENNAME.value()
                    }

                    this.birth = PhoneticBirth().apply {
                       this.birthDate = dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                    }

                    if(gender !== null && gender !== "UNKNOWN"){
                        this.gender = PhoneticGender().apply {
                            this.genderCode
                        }
                    }

                    if(countryCode !== null){
                        this.address = PhoneticAddress().apply {
                            if(cityCode !== null){
                                this.cityCode = cityCode
                            }
                            if (countryCode != null) {
                                this.countryCode = countryCode
                            }
                        }
                    }
                    this.maximumResultCount = limit

                }
            })

            log.info("SearchPersonBySsin response: "+ ConnectorXmlUtils.toString(searchPersonPhoneticallyResponse))

            ConsultRnSearchPersonPhoneticallyResponseDto(
                id = searchPersonPhoneticallyResponse?.id,
                inResponseTo = searchPersonPhoneticallyResponse?.inResponseTo,
                issueInstant = searchPersonPhoneticallyResponse?.issueInstant,
                status = searchPersonPhoneticallyResponse?.status,
                result = searchPersonPhoneticallyResponse?.result,
                xmlConversations = ConsultRnConversationDto(
                    request = null,
                    response = ConnectorXmlUtils.toString(searchPersonPhoneticallyResponse)
                )
            )

        }catch (ex: SearchPersonPhoneticallyException){
            log.info("SearchPersonBySsin error response: "+ ConnectorXmlUtils.toString(ex))
            ConsultRnSearchPersonPhoneticallyResponseDto(
                id = ex.searchPersonPhoneticallyResponse?.id,
                inResponseTo = ex.searchPersonPhoneticallyResponse?.inResponseTo,
                issueInstant = ex.searchPersonPhoneticallyResponse?.issueInstant,
                status = ex.searchPersonPhoneticallyResponse?.status,
                result = null,
                xmlConversations = ConsultRnConversationDto(
                    request = null,
                    response = ConnectorXmlUtils.toString(ex.searchPersonPhoneticallyResponse)
                )
            )
        }
    }

    override fun registerPerson(keystoreId: UUID, tokenId: UUID, passPhrase: String, mid: PersonMid): RegisterPersonResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        return try{
            backingCbssPersonService.registerPerson(samlToken, RegisterPersonRequest().apply {
                id = IdGeneratorFactory.getIdGenerator("uuid").generateId()
                applicationId = "0"
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
        })}catch (ex: ConsultCurrentSsinException){
            ex.consultCurrentSsinResponse
        }
    }
}
