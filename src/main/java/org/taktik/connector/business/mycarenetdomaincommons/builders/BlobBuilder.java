package org.taktik.connector.business.mycarenetdomaincommons.builders;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableImplementation;

public interface BlobBuilder extends ConfigurableImplementation {
   String PROJECT_NAME_KEY = "projectName";

   Blob build(byte[] var1) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2, String var3, String var4) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2, String var3, String var4, String var5) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   Blob build(byte[] var1, String var2, String var3, String var4, String var5, String var6) throws InvalidBlobContentConnectorException, TechnicalConnectorException;

   byte[] checkAndRetrieveContent(Blob var1) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
}
