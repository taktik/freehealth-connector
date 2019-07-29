package be.ehealth.business.mycarenetdomaincommons.validator;

import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.business.mycarenetdomaincommons.exception.ConnectorValidationException;
import be.ehealth.business.mycarenetdomaincommons.exception.ValidationError;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class RoutingValidator extends AbstractMyCarenetValidator {
   public static void validate(Routing validRouting) throws ConnectorValidationException {
      List<ValidationError> errors = new ArrayList();
      String path = "Routing";
      if (validRouting == null) {
         errors.add(new ValidationError(path, "the Routing element was null"));
      } else {
         validateCareReceiver(errors, addPath(path, "CareReceiver"), validRouting.getCareReceiver());
         validateReferenceDate(errors, addPath(path, "ReferenceDate"), validRouting.getReferenceDate());
         validatePeriod(errors, addPath(path, "Period"), validRouting.getPeriod());
      }

      if (!errors.isEmpty()) {
         throw new ConnectorValidationException(errors);
      }
   }

   private static void validatePeriod(List<ValidationError> errors, String path, Period period) {
      if (period != null) {
         if (period.getBegin() != null && period.getEnd() != null) {
            if (period.getBegin().getMillis() > period.getEnd().getMillis()) {
               errors.add(new ValidationError(path, "begindate " + period.getBegin() + " is after endDate " + period.getEnd()));
            }
         } else {
            errors.add(new ValidationError(path, "begindate " + period.getBegin() + " or endDate " + period.getEnd() + " is null, period should always contain both"));
         }
      }

   }

   private static void validateReferenceDate(List<ValidationError> errors, String path, DateTime referenceDate) {
      if (referenceDate == null) {
         errors.add(new ValidationError(path, "the reference date is a required field"));
      }

   }

   private static void validateCareReceiver(List<ValidationError> errors, String path, CareReceiverId careReceiver) {
      if (careReceiver == null) {
         errors.add(new ValidationError(path, "no carereceiver filled out"));
      } else if (!isAValidCombinationIsFilledOut(careReceiver.getSsinNumber(), careReceiver.getMutuality(), careReceiver.getRegistrationNumberWithMutuality())) {
         errors.add(new ValidationError(path, "no valid combination for careReceiverIds found  " + careReceiver + " : at least ssinNumber or combination of mutuality and mutualityRegistrationNumber is required"));
      }

   }

   private static boolean isAValidCombinationIsFilledOut(String ssinNumber, String mutuality, String registrationNumberWithMutuality) {
      if (isFilledOut(ssinNumber)) {
         return true;
      } else {
         return isFilledOut(registrationNumberWithMutuality) && isFilledOut(mutuality);
      }
   }

   private static boolean isFilledOut(String ssinNumber) {
      return ssinNumber != null && !ssinNumber.isEmpty();
   }
}
