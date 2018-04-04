package be.ehealth.businessconnector.genericasync.builders;

import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public interface ResponseObjectBuilder extends ConfigurableImplementation {
   boolean handlePostResponse(PostResponse var1) throws GenAsyncBusinessConnectorException;

   Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse var1) throws GenAsyncBusinessConnectorException;
}
