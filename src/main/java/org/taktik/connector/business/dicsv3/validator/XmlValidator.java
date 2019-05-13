package org.taktik.connector.business.dicsv3.validator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.validator.ValidatorHelper;

public final class XmlValidator {
   private static final String DICS_RESPONSE = "/ehealth-dics/XSD/ehealth-dics-protocol-3_0.xsd";

   private XmlValidator() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static void validateXml(Object xmlObject) throws TechnicalConnectorException {
      ValidatorHelper.Companion.validate(xmlObject, "/ehealth-dics/XSD/ehealth-dics-protocol-3_0.xsd");
   }
}
