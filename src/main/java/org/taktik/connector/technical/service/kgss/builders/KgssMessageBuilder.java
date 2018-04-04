package org.taktik.connector.technical.service.kgss.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;

public interface KgssMessageBuilder {
   GetNewKeyRequest sealGetNewKeyRequest(GetNewKeyRequestContent var1) throws TechnicalConnectorException;

   GetNewKeyResponseContent unsealGetNewKeyResponse(GetNewKeyResponse var1) throws TechnicalConnectorException;

   GetKeyRequest sealGetKeyRequest(GetKeyRequestContent var1) throws TechnicalConnectorException;

   GetKeyResponseContent unsealGetKeyResponse(GetKeyResponse var1) throws TechnicalConnectorException;
}
