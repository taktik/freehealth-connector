package be.fgov.ehealth.technicalconnector.ra.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import java.security.PublicKey;

public interface EncryptionTokenRegistrationService {
   Result<byte[]> startETKRegistration(PublicKey var1, Credential var2) throws TechnicalConnectorException;

   Result<Void> completeETKRegistration(byte[] var1, Credential var2) throws TechnicalConnectorException;

   Result<Void> activateToken(Credential var1) throws TechnicalConnectorException;
}
