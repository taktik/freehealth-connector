package be.ehealth.technicalconnector.validator.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.ehealth.technicalconnector.validator.XMLValidator;
import java.util.HashMap;
import java.util.Map;

public class XMLValidatorImpl implements XMLValidator {
   protected static final Map<Class, String> XSD_FILE_LOCATION_FOR_CLASS_MAP = new HashMap();

   public XMLValidatorImpl() {
   }

   public void validate(Object xmlObject) throws TechnicalConnectorException {
      if (xmlObject != null) {
         ValidatorHelper.validate(xmlObject, xmlObject.getClass(), this.getXsdFileLocationForXmlObject(xmlObject));
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, new Object[]{"xml object had null value"});
      }
   }

   private String getXsdFileLocationForXmlObject(Object xmlObject) throws TechnicalConnectorException {
      if (xmlObject != null && XSD_FILE_LOCATION_FOR_CLASS_MAP.containsKey(xmlObject.getClass())) {
         return (String)XSD_FILE_LOCATION_FOR_CLASS_MAP.get(xmlObject.getClass());
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, new Object[]{"no xsd source defined for xmlObject " + xmlObject});
      }
   }
}
