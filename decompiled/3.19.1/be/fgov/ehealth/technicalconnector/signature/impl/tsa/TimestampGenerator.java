package be.fgov.ehealth.technicalconnector.signature.impl.tsa;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public interface TimestampGenerator {
   byte[] generate(String var1, String var2, byte[] var3) throws TechnicalConnectorException;
}
