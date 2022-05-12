package be.ehealth.businessconnector.mycarenet.attestv3.builders;

import be.ehealth.business.mycarenetcommons.domain.EncryptedRequestHolder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationRequest;

public interface RequestObjectBuilder {
   EncryptedRequestHolder<SendAttestationRequest> buildSendAttestationRequest(SendAttestationRequestInput var1) throws TechnicalConnectorException;

   CancelAttestationRequest buildCancelAttestationRequest(CancelAttestationRequestInput var1) throws TechnicalConnectorException;
}
