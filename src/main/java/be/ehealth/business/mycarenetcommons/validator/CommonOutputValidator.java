package be.ehealth.business.mycarenetcommons.validator;

import be.ehealth.business.mycarenetdomaincommons.exception.ConnectorValidationException;
import be.ehealth.business.mycarenetdomaincommons.exception.ValidationError;
import be.ehealth.business.mycarenetdomaincommons.validator.AbstractMyCarenetValidator;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v2.CommonOutputType;
import java.util.ArrayList;
import java.util.List;

public class CommonOutputValidator extends AbstractMyCarenetValidator implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public static void validate(CommonOutputType commonOutput) throws ConnectorValidationException {
      String currentPath = "commonOnput";
      List<ValidationError> errors = new ArrayList();
      if (commonOutput != null) {
         validateReference(errors, addPath(currentPath, "InputReference"), commonOutput.getInputReference());
         validateReference(errors, addPath(currentPath, "NIPReference"), commonOutput.getNIPReference());
         validateReference(errors, addPath(currentPath, "OutputReference"), commonOutput.getOutputReference());
         if (!errors.isEmpty()) {
            throw new ConnectorValidationException(errors);
         }
      } else {
         errors.add(new ValidationError(currentPath, "the " + currentPath + " is null"));
         throw new ConnectorValidationException(errors);
      }
   }

   private static void validateReference(List<ValidationError> errors, String path, String inputReference) {
      if (inputReference == null) {
         errors.add(new ValidationError(path, "the " + path + " is null"));
      }

   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(CommonOutputType.class);
   }
}
