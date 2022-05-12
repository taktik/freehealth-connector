package be.fgov.ehealth.technicalconnector.signature.impl.tsa.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.ehealth.technicalconnector.utils.ConnectorCryptoUtils;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import be.fgov.ehealth.technicalconnector.signature.impl.SignatureUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.tsa.TimestampGenerator;
import java.util.Map;
import oasis.names.tc.dss._1_0.core.schema.DocumentHash;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.SignRequest;
import oasis.names.tc.dss._1_0.core.schema.SignResponse;
import oasis.names.tc.dss._1_0.core.schema.Timestamp;
import org.apache.xml.security.algorithms.JCEMapper;
import org.w3._2000._09.xmldsig.DigestMethod;

public class TimeStampGeneratorImpl implements ConfigurableImplementation, TimestampGenerator {
   private static final String ENDPOINT_TS_AUTHORITY_V2 = "endpoint.ts.authority.v2";
   private static final String SOAP_ACTION_TS_AUTHORITY_V2 = "urn:be:fgov:ehealth:timestamping:protocol:v2:stamp";
   private Map<String, Object> options;

   public TimeStampGeneratorImpl() {
   }

   public byte[] generate(String requestId, String digestAlgoUri, byte[] digest) throws TechnicalConnectorException {
      SignResponse response = this.invoke(requestId, digestAlgoUri, digest);
      if ("urn:oasis:names:tc:dss:1.0:resultmajor:Success".equals(response.getResult().getResultMajor())) {
         Timestamp ts = response.getSignatureObject().getTimestamp();
         if (ts.getOther() != null) {
            throw new UnsupportedOperationException("Only RFC3161 TimeStampToken is supported.");
         } else {
            return ts.getRFC3161TimeStampToken();
         }
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{response.getResult().getResultMajor() + " : minor result : " + response.getResult().getResultMinor() + " message: " + response.getResult().getResultMessage()});
      }
   }

   private SignResponse invoke(String requestId, String digestAlgoUri, byte[] digest) throws TechnicalConnectorException {
      GenericRequest req = new GenericRequest();
      req.setPayload((Object)this.generateSignRequest(requestId, digestAlgoUri, digest));
      req.setCredential(this.getCredential(), TokenType.X509);
      req.setEndpoint(this.determineEndpoint());
      req.setSoapAction("urn:be:fgov:ehealth:timestamping:protocol:v2:stamp");
      req.addDefaulHandlerChain();

      try {
         return (SignResponse)ServiceFactory.getGenericWsSender().send(req).asObject(SignResponse.class);
      } catch (Exception var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var6, new Object[]{"Unable to invoke TimestampAuthority"});
      }
   }

   private String determineEndpoint() throws TechnicalConnectorException {
      String tsaEndpoint = (String)SignatureUtils.getOption("SignatureTimestampEndpointTimestampAuthority", this.options, ConfigFactory.getConfigValidator().getProperty("endpoint.ts.authority.v2", "$uddi{uddi:ehealth-fgov-be:business:timestampauthority:v2}"));
      if (tsaEndpoint != null && !tsaEndpoint.isEmpty()) {
         return tsaEndpoint;
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"SignatureTimestampEndpointTimestampAuthorityis empty or property endpoint.ts.authority.v2 is not present in the properties."});
      }
   }

   private Credential getCredential() throws TechnicalConnectorException {
      Credential cred = (Credential)SignatureUtils.getOption("SignatureTimestampCredential", this.options, Session.getInstance().getSession().getHolderOfKeyCredential());
      if (cred == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"SignatureTimestampCredentialis empty or no active session."});
      } else {
         return cred;
      }
   }

   private SignRequest generateSignRequest(String requestId, String digestAlgoURI, byte[] transformed) throws TechnicalConnectorException {
      SignRequest request = new SignRequest();
      request.setRequestID(requestId);
      request.setProfile((String)SignatureUtils.getOption("SignatureTimestampProfile", this.options, "urn:ehealth:profiles:timestamping:2.1-cert"));
      InputDocuments inputDocuments = new InputDocuments();
      DocumentHash docHash = new DocumentHash();
      docHash.setDigestMethod(new DigestMethod());
      docHash.getDigestMethod().setAlgorithm(digestAlgoURI);
      docHash.setDigestValue(ConnectorCryptoUtils.calculateDigest(JCEMapper.translateURItoJCEID(digestAlgoURI), transformed));
      inputDocuments.getDocumentHash().add(docHash);
      request.setInputDocuments(inputDocuments);
      return request;
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.options = parameterMap;
   }
}
