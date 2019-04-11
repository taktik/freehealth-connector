package org.taktik.connector.business.kmehrcommons.validator.impl;

import org.taktik.connector.business.kmehrcommons.validator.KmehrValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.validator.ValidatorHelper;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;

public class KmehrValidatorImpl implements KmehrValidator {
   protected static final String KMEHR_LOCATION = "/ehealth-kmehr/XSD/kmehr_elements-1_19.xsd";

   public void validateKmehrMessage(Kmehrmessage msg) throws TechnicalConnectorException {
      ValidatorHelper.Companion.validate(msg, Kmehrmessage.class, "/ehealth-kmehr/XSD/kmehr_elements-1_19.xsd");
   }
}
