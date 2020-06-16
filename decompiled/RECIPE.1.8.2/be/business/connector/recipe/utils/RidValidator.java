package be.business.connector.recipe.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class RidValidator {
   private static final String RID_PATTERN = "BE(P|K|N)(P|0|1|2|3|4|5|6|7|8|9)[(0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F | G | H | J | K | L | M | N | P | Q | R | S | T | V | W | X | Y | Z)]{8}";
   private static final int RID_LENGTH = 12;
   private static final Pattern ridPattern = Pattern.compile("BE(P|K|N)(P|0|1|2|3|4|5|6|7|8|9)[(0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F | G | H | J | K | L | M | N | P | Q | R | S | T | V | W | X | Y | Z)]{8}");
   private static final Logger LOG = Logger.getLogger(RidValidator.class);

   private RidValidator() {
   }

   /** @deprecated */
   @Deprecated
   private static void validateExecutorRidV4(String rid) throws IntegrationModuleException {
      validateRid(rid);
   }

   /** @deprecated */
   @Deprecated
   private static void validateExecutorRid(String rid) throws IntegrationModuleException {
      validateRid(rid);
   }

   /** @deprecated */
   @Deprecated
   private static void validatePrescriberRid(String rid) throws IntegrationModuleException {
      validateRid(rid);
   }

   /** @deprecated */
   @Deprecated
   private static void validatePatientRid(String rid) throws IntegrationModuleException {
      validateRid(rid);
   }

   public static void validateRid(String rid) throws IntegrationModuleException {
      Matcher ridMatcher = ridPattern.matcher(rid);
      if (!ridMatcher.find() || rid.length() != 12) {
         LOG.error("Invalid RID was provided.");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.rid.validation", new Object[]{rid}));
      }
   }
}
