package be.ehealth.business.mycarenetcommons.validator;

import be.ehealth.business.mycarenetdomaincommons.exception.ConnectorValidationException;
import be.ehealth.business.mycarenetdomaincommons.exception.ValidationError;
import be.ehealth.business.mycarenetdomaincommons.validator.AbstractMyCarenetValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonOutputType;
import java.util.ArrayList;
import java.util.List;

public class CommonOutputValidator extends AbstractMyCarenetValidator implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public CommonOutputValidator() {
   }

   public static void validate(CommonOutputType commonOutput) throws ConnectorValidationException {
      validateCommonOutput(commonOutput);
   }

   public static void validate(be.fgov.ehealth.mycarenet.commons.core.v2.CommonOutputType commonOutput) throws ConnectorValidationException {
      validateCommonOutput(commonOutput);
   }

   private static void validateCommonOutput(Object commonOutput) throws ConnectorValidationException {
      String currentPath = "commonOutput";
      List<ValidationError> errors = new ArrayList();
      if (commonOutput == null) {
         errors.add(new ValidationError(currentPath, "the " + currentPath + " is null"));
         throw new ConnectorValidationException(errors);
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(CommonOutputType.class);
   }
}
