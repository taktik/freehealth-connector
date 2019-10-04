package org.taktik.connector.business.mycarenetcommons.mapper.v3

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareProvider
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareReceiverId
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput
import org.taktik.connector.business.mycarenetdomaincommons.domain.Identification
import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo
import org.taktik.connector.business.mycarenetdomaincommons.domain.Nihii
import org.taktik.connector.business.mycarenetdomaincommons.domain.Party
import org.taktik.connector.business.mycarenetdomaincommons.domain.Period
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.utils.ByteArrayDatasource
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType
import be.fgov.ehealth.mycarenet.commons.core.v3.CareProviderType
import be.fgov.ehealth.mycarenet.commons.core.v3.CareReceiverIdType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v3.IdType
import be.fgov.ehealth.mycarenet.commons.core.v3.LicenseType
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v3.PackageType
import be.fgov.ehealth.mycarenet.commons.core.v3.PartyType
import be.fgov.ehealth.mycarenet.commons.core.v3.PeriodType
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType
import be.fgov.ehealth.mycarenet.commons.core.v3.ValueRefString
import java.io.IOException
import javax.activation.DataHandler
import org.apache.commons.io.IOUtils

class SendRequestMapper : ConfigurationModuleBootstrap.ModuleBootstrapHook {

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(BlobType::class.java)
        JaxbContextFactory.initJaxbContext(CareProviderType::class.java)
        JaxbContextFactory.initJaxbContext(CareReceiverIdType::class.java)
        JaxbContextFactory.initJaxbContext(CommonInputType::class.java)
        JaxbContextFactory.initJaxbContext(IdType::class.java)
        JaxbContextFactory.initJaxbContext(LicenseType::class.java)
        JaxbContextFactory.initJaxbContext(NihiiType::class.java)
        JaxbContextFactory.initJaxbContext(OriginType::class.java)
        JaxbContextFactory.initJaxbContext(PackageType::class.java)
        JaxbContextFactory.initJaxbContext(PartyType::class.java)
        JaxbContextFactory.initJaxbContext(PeriodType::class.java)
        JaxbContextFactory.initJaxbContext(RequestType::class.java)
        JaxbContextFactory.initJaxbContext(RoutingType::class.java)
        JaxbContextFactory.initJaxbContext(ValueRefString::class.java)
    }

    companion object {
        fun mapCommonInput(commonInput: CommonInput): CommonInputType {
            val inputType = CommonInputType()
            inputType.origin = getOrigin(commonInput)
            inputType.inputReference = commonInput.inputReference
            inputType.request = getRequestType(commonInput.isTest!!)
            return inputType
        }

        fun mapRouting(inRouting: Routing): RoutingType {
            val routing = RoutingType()
            routing.careReceiver = getCareReceiver(inRouting.careReceiver)
            routing.period = getPeriod(inRouting.period)
            routing.referenceDate = inRouting.referenceDate
            return routing
        }

        fun mapBlobToBlobType(inBlob: Blob): BlobType {
            val blob = BlobType()
            blob.id = inBlob.id
            blob.value = inBlob.content
            blob.hashValue = inBlob.hashValue
            blob.contentEncoding = inBlob.contentEncoding
            blob.contentType = inBlob.contentType
            blob.contentEncryption = inBlob.contentEncryption
            return blob
        }

        fun mapBlobTypeToBlob(inBlob: BlobType): Blob {
            val blob = Blob()
            blob.id = inBlob.id
            blob.content = inBlob.value
            blob.hashValue = inBlob.hashValue
            blob.contentEncoding = inBlob.contentEncoding
            blob.contentType = inBlob.contentType
            return blob
        }

        private fun getOrigin(commonInput: CommonInput): OriginType {
            val origin = OriginType()
            origin.careProvider = getCareprovider(commonInput.origin.careProvider)
            origin.setPackage(getPackage(commonInput.origin.mcnPackageInfo))
            origin.sender = getParty(commonInput.origin.sender)
            return origin
        }

        private fun getCareprovider(inProvider: CareProvider): CareProviderType {
            val careProvider = CareProviderType()
            careProvider.nihii = getNihii(inProvider.nihii)
            careProvider.organization = getIdType(inProvider.organization)
            careProvider.physicalPerson = getIdType(inProvider.physicalPerson)
            return careProvider
        }

        private fun getRequestType(isTest: Boolean): RequestType {
            val requestType = RequestType()
            requestType.isIsTest = isTest
            return requestType
        }

        private fun getCareReceiver(inCareReceiver: CareReceiverId): CareReceiverIdType {
            val careReceiver = CareReceiverIdType()
            careReceiver.mutuality = inCareReceiver.mutuality
            careReceiver.regNrWithMut = inCareReceiver.registrationNumberWithMutuality
            careReceiver.ssin = inCareReceiver.ssinNumber
            return careReceiver
        }

        private fun getPackage(info: McnPackageInfo): PackageType {
            val type = PackageType()
            type.name = getValueRef(info.packageName, null as String?)
            type.license = getLicense(info.userName, info.password)
            return type
        }

        private fun getNihii(inNihii: Nihii?): NihiiType? {
            var nihii: NihiiType? = null
            if (inNihii != null) {
                nihii = NihiiType()
                nihii.value = getValueRef(inNihii.value, null as String?)
                nihii.quality = inNihii.quality
            }

            return nihii
        }

        private fun getIdType(id: Identification?): IdType? {
            var idType: IdType? = null
            if (id != null) {
                idType = IdType()
                idType.cbe = getValueRef(id.cbe, null as String?)
                idType.name = getValueRef(id.name, null as String?)
                idType.ssin = getValueRef(id.ssin, null as String?)
                idType.nihii = getNihii(id.nihii)
            }

            return idType
        }

        private fun getValueRef(value: String?, reference: String?): ValueRefString? {
            var valueRef: ValueRefString? = null
            if (value != null) {
                valueRef = ValueRefString()
                valueRef.value = value
                valueRef.valueRef = reference
            }

            return valueRef
        }

        private fun getLicense(userName: String, password: String): LicenseType {
            val license = LicenseType()
            license.username = userName
            license.password = password
            return license
        }

        private fun getParty(inParty: Party): PartyType {
            val party = PartyType()
            party.organization = getIdType(inParty.organization)
            party.physicalPerson = getIdType(inParty.physicalPerson)
            return party
        }

        private fun getPeriod(inPeriod: Period?): PeriodType? {
            var period: PeriodType? = null
            if (inPeriod != null) {
                period = PeriodType()
                period.start = inPeriod.begin
                period.end = inPeriod.end
            }

            return period
        }

        @Throws(TechnicalConnectorException::class)
        fun mapToBlob(blob: be.cin.types.v1.Blob): Blob {
            val result = Blob()
            result.content = convertToByteArray(blob.value)
            result.id = blob.id
            result.contentEncoding = blob.contentEncoding
            result.hashValue = blob.hashValue
            result.contentType = blob.contentType
            return result
        }

        fun mapBlobToCinBlob(blob: Blob): be.cin.types.v1.Blob {
            val result = be.cin.types.v1.Blob()
            val rawData = ByteArrayDatasource(blob.content)
            val dh = DataHandler(rawData)
            result.value = dh
            result.messageName = blob.messageName
            result.id = blob.id
            result.contentEncoding = blob.contentEncoding
            result.hashValue = blob.hashValue
            result.contentType = blob.contentType
            return result
        }

        @Throws(TechnicalConnectorException::class)
        private fun convertToByteArray(value: DataHandler?): ByteArray {
            return if (value == null) {
                ByteArray(0)
            } else {
                try {
                    IOUtils.toByteArray(value.inputStream)
                } catch (var2: IOException) {
                    throw TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, *arrayOf<Any>("IoException while converting dataHandler to byteArray", var2))
                }

            }
        }
    }
}
