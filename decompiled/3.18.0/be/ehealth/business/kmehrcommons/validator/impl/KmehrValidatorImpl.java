package be.ehealth.business.kmehrcommons.validator.impl;

import be.ehealth.business.kmehrcommons.validator.KmehrValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;

public class KmehrValidatorImpl implements KmehrValidator {
   protected static final String KMEHR_LOCATION = "/ehealth-kmehr/XSD/kmehr_elements-1_29.xsd";

   public void validateKmehrMessage(Kmehrmessage msg) throws TechnicalConnectorException {
      ValidatorHelper.validate(msg, Kmehrmessage.class, "/ehealth-kmehr/XSD/kmehr_elements-1_29.xsd");
   }
}
