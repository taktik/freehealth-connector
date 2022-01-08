package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.commons.core.v2.Id
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.idsupport.core.v2.IdentificationData
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdRequest
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdResponse
import be.fgov.ehealth.rn.baselegaldata.v1.AddressBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.AddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.AdministratorBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.BirthInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.ContactAddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.ForeignAddressDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.GenderInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.GivenNameType
import be.fgov.ehealth.rn.baselegaldata.v1.LocationDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NameInfoDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NationalitiesDeclarationType
import be.fgov.ehealth.rn.baselegaldata.v1.NationalityInfoBaseType
import be.fgov.ehealth.rn.baselegaldata.v1.PlainAddressType
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
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.service.idsupport.impl.IdSupportServiceImpl
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.common.ErrorDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultConversationDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultDeceaseType
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultNameType
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultPersonDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultRegisterPersonResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchByNissResultDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultPersonMid
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.RnConsultService
import org.taktik.freehealth.middleware.service.STSService
import java.util.*
import javax.xml.ws.soap.SOAPFaultException

@Service
class RnConsultServiceImpl(private val stsService: STSService) : RnConsultService {
    private val log = LogFactory.getLog(this.javaClass)
    val backingPersonService = ConsultrnPersonServiceImpl(EhealthReplyValidatorImpl());
    val backingCbssPersonService = ConsultrnCBSSPersonServiceImpl(EhealthReplyValidatorImpl());
    val historyService = SsinHistoryTokenServiceImpl(EhealthReplyValidatorImpl())
    val verifyIdService = IdSupportServiceImpl(EhealthReplyValidatorImpl())

