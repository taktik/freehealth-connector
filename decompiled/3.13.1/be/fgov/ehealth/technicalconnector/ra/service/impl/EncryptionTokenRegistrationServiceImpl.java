package be.fgov.ehealth.technicalconnector.ra.service.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.fgov.ehealth.etkra.protocol.v1.ActivateETKRequest;
import be.fgov.ehealth.etkra.protocol.v1.ActivateETKResponse;
import be.fgov.ehealth.etkra.protocol.v1.CompleteEtkRegistrationRequest;
import be.fgov.ehealth.etkra.protocol.v1.CompleteEtkRegistrationResponse;
import be.fgov.ehealth.etkra.protocol.v1.StartEtkRegistrationRequest;
import be.fgov.ehealth.etkra.protocol.v1.StartEtkRegistrationResponse;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.service.EncryptionTokenRegistrationService;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import be.fgov.ehealth.technicalconnector.ra.utils.RaUtils;
import java.security.PublicKey;
import java.util.Map;

public class EncryptionTokenRegistrationServiceImpl implements EncryptionTokenRegistrationService, ConfigurableImplementation {
   private Credential cred;

   public byte[] registerPublicKey(PublicKey key) throws TechnicalConnectorException {
      StartEtkRegistrationRequest req = new StartEtkRegistrationRequest();
      req.setPublicEncryptionKey(RaUtils.transform(this.cred, key.getEncoded()));
      Result<StartEtkRegistrationResponse> resp = RaUtils.invokeEtkRa(req, "urn:be:fgov:ehealth:etee:etkra:registerpublickey", StartEtkRegistrationResponse.class);
      return ((StartEtkRegistrationResponse)resp.getResult()).getChallenge();
   }

   public Result<Void> registerToken(byte[] etk) throws TechnicalConnectorException {
      CertificateUtils.toX509Certificate(etk);
      CompleteEtkRegistrationRequest complete = new CompleteEtkRegistrationRequest();
      complete.setToBeRegistered(RaUtils.transform(this.cred, etk));
      Result<CompleteEtkRegistrationResponse> response = RaUtils.invokeEtkRa(complete, "urn:be:fgov:ehealth:etee:etkra:registertoken", CompleteEtkRegistrationResponse.class);
      if (response.hasStatusError()) {
         new Result("Unable to complete ETK Registration", response.getCause());
      }

      return new Result((Void)null);
   }

   public Result<Void> activateToken() throws TechnicalConnectorException {
      ActivateETKRequest req = new ActivateETKRequest();
      req.setSignedRequest(RaUtils.transform(this.cred, "ACTIVATE".getBytes()));
      Result<ActivateETKResponse> response = RaUtils.invokeEtkRa(req, "urn:be:fgov:ehealth:etee:etkra:activatetoken", ActivateETKResponse.class);
      if (response.hasStatusError()) {
         new Result("Unable to activate ETK", response.getCause());
      }

      return new Result((Void)null);
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.cred = (Credential)parameterMap.get("credential");
   }
}
