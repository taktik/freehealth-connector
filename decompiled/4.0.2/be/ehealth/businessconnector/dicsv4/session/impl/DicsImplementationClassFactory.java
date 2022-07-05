package be.ehealth.businessconnector.dicsv4.session.impl;

import be.ehealth.business.common.validator.ValidatorFactory;
import be.ehealth.businessconnector.dicsv4.validator.DicsValidator;
import be.ehealth.businessconnector.dicsv4.validator.impl.DicsValidatorImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class DicsImplementationClassFactory extends ImplementationClassFactory {
   private static final String PROP_DICS_EHEALTH_VALIDATOR = "be.ehealth.businessconnector.dicsv4.ehealthreplyvalidator";
   private static final String PROP_DICS_VALIDATOR = "be.ehealth.businessconnector.dicsv4.validator";

   public DicsImplementationClassFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws ConnectorException {
      if (clazz.equals(DicsSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new DicsSessionServiceImpl(sessionValidator, ValidatorFactory.getEhealthReplyValidator("be.ehealth.businessconnector.dicsv4.ehealthreplyvalidator"), (DicsValidator)ValidatorFactory.getValidator("be.ehealth.businessconnector.dicsv4.validator", DicsValidatorImpl.class));
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported or the number of additional parameters(" + additionalParameters.length + ") are added ");
      }
   }
}
