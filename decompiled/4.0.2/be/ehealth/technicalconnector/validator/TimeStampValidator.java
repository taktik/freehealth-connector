package be.ehealth.technicalconnector.validator;

import be.ehealth.technicalconnector.exception.InvalidTimeStampException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.bouncycastle.tsp.TimeStampToken;

public interface TimeStampValidator {
   /** @deprecated */
   @Deprecated
   String ALIASLIST = "timestampvalidatior.aliaslist";
   String KEYSTORE = "timestampvalidatior.keystore";

   void validateTimeStampToken(TimeStampToken var1) throws InvalidTimeStampException, TechnicalConnectorException;

   void validateTimeStampToken(byte[] var1, TimeStampToken var2) throws InvalidTimeStampException, TechnicalConnectorException;
}
