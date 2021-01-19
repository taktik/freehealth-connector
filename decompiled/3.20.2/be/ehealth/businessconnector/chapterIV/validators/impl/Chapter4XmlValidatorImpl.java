package be.ehealth.businessconnector.chapterIV.validators.impl;

import be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response;
import be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Request;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.chapterIV.validators.Chapter4XmlValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrrequest;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.util.HashMap;
import java.util.Map;

public class Chapter4XmlValidatorImpl implements Chapter4XmlValidator, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final long serialVersionUID = -1497994839194474681L;
   protected static final Map<Class, String> XSD_FILE_LOCATION_FOR_CLASS_MAP = new HashMap();

   public void validate(Object xmlObject) throws TechnicalConnectorException, ChapterIVBusinessConnectorException {
      if (xmlObject != null) {
         ValidatorHelper.validate(xmlObject, xmlObject.getClass(), this.getXsdFileLocationForXmlObject(xmlObject));
      } else {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_XML_CHAPTER4VALIDATOR, "xml object had null value");
      }
   }

   private String getXsdFileLocationForXmlObject(Object xmlObject) throws ChapterIVBusinessConnectorException {
      if (xmlObject != null && XSD_FILE_LOCATION_FOR_CLASS_MAP.containsKey(xmlObject.getClass())) {
         return (String)XSD_FILE_LOCATION_FOR_CLASS_MAP.get(xmlObject.getClass());
      } else {
         throw new ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_XML_UNDEFINED_XSD_FOR_XML_CLASS_VALIDATOR, "no xsd source defined for xmlObject " + xmlObject);
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(Request.class);
      JaxbContextFactory.initJaxbContext(be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request.class);
      JaxbContextFactory.initJaxbContext(be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request.class);
      JaxbContextFactory.initJaxbContext(be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request.class);
      JaxbContextFactory.initJaxbContext(ConsultChap4MedicalAdvisorAgreementRequest.class);
      JaxbContextFactory.initJaxbContext(AskChap4MedicalAdvisorAgreementRequest.class);
      JaxbContextFactory.initJaxbContext(Kmehrrequest.class);
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
      JaxbContextFactory.initJaxbContext(Response.class);
      JaxbContextFactory.initJaxbContext(be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response.class);
      JaxbContextFactory.initJaxbContext(Kmehrresponse.class);
      JaxbContextFactory.initJaxbContext(FolderType.class);
   }

   static {
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Request.class, "/niccin-mycarenet-chapteriv/XSD/IO-BE-ConsultUnaddressed.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request.class, "/niccin-mycarenet-chapteriv/XSD/IO-BE-AskUnaddressed.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request.class, "/niccin-mycarenet-chapteriv/XSD/IO-IM-ConsultAddressed.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request.class, "/niccin-mycarenet-chapteriv/XSD/IO-IM-AskAddressed.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(ConsultChap4MedicalAdvisorAgreementRequest.class, "/ehealth-chapteriv/XSD/chap4services_protocol-1_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(AskChap4MedicalAdvisorAgreementRequest.class, "/ehealth-chapteriv/XSD/chap4services_protocol-1_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Kmehrrequest.class, "/validators/niccin-mycarenet-chapteriv/XSD/medicalagreement-core-1_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Kmehrmessage.class, "/validators/kmehr/XSD/kmehr_elements-1_5.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Response.class, "/niccin-mycarenet-chapteriv/XSD/MCN_ask_encrypted_response.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response.class, "/niccin-mycarenet-chapteriv/XSD/MCN_consult_encrypted_response.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Kmehrresponse.class, "/validators/niccin-mycarenet-chapteriv/XSD/medicalagreement-core-1_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(FolderType.class, "/ehealth-kmehr/XSD/kmehr_elements-ext.xsd");
   }
}
