package org.taktik.connector.business.civicsv2.validator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.validator.ValidatorHelper;

public final class XmlValidator {
   private XmlValidator() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static void validateXml(Object xmlObject) throws TechnicalConnectorException {
      ValidatorHelper.validate(xmlObject, "/ehealth-civics/XSD/sam-civics-protocol-2_0.xsd");
   }
}
