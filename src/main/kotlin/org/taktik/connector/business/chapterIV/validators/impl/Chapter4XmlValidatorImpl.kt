package org.taktik.connector.business.chapterIV.validators.impl

import be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response
import be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Request
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues
import org.taktik.connector.business.chapterIV.validators.Chapter4XmlValidator
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import org.taktik.connector.technical.validator.ValidatorHelper
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrrequest
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import java.util.HashMap

class Chapter4XmlValidatorImpl : Chapter4XmlValidator, ConfigurationModuleBootstrap.ModuleBootstrapHook {

    @Throws(TechnicalConnectorException::class, ChapterIVBusinessConnectorException::class)
    override fun validate(xmlObject: Any?) {
        if (xmlObject != null) {
            ValidatorHelper.validate(xmlObject, xmlObject.javaClass, this.getXsdFileLocationForXmlObject(xmlObject))
        } else {
            throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_XML_CHAPTER4VALIDATOR, "xml object had null value")
        }
    }

    @Throws(ChapterIVBusinessConnectorException::class)
    private fun getXsdFileLocationForXmlObject(xmlObject: Any?): String {
        return if (xmlObject != null && XSD_FILE_LOCATION_FOR_CLASS_MAP.containsKey(xmlObject.javaClass)) {
            XSD_FILE_LOCATION_FOR_CLASS_MAP[xmlObject.javaClass] as String
        } else {
            throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_XML_UNDEFINED_XSD_FOR_XML_CLASS_VALIDATOR, "no xsd source defined for xmlObject " + xmlObject!!)
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(Request::class.java)
        JaxbContextFactory.initJaxbContext(be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request::class.java)
        JaxbContextFactory.initJaxbContext(be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request::class.java)
        JaxbContextFactory.initJaxbContext(be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request::class.java)
        JaxbContextFactory.initJaxbContext(ConsultChap4MedicalAdvisorAgreementRequest::class.java)
        JaxbContextFactory.initJaxbContext(AskChap4MedicalAdvisorAgreementRequest::class.java)
        JaxbContextFactory.initJaxbContext(Kmehrrequest::class.java)
        JaxbContextFactory.initJaxbContext(Kmehrmessage::class.java)
        JaxbContextFactory.initJaxbContext(Response::class.java)
        JaxbContextFactory.initJaxbContext(be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response::class.java)
        JaxbContextFactory.initJaxbContext(Kmehrresponse::class.java)
        JaxbContextFactory.initJaxbContext(FolderType::class.java)
    }

    companion object {
        private const val serialVersionUID = -1497994839194474681L
        protected val XSD_FILE_LOCATION_FOR_CLASS_MAP: MutableMap<Class<*>, String> = HashMap()

        init {
            XSD_FILE_LOCATION_FOR_CLASS_MAP[Request::class.java] = "/XSD/chapterIV_v1/IO-BE-ConsultUnaddressed.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request::class.java] =
                "/XSD/chapterIV_v1/IO-BE-AskUnaddressed.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request::class.java] =
                "/XSD/chapterIV_v1/IO-IM-ConsultAddressed.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request::class.java] =
                "/XSD/chapterIV_v1/IO-IM-AskAddressed.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[ConsultChap4MedicalAdvisorAgreementRequest::class.java] =
                "/XSD/chapterIV_v1/chap4services-protocol-1_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[AskChap4MedicalAdvisorAgreementRequest::class.java] =
                "/XSD/chapterIV_v1/chap4services-protocol-1_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[Kmehrrequest::class.java] =
                "/XSD/chapterIV_v1/medicalagreement-core-1_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[Kmehrmessage::class.java] = "/XSD/kmehr/kmehr_elements-1_5.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[Response::class.java] = "/XSD/chapterIV_v1/MCN_ask_encrypted_response.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response::class.java] =
                "/XSD/chapterIV_v1/MCN_consult_encrypted_response.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[Kmehrresponse::class.java] =
                "/XSD/chapterIV_v1/medicalagreement-core-1_0.xsd"
            XSD_FILE_LOCATION_FOR_CLASS_MAP[FolderType::class.java] = "/XSD/kmehr/kmehr_elements-1_5.xsd"
        }
    }
}
