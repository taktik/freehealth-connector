package org.taktik.connector.business.genericasync.builders;

import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public interface ResponseObjectBuilder extends ConfigurableImplementation {
   boolean handlePostResponse(PostResponse var1) throws GenAsyncBusinessConnectorException;

   Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse var1) throws GenAsyncBusinessConnectorException;
}
