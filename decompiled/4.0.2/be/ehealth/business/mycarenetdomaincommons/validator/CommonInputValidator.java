package be.ehealth.business.mycarenetdomaincommons.validator;

import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.business.mycarenetdomaincommons.exception.ConnectorValidationException;
import be.ehealth.business.mycarenetdomaincommons.exception.ValidationError;
import java.util.ArrayList;
import java.util.List;

public class CommonInputValidator extends AbstractMyCarenetValidator {
   public CommonInputValidator() {
   }

   public static void validate(CommonInput commonInput) throws ConnectorValidationException {
      String currentPath = "commonInput";
      List<ValidationError> errors = new ArrayList();
      validateIsTest(errors, addPath(currentPath, "isTest"), commonInput.isTest());
      validateOrigin(errors, addPath(currentPath, "Origin"), commonInput.getOrigin());
      validateInputReference(errors, addPath(currentPath, "InputReference"), commonInput.getInputReference());
      if (!errors.isEmpty()) {
         throw new ConnectorValidationException(errors);
      }
   }

   private static void validateInputReference(List<ValidationError> errors, String path, String inputReference) {
      if (inputReference == null) {
         errors.add(new ValidationError(path, "the inputReference is null"));
      }

   }

   private static void validateOrigin(List<ValidationError> errors, String path, Origin origin) {
      if (origin == null) {
         errors.add(new ValidationError(path, "Origin was null"));
      } else {
         if (origin.getCareProvider() == null && origin.getSender() == null) {
            errors.add(new ValidationError(path, "at least one of careProvider or Sender must be filled out"));
         }

         validateCareProvider(errors, addPath(path, "CareProvider"), origin.getCareProvider());
         validatePackageInfo(errors, addPath(path, "McnPackageInfo"), origin.getMcnPackageInfo());
         validateSender(errors, addPath(path, "Sender"), origin.getSender());
      }

   }

   private static void validateSender(List<ValidationError> errors, String path, Party sender) {
      if (sender != null) {
         validatePartyType(errors, path, sender);
      }

   }

   private static void validatePartyType(List<ValidationError> errors, String path, Party party) {
      if (party == null) {
         errors.add(new ValidationError(path, "required party was null"));
      } else {
         Identification organization = party.getOrganization();
         Identification physicalPerson = party.getPhysicalPerson();
         if (organization == null && physicalPerson == null) {
            errors.add(new ValidationError(path, "party without organisation and physicalPerson , at least one of them must be filled out"));
         }

         validateIdentificationIfNotNull(errors, addPath(path, "organisation"), organization);
         validateIdentificationIfNotNull(errors, addPath(path, "physicalPerson"), physicalPerson);
      }

   }

   private static void validateIdentificationIfNotNull(List<ValidationError> errors, String path, Identification organization) {
      if (organization != null) {
         validateIdentification(errors, path, organization);
      }

   }

   private static void validateIdentification(List<ValidationError> errors, String path, Identification identification) {
      if (identification == null) {
         errors.add(new ValidationError(path, "required identification is null"));
      } else {
         if (identification.getName() == null) {
            errors.add(new ValidationError(path, "the name if the identification is null, its a required parameter!"));
         }

         if (identification.getCbe() == null && identification.getNihii() == null && identification.getSsin() == null) {
            errors.add(new ValidationError(path, "no parameters are filled out in identification , at least a Cbe, nihii, or ssin is required!"));
         }
      }

   }

   private static void validatePackageInfo(List<ValidationError> errors, String path, McnPackageInfo packageInfo) {
      if (packageInfo == null) {
         errors.add(new ValidationError(path, "packageInfo is required, but was null"));
      } else {
         if (packageInfo.getPassword() == null) {
            errors.add(new ValidationError(path, "packageInfo.password was null"));
         }

         if (packageInfo.getUserName() == null) {
            errors.add(new ValidationError(path, "packageInfo.userName was null"));
         }
      }

   }

   private static void validateCareProvider(List<ValidationError> errors, String path, CareProvider careProvider) {
      if (careProvider != null) {
         validateNihii(errors, addPath(path, "Nihii"), careProvider.getNihii());
         if (careProvider.getOrganization() != null) {
            validateIdentification(errors, addPath(path, "Organisation"), careProvider.getOrganization());
         }

         if (careProvider.getPhysicalPerson() != null) {
            validateIdentification(errors, addPath(path, "PhysicalPerson"), careProvider.getPhysicalPerson());
         }
      }

   }

   private static void validateNihii(List<ValidationError> errors, String path, Nihii nihii) {
      if (nihii == null) {
         errors.add(new ValidationError(path, "Nihii was null"));
      } else {
         if (nihii.getQuality() == null) {
            errors.add(new ValidationError(path, "nihii.quality was null"));
         }

         if (nihii.getValue() == null) {
            errors.add(new ValidationError(path, "nihii.value was null"));
         }
      }

   }

   private static void validateIsTest(List<ValidationError> errors, String path, Boolean test) {
      if (test == null) {
         errors.add(new ValidationError(path, "the isTest variable should never be null"));
      }

   }
}
