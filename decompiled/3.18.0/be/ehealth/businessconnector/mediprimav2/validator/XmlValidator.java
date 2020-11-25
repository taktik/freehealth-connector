package be.ehealth.businessconnector.mediprimav2.validator;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.validator.ValidatorHelper;

public final class XmlValidator {
   private static final String MEDIPRIMA_XSD = "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-2_0.xsd";

   private XmlValidator() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static void validateXml(Object xmlObject) throws TechnicalConnectorException {
      ValidatorHelper.validate(xmlObject, "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-2_0.xsd");
   }
}
