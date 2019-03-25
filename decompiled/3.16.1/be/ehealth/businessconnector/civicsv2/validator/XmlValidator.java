package be.ehealth.businessconnector.civicsv2.validator;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.validator.ValidatorHelper;

public final class XmlValidator {
   private XmlValidator() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static void validateXml(Object xmlObject) throws TechnicalConnectorException {
      ValidatorHelper.validate(xmlObject, "/ehealth-civics/XSD/sam-civics-protocol-2_0.xsd");
   }
}
