package be.ehealth.business.kmehrcommons.validator.impl;

import be.ehealth.business.common.util.PomPropertiesUtil;
import be.ehealth.business.kmehrcommons.validator.KmehrValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;

public class KmehrValidatorImpl implements KmehrValidator {
   public static final String KMEHR_LOCATION = "/ehealth-kmehr/XSD/kmehr_elements-" + PomPropertiesUtil.getProperty("version.kmehr.major") + "_" + PomPropertiesUtil.getProperty("version.kmehr.minor") + ".xsd";

   public KmehrValidatorImpl() {
   }

   public void validateKmehrMessage(Kmehrmessage msg) throws TechnicalConnectorException {
      ValidatorHelper.validate(msg, Kmehrmessage.class, KMEHR_LOCATION);
   }
}
