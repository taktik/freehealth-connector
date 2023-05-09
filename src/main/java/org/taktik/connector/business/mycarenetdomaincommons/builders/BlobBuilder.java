package org.taktik.connector.business.mycarenetdomaincommons.builders;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableImplementation;

import java.util.Map;

public interface BlobBuilder extends ConfigurableImplementation {
   void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException;
   Blob build(byte[] input) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
   Blob build(byte[] input, String id) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
   Blob build(byte[] input, String encodingType, String id, String contentType) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
   Blob build(byte[] input, String encodingType, String id, String contentType, String messageName) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
   Blob build(byte[] input, String encodingType, String id, String contentType, String messageName, String contentEncryption) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
   Blob build(byte[] input, String encodingType, String id, String contentType, String messageName, String messageVersion, String contentEncryption) throws InvalidBlobContentConnectorException, TechnicalConnectorException;
   byte[] checkAndRetrieveContent(Blob blob) throws TechnicalConnectorException;
}