    override fun searchPersonBySsin(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): RnConsultSearchPersonBySsinResponseDto {
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

            RnConsultSearchPersonBySsinResponseDto(
                ssin = searchPersonBySsinResponse?.ssin,
                result = RnConsultSearchByNissResultDto(
                    person = RnConsultPersonDto(
                        ssin = searchPersonBySsinResponse?.result?.person?.ssin,
                        nobilityTitle = searchPersonBySsinResponse?.result?.person?.nobilityTitle,
                        name = RnConsultNameType(
                            lastName = searchPersonBySsinResponse?.result?.person?.name?.lastName,
                            firstName = searchPersonBySsinResponse?.result?.person?.name?.givenNames?.joinToString { it.value },
                            inceptionDate = searchPersonBySsinResponse?.result?.person?.name?.inceptionDate.toString()
                        ),
                        nationalities = searchPersonBySsinResponse?.result?.person?.nationalities,
                        birth = searchPersonBySsinResponse?.result?.person?.birth,
                        decease = RnConsultDeceaseType().apply {
                            deceaseDate = searchPersonBySsinResponse?.result?.person?.decease?.deceaseDate
                            deceasePlace = searchPersonBySsinResponse?.result?.person?.decease?.deceasePlace
                            searchPersonBySsinResponse?.result?.person?.decease?.deceaseDate.let {
                                if (it != null){
                                    isDecease = true
                                }else{
                                    isDecease = false
                                }
                            }
                        },
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
                xmlConversations = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonBySsinRequest),
                    response = ConnectorXmlUtils.toString(searchPersonBySsinResponse)
                ),
                exception = null
            )
        }catch (ex: Exception){
            log.info("Error: "+ex)
            RnConsultSearchPersonBySsinResponseDto(
                result = null,
                status = null,
                id = null,
                inResponseTo = null,
                issueInstant = null,
                xmlConversations = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonBySsinRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                exception = ex
            )
        }

    }

    override fun searchPersonPhonetically(keystoreId: UUID, tokenId: UUID, passPhrase: String, dateOfBirth: Int, lastName: String, firstName: String?, middleName: String?, matchingType: String?, gender: String?, countryCode: Int, cityCode: String?, tolerance: Int?, limit: Int?): RnConsultSearchPersonPhoneticallyResponseDto {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        val givenNames = mutableListOf<GivenNameType>()
        if(!firstName.isNullOrBlank()){
            givenNames.add(GivenNameType().apply {
                value = firstName?.capitalize()
                sequence = 1
            })
        }else{
            //Add empty value if we don't have firstname with IGNORE_GIVENNAME matching type because in the xsd givenName node is mandatory
            if(matchingType.equals("IGNORE_GIVENNAME")){
                givenNames.add(GivenNameType().apply {
                    value = " "
                    sequence = 1
                })
            }
        }

       if(!middleName.isNullOrEmpty()){
            givenNames.add(GivenNameType().apply {
                value = middleName?.capitalize()
                sequence = 2
            })
        }

        val searchPersonPhoneticallyRequest = SearchPersonPhoneticallyRequest().apply {
            applicationId = "0"
            issueInstant = DateTime.now()
            id = "ID${System.currentTimeMillis()}"
            criteria = SearchPersonPhoneticallyCriteriaType().apply {
                this.name = PhoneticName().apply {
                    this.lastName = lastName.capitalize()
                    this.givenNames.addAll(givenNames)
                    this.givenNameMatching = GivenNameMatchingType.fromValue(matchingType).value()
                }

                this.birth = PhoneticBirth().apply {
                    this.birthDate = dateOfBirth.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                    this.variation = tolerance
                }

                gender.let {
                    this.gender = PhoneticGender().apply {
                        if (gender?.toUpperCase() == "MALE"){
                            this.genderCode = "M"
                        }else{
                            this.genderCode = "F"
                        }
                    }
                }

                if(countryCode != 0){
                    countryCode.let {
                        this.address = PhoneticAddress().apply {
                            this.countryCode = countryCode
                            if(!cityCode.isNullOrEmpty()){this.cityCode = cityCode}
                        }
                    }
                }
                this.maximumResultCount = limit
            }
        }

        return try{
            val searchPersonPhoneticallyResponse = backingPersonService.searchPersonPhonetically(samlToken, searchPersonPhoneticallyRequest)
            log.info("SearchPersonPhonetically response: "+ ConnectorXmlUtils.toString(searchPersonPhoneticallyResponse))

            RnConsultSearchPersonPhoneticallyResponseDto(
                id = searchPersonPhoneticallyResponse?.id,
                inResponseTo = searchPersonPhoneticallyResponse?.inResponseTo,
                issueInstant = searchPersonPhoneticallyResponse?.issueInstant,
                status = searchPersonPhoneticallyResponse?.status,
                result = searchPersonPhoneticallyResponse?.result,
                xmlConversations = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonPhoneticallyRequest),
                    response = ConnectorXmlUtils.toString(searchPersonPhoneticallyResponse)
                )
            )
        }catch (ex: Exception){
            log.info("Error: "+ex)
            RnConsultSearchPersonPhoneticallyResponseDto(
                result = null,
                status = null,
                id = null,
                inResponseTo = null,
                issueInstant = null,
                xmlConversations = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(searchPersonPhoneticallyRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                exception = ex
            )
        }
    }

    override fun registerPerson(keystoreId: UUID, tokenId: UUID, passPhrase: String, mid: RnConsultPersonMid): RnConsultRegisterPersonResponseDto {
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
            id = "ID${System.currentTimeMillis()}"
            applicationId = "0"
            issueInstant = DateTime.now()
            declaration = RegisterPersonDeclarationType().apply {
                person = CbssPersonRequestType().apply {
                    name = NameInfoDeclarationType().apply {
                        this.lastName = mid?.lastName?.capitalize()
                        this.givenNames.addAll(givenNames)
                        inceptionDate = DateTime.now()
                    }

                    mid?.nationalityCode.let {
                        nationalities = NationalitiesDeclarationType().apply {
                            nationalities.add(NationalityInfoBaseType().apply {
                                nationalityCode = it
                                inceptionDate = DateTime.now()
                            })
                        }
                    }

                    mid?.dateOfBirth.let {
                        birth = BirthInfoDeclarationType().apply {
                            birthDate = it.toString().replace(Regex("(....)(..)(..)"), "$1-$2-$3")
                            birthPlace = mid?.birthPlace?.let {
                                LocationDeclarationType().apply {
                                    countryCode = it.countryCode
                                    it.countryName.let {
                                        if(!it.isNullOrEmpty()){
                                            countryNames.add(LocalizedDescriptionType().apply {
                                                value = it
                                                lang = mid?.language?.toUpperCase()
                                            })
                                        }
                                    }
                                    it.cityCode.let {
                                        cityCode = it
                                    }

                                    it.cityName?.let {
                                        cityNames.add(LocalizedDescriptionType().apply {
                                            value = it
                                            lang = mid?.language?.toUpperCase()
                                        })
                                    }
                                    it.countryIsoCode.let {
                                        countryIsoCode = it
                                    }
                                }
                            }
                        }
                    }

                    mid.gender?.let {
                        gender = GenderInfoDeclarationType().apply {
                           if(it?.toUpperCase() == "MALE"){
                               this.genderCode = "M"
                           }else{
                               this.genderCode = "F"
                           }
                            inceptionDate = DateTime.now()
                        }
                    }
                    mid?.residentialAddress?.let {
                        address = AddressDeclarationType().apply {
                            residentialAddress = ForeignAddressDeclarationType().apply {
                                it.countryCode?.let {
                                    if(it != 0){
                                        this.countryCode = it
                                    }
                                }
                                it.countryIsoCode.let {
                                    this.countryIsoCode = it
                                }
                                it.countryName.let {
                                    this.countryName = LocalizedDescriptionType().apply {
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
                                it.streetName?.let {
                                    this.streetName = LocalizedDescriptionType().apply {
                                        value = it
                                        lang = mid?.language?.toUpperCase()
                                    }
                                }
                                it.houseNumber?.let {
                                    this.houseNumber = it
                                }
                                it.boxNumber?.let {
                                    this.boxNumber = it
                                }
                                inceptionDate = DateTime.now()
                            }
                        }
                    }

                    mid?.contactAddress?.let {
                        contactAddress = ContactAddressDeclarationType().apply {
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
                                    lang = mid?.language?.toUpperCase()
                                }
                            }
                            it.cityCode.let{
                                this.cityCode = it
                            }
                            it.cityName?.let {
                                this.cityName = LocalizedDescriptionType().apply {
                                    value = it
                                    lang = mid?.language?.toUpperCase()
                                }
                            }
                            it.postalCode.let {
                                this.postalCode = it
                            }

                            it.streetName?.let {
                                this.streetName = LocalizedDescriptionType().apply {
                                    value = it
                                    lang = mid?.language?.toUpperCase()
                                }
                            }
                            it.typeCode?.let {
                                if(it != 0){
                                    this.typeCode = it
                                }
                            }
                            inceptionDate = DateTime.now()
                        }
                    }
                }
            }
        }

        return try{
            val registerPersonResponse = backingCbssPersonService.registerPerson(samlToken, registerPersonRequest)
            RnConsultRegisterPersonResponseDto(
                id = registerPersonResponse.id,
                issueInstant = registerPersonResponse.issueInstant,
                inResponseTo = registerPersonResponse.inResponseTo,
                status = registerPersonResponse.status,
                declaration = registerPersonResponse.declaration,
                result = registerPersonResponse.result,
                xmlConversation = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(registerPersonRequest),
                    response = ConnectorXmlUtils.toString(registerPersonResponse)
                ),
                error = null
            )
        }catch (ex: SoaErrorException){
            log.info("Error: "+ConnectorXmlUtils.toString(ex))
            RnConsultRegisterPersonResponseDto(
                id = ex.responseTypeV2.id,
                issueInstant = ex.responseTypeV2.issueInstant,
                inResponseTo = ex.responseTypeV2.inResponseTo,
                status = null,
                declaration = null,
                result = null,
                xmlConversation = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(registerPersonRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                error = ErrorDto(code = ex.errorCode, descr = ex.message)
            )
        }catch (ex: SOAPFaultException){
            log.info("Error: "+ConnectorXmlUtils.toString(ex))
            RnConsultRegisterPersonResponseDto(
                id = null,
                issueInstant = null,
                inResponseTo = null,
                status = null,
                declaration = null,
                result = null,
                xmlConversation = RnConsultConversationDto(
                    request = ConnectorXmlUtils.toString(registerPersonRequest),
                    response = ConnectorXmlUtils.toString(ex)
                ),
                error = null
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

    override fun verifyId(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String?, cardNumber: String?, barCoded: String?): VerifyIdResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Rn consult operations")

        val ssinType = "urn:be:fgov:person:ssin"
        val cardNumberType = "urn:be:fgov:person:cardsupport:cardnumber"
        val barCodedType = "urn:be:fgov:person:cardsupport:barcoded"

        val verifyIdRequest = VerifyIdRequest().apply {
            id = "ID${System.currentTimeMillis()}"
            issueInstant = DateTime.now()
            legalContext = "patient insurance validation"
            identificationData = IdentificationData().apply {
                this.ids.addAll(listOf(
                    ssin.let {
                        Id().apply {
                            type = ssinType
                            value = ssin
                        }
                    },
                    cardNumber.let {
                        Id().apply {
                            type = cardNumberType
                            value = cardNumber
                        }
                    },
                    barCoded.let {
                        if(!barCoded.isNullOrEmpty()){
                            Id().apply {
                                type = barCodedType
                                value = barCoded
                            }
                        }else{
                            null
                        }
                    })
                )
            }
        }

        return verifyIdService.verifyId(verifyIdRequest, samlToken)
    }
}
