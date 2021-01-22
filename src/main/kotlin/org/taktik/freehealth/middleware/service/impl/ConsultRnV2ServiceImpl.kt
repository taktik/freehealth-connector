package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.baselegaldata.v1.AddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.ForeignAddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.GivenNameType
import be.fgov.ehealth.rn.baselegaldata.v1.LocationDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NationalityInfoBaseType
import be.fgov.ehealth.rn.cbsspersonlegaldata.v1.CbssPersonRequestType
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonDeclarationType
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest
import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType
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
import org.taktik.connector.business.consultrnv2.exception.ssinInformationservice.ConsultCurrentSsinException
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnCBSSPersonServiceImpl
import org.taktik.connector.business.consultrnv2.service.impl.ConsultrnPersonServiceImpl
import org.taktik.connector.business.ssinhistory.service.impl.SsinHistoryTokenServiceImpl
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnConversationDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnPersonDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnRegisterPersonResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchByNissResultDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.PersonMid
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

    override fun searchPersonBySsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): ConsultRnSearchPersonBySsinResponseDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        val searchPersonBySsinRequest = SearchPersonBySsinRequest().apply {
            applicationId = "0"
            issueInstant = DateTime.now()
            id = "ID${System.currentTimeMillis()}"
            criteria = SearchPersonBySsinCriteriaType().apply {
                this.ssin = ssin
            }
        }

        return try {
           val searchPersonBySsinResponse = backingPersonService.searchPersonBySsin(samlToken, searchPersonBySsinRequest)

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
                    request = ConnectorXmlUtils.toString(searchPersonBySsinRequest),
                    response = ConnectorXmlUtils.toString(searchPersonBySsinResponse)
                ),
                exception = null
            )
        }catch (ex: Exception){
            ConsultRnSearchPersonBySsinResponseDto(
                result = null,
                status = null,
                id = null,
                inResponseTo = null,
                issueInstant = null,
                xmlConversations = ConsultRnConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonBySsinRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                exception = ex
            )
        }

    }

    override fun searchPersonPhonetically(keystoreId: UUID, tokenId: UUID, passPhrase: String, dateOfBirth: Int, lastName: String, firstName: String?, middleName: String?, matchingType: String?, gender: String?, countryCode: Int, cityCode: String?, tolerance: Int?, limit: Int?): ConsultRnSearchPersonPhoneticallyResponseDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        val givenNames = mutableListOf<GivenNameType>()
        givenNames.add(GivenNameType().apply {
            value = firstName
            sequence = 1
        })

       if(!middleName.isNullOrEmpty()){
            givenNames.add(GivenNameType().apply {
                value = middleName
                sequence = 2
            })
        }

        val searchPersonPhoneticallyRequest = SearchPersonPhoneticallyRequest().apply {
            applicationId = "0"
            issueInstant = DateTime.now()
            id = "ID${System.currentTimeMillis()}"
            criteria = SearchPersonPhoneticallyCriteriaType().apply {
                this.name = PhoneticName().apply {
                    this.lastName = lastName
                    this.givenNames.addAll(givenNames)
                    this.givenNameMatching = GivenNameMatchingType.fromValue(matchingType).value()
                }

                this.birth = PhoneticBirth().apply {
                    this.birthDate = dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                    this.variation = tolerance
                }

                gender.let {
                    this.gender = PhoneticGender().apply {
                        if (gender === "MALE"){
                            this.genderCode = "M"
                        }else{
                            this.genderCode = "F"
                        }
                    }
                }

                countryCode.let {
                    this.address = PhoneticAddress().apply {
                        this.countryCode = countryCode
                        if(!cityCode.isNullOrEmpty()){this.cityCode = cityCode}
                    }
                }

                this.maximumResultCount = limit
            }
        }

        return try{
            val searchPersonPhoneticallyResponse = backingPersonService.searchPersonPhonetically(samlToken, searchPersonPhoneticallyRequest)
            log.info("SearchPersonPhonetically response: "+ ConnectorXmlUtils.toString(searchPersonPhoneticallyResponse))

            ConsultRnSearchPersonPhoneticallyResponseDto(
                id = searchPersonPhoneticallyResponse?.id,
                inResponseTo = searchPersonPhoneticallyResponse?.inResponseTo,
                issueInstant = searchPersonPhoneticallyResponse?.issueInstant,
                status = searchPersonPhoneticallyResponse?.status,
                result = searchPersonPhoneticallyResponse?.result,
                xmlConversations = ConsultRnConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonPhoneticallyRequest),
                    response = ConnectorXmlUtils.toString(searchPersonPhoneticallyResponse)
                )
            )
        }catch (ex: Exception){
            ConsultRnSearchPersonPhoneticallyResponseDto(
                result = null,
                status = null,
                id = null,
                inResponseTo = null,
                issueInstant = null,
                xmlConversations = ConsultRnConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonPhoneticallyRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                exception = ex
            )
        }
    }

    override fun registerPerson(keystoreId: UUID, tokenId: UUID, passPhrase: String, mid: PersonMid): ConsultRnRegisterPersonResponseDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        val givenNames = mutableListOf<GivenNameType>()

        givenNames.add(GivenNameType().apply {
            value = mid?.firstName?.capitalize()
            sequence = 1
        })

        if(!mid.middleName.isNullOrEmpty()){
            givenNames.add(GivenNameType().apply {
                value = mid?.middleName?.capitalize()
                sequence = 2
            })
        }

        val registerPersonRequest = RegisterPersonRequest().apply {
            id = IdGeneratorFactory.getIdGenerator("uuid").generateId()
            applicationId = "0"
            issueInstant = DateTime.now()
            declaration = RegisterPersonDeclarationType().apply {
                person = CbssPersonRequestType().apply {
                    name = NameInfoDeclarationType().apply {
                        this.lastName = mid?.lastName?.capitalize()
                        this.givenNames.addAll(givenNames)
                    }
                    mid?.nationalityCode.let {
                        nationalities = NationalitiesDeclarationType().apply {
                            nationalities.add(NationalityInfoBaseType().apply {
                                nationalityCode = it
                            })
                        }
                    }
                    birth = BirthInfoDeclarationType().apply {
                        birthDate = mid?.dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                        birthPlace = mid?.birthPlace?.let {
                            LocationDeclarationType().apply {
                                countryCode = it.countryCode
                                cityCode = it.cityCode
                                it.cityName?.let {
                                    cityNames.add(LocalizedDescriptionType().apply {
                                        value = it
                                    })
                                }
                            }
                        }
                    }
                    this.gender = mid.gender?.let {
                        GenderInfoDeclarationType().apply {
                           if(it === "MALE"){
                               this.genderCode = "M"
                           }else{
                               this.genderCode = "M"
                           }
                        }
                    }
                    this.address = mid?.residentialAddress?.let {
                        AddressDeclarationType().apply {
                            ForeignAddressDeclarationType().apply {
                                it.countryCode?.let {
                                    if(it != 0){
                                        this.countryCode = it
                                    }
                                }
                                it.countryIsoCode.let {
                                    countryIsoCode = it
                                }
                                it.countryName.let {
                                    LocalizedDescriptionType().apply {
                                        value = it
                                    }
                                }
                                it.cityName?.let {
                                    this.cityName = LocalizedDescriptionType().apply {
                                        value = it
                                    }
                                }
                                it.postalCode.let {
                                    postalCode = it
                                }
                                it.streetName?.let {
                                    this.streetName = LocalizedDescriptionType().apply {
                                        value = it
                                    }
                                }
                                it.houseNumber?.let {
                                    this.houseNumber = it
                                }
                                it.boxNumber?.let {
                                    this.boxNumber = it
                                }
                            }
                        }
                    }

                    this.contactAddress = mid?.contactAddress?.let {
                        ContactAddressDeclarationType().apply {
                            it.countryCode?.let {
                                if(it != 0){
                                    this.countryCode = it
                                }
                            }
                            it.countryIsoCode?.let {
                                this.countryIsoCode = it
                            }
                            it.countryName.let {
                                LocalizedDescriptionType().apply {
                                    value = it
                                }
                            }
                            it.cityName?.let {
                                this.cityName = LocalizedDescriptionType().apply {
                                    value = it
                                }
                            }
                            it.postalCode.let {
                                this.postalCode = it
                            }
                            it.streetCode.let {
                                this.streetCode = it
                            }
                            it.streetName?.let {
                                this.streetName = LocalizedDescriptionType().apply {
                                    value = it
                                }
                            }
                            it.houseNumber?.let {
                                this.houseNumber = it
                            }
                            it.boxNumber?.let {
                                this.boxNumber = it
                            }
                            it.typeCode?.let {
                                if(it != 0){
                                    this.typeCode = it
                                }
                            }
                        }
                    }
                }
            }
        }

        return try{
            val registerPersonResponse = backingCbssPersonService.registerPerson(samlToken, registerPersonRequest)
            ConsultRnRegisterPersonResponseDto(
                id = registerPersonResponse.id,
                issueInstant = registerPersonResponse.issueInstant,
                inResponseTo = registerPersonResponse.inResponseTo,
                status = registerPersonResponse.status,
                declaration = registerPersonResponse.declaration,
                result = registerPersonResponse.result,
                xmlConversation = ConsultRnConversationDto(
                    request = ConnectorXmlUtils.toString(registerPersonRequest),
                    response = ConnectorXmlUtils.toString(registerPersonResponse)
                ),
                exception = null

            )
        }catch (ex: Exception){
            ConsultRnRegisterPersonResponseDto(
                id = null,
                issueInstant = null,
                inResponseTo = null,
                status = null,
                declaration = null,
                result = null,
                xmlConversation = ConsultRnConversationDto(
                    request = ConnectorXmlUtils.toString(registerPersonRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                exception = ex
            )
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
