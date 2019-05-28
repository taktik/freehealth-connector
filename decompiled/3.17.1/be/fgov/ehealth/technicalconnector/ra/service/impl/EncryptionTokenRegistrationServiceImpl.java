package be.fgov.ehealth.technicalconnector.ra.service.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.etkra.protocol.v2.ActivateETKRequest;
import be.fgov.ehealth.etkra.protocol.v2.ActivateETKResponse;
import be.fgov.ehealth.etkra.protocol.v2.CompleteETKRegistrationRequest;
import be.fgov.ehealth.etkra.protocol.v2.CompleteETKRegistrationResponse;
import be.fgov.ehealth.etkra.protocol.v2.StartETKRegistrationRequest;
import be.fgov.ehealth.etkra.protocol.v2.StartETKRegistrationResponse;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.exceptions.RaException;
import be.fgov.ehealth.technicalconnector.ra.service.EncryptionTokenRegistrationService;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import be.fgov.ehealth.technicalconnector.ra.utils.RaUtils;
import java.security.PublicKey;

public class EncryptionTokenRegistrationServiceImpl implements EncryptionTokenRegistrationService {
   public Result<byte[]> startETKRegistration(PublicKey key, Credential credential) throws TechnicalConnectorException {
      StartETKRegistrationRequest req = new StartETKRegistrationRequest();
      RaUtils.setCommonAttributes(req);
      req.setPublicEncryptionKey(key.getEncoded());
      Result<StartETKRegistrationResponse> resp = RaUtils.invokeEtkRa(RaUtils.sign(req, req.getId(), credential), "urn:be:fgov:ehealth:etee:etkra:protocol:v2:startETKRegistration", StartETKRegistrationResponse.class);
      return new Result(((StartETKRegistrationResponse)resp.getResult()).getChallenge(), (StatusResponseType)resp.getResult());
   }

   public Result<Void> completeETKRegistration(byte[] etk, Credential credential) throws TechnicalConnectorException {
      CertificateUtils.toX509Certificate(etk);
      CompleteETKRegistrationRequest req = new CompleteETKRegistrationRequest();
      RaUtils.setCommonAttributes(req);
      req.setToBeRegistered(etk);
      Result<CompleteETKRegistrationResponse> response = RaUtils.invokeEtkRa(RaUtils.sign(req, req.getId(), credential), "urn:be:fgov:ehealth:etee:etkra:protocol:v2:completeETKregistration", CompleteETKRegistrationResponse.class);
      if (response.hasStatusError()) {
         throw new RaException("Unable to complete ETK Registration", response.getCause(), (StatusResponseType)response.getResult());
      } else {
         return new Result((Void)null);
      }
   }

   public Result<Void> activateToken(Credential credential) throws TechnicalConnectorException {
      ActivateETKRequest req = new ActivateETKRequest();
      RaUtils.setCommonAttributes(req);
      Result<ActivateETKResponse> response = RaUtils.invokeEtkRa(RaUtils.sign(req, req.getId(), credential), "urn:be:fgov:ehealth:etee:etkra:protocol:v2:activateETK", ActivateETKResponse.class);
      if (response.hasStatusError()) {
         throw new RaException("Unable to activate ETK", response.getCause(), (StatusResponseType)response.getResult());
      } else {
         return new Result((Void)null);
      }
   }
}
