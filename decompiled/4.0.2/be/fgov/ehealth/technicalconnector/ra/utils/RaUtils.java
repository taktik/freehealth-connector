package be.fgov.ehealth.technicalconnector.ra.utils;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.etee.commons.core.v2.EteeErrorType;
import be.fgov.ehealth.etee.commons.core.v2.EteeStatusDetail;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.exceptions.RaException;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.soap.SOAPException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class RaUtils {
   public static final String SOAPACTION_CERTRA_GEN_CERT = "urn:be:fgov:ehealth:etee:certra:protocol:v2:generatecertificate";
   public static final String SOAPACTION_CERTRA_GET_CERT = "urn:be:fgov:ehealth:etee:certra:protocol:v2:getcertificate";
   public static final String SOAPACTION_CERTRA_QUAL = "urn:be:fgov:ehealth:certra:protocol:v2:getActorQualities";
   public static final String SOAPACTION_CERTRA_APPLICATIONIDS = "urn:be:fgov:ehealth:etee:certra:protocol:v2:getexistingapplicationids";
   public static final String SOAPACTION_CERTRA_GET_CERTIFICATE_INFO = "urn:be:fgov:ehealth:etee:certra:protocol:v2:getCertificateInfoForAuthenticationCertificate";
   public static final String SOAPACTION_CERTRA_REVOKE = "urn:be:fgov:ehealth:etee:certra:protocol:v2:revoke";
   public static final String SOAPACTION_ETKRA_START_REGISTRATION = "urn:be:fgov:ehealth:etee:etkra:protocol:v2:startETKRegistration";
   public static final String SOAPACTION_ETKRA_COMPLETE_ETK_REGISTRATION = "urn:be:fgov:ehealth:etee:etkra:protocol:v2:completeETKregistration";
   public static final String SOAPACTION_ETKRA_ACTIVATE_ETK = "urn:be:fgov:ehealth:etee:etkra:protocol:v2:activateETK";
   public static final String SOAPACTION_CERTRA_GEN_CONTRACT = "urn:be:fgov:ehealth:certra:protocol:v2:generateContract";
   public static final String SOAPACTION_CERTRA_ORGANIZATION_TYPES = "urn:be:fgov:ehealth:certra:protocol:v2:getGenericOrganizationTypes";
   public static final String SOAPACTION_CERTRA_GET_CERTIFICATE_INFO_FOR_CITIZEN = "urn:be:fgov:ehealth:certra:protocol:v2:getCertificateInfoForCitizen";
   public static final String SOAPACTION_CERTRA_GENERATE_REVOCATION_CONTRACT = "urn:be:fgov:ehealth:certra:protocol:v2:generateRevocationContract";
   public static final String SOAPACTION_CERTRA_SUBMIT_CSR_FOREIGNER = "urn:be:fgov:ehealth:certra:protocol:v2:submitCSRForForeigner";
   private static final Logger LOG = LoggerFactory.getLogger(RaUtils.class);
   private static final String ENDPOINT_ETEE_CERTRA = "endpoint.etee.certra";
   private static final String ENDPOINT_ETEE_ETKRA = "endpoint.etee.etkra";
   public static final String URN_BE_FGOV_EHEALTH_2_0_STATUS_SUCCESS = "urn:be:fgov:ehealth:2.0:status:Success";
   private static ConfigValidator config = ConfigFactory.getConfigValidator();

   public RaUtils() {
   }

   public static <T extends ResponseType> Result<T> invokeCertRa(String payload, String soapAction, Class<T> clazz) throws RaException {
      Result<T> result = invokeRa(payload, soapAction, config.getProperty("endpoint.etee.certra", "$uddi{uddi:ehealth-fgov-be:business:certra:v2}"), clazz);
      return handleResultStatusCode(result);
   }

   private static <T extends ResponseType> Result<T> handleResultStatusCode(Result<T> result) throws RaException {
      if (result.getResult() instanceof StatusResponseType) {
         StatusResponseType statusResponseType = (StatusResponseType)result.getResult();
         String statusCode = statusResponseType.getStatus().getStatusCode().getValue();
         if (!"urn:be:fgov:ehealth:2.0:status:Success".equals(statusCode)) {
            if (getErrorCodes(statusResponseType).contains("CERT_NOT_YET_DELIVERED")) {
               return new Result((new DateTime()).plusSeconds(10));
            }

            return new Result("(CertRA) " + statusResponseType.getStatus().getStatusMessage(), statusResponseType);
         }
      }

      return result;
   }

   public static <T extends StatusResponseType> Result<T> invokeEtkRa(String payload, String soapAction, Class<T> clazz) throws TechnicalConnectorException {
      Result<T> result = invokeRa(payload, soapAction, config.getProperty("endpoint.etee.etkra", "$uddi{uddi:ehealth-fgov-be:business:etkra:v2}"), clazz);
      return getResult(result);
   }

   public static String sign(Object payload, String payloadId, Credential credential) throws TechnicalConnectorException {
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XML);
      Map<String, Object> options = new HashMap();
      List<String> tranforms = new ArrayList();
      tranforms.add("http://www.w3.org/2001/10/xml-exc-c14n#");
      options.put("transformerList", tranforms);
      options.put("baseURI", payloadId);
      options.put("encapsulate", true);
      byte[] toSign = ConnectorXmlUtils.toByteArray(payload);
      byte[] signed = builder.sign(credential, toSign, options);
      String signedString = ConnectorIOUtils.toString(signed, Charset.UTF_8);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Signed request " + signedString);
      }

      return signedString;
   }

   public static void setCommonAttributes(RequestType payload) throws TechnicalConnectorException {
      payload.setId(generateRequestId());
      payload.setIssueInstant(DateTime.now());
   }

   public static void setIssueInstant(RequestType payload) throws TechnicalConnectorException {
      payload.setIssueInstant(DateTime.now());
   }

   public static String generateRequestId() throws TechnicalConnectorException {
      return "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();
   }

   public static List<String> getErrorCodes(StatusResponseType statusResponseType) {
      List<String> errorCodes = new ArrayList();
      Iterator var2 = statusResponseType.getStatus().getStatusDetail().getAnies().iterator();

      while(var2.hasNext()) {
         Object object = var2.next();
         EteeStatusDetail eteeStatusDetail = (EteeStatusDetail)ConnectorXmlUtils.toObject(ConnectorXmlUtils.toByteArray((Node)object), EteeStatusDetail.class);
         Iterator var5 = eteeStatusDetail.getErrors().iterator();

         while(var5.hasNext()) {
            EteeErrorType eteeErrorType = (EteeErrorType)var5.next();
            errorCodes.add(eteeErrorType.getCode());
         }
      }

      return errorCodes;
   }

   private static <T extends StatusResponseType> Result<T> getResult(Result<T> result) throws RaException {
      StatusResponseType statusResponseType = (StatusResponseType)result.getResult();
      String statusCode = statusResponseType.getStatus().getStatusCode().getValue();
      return !"urn:be:fgov:ehealth:2.0:status:Success".equals(statusCode) ? new Result("(EtkRA) " + statusResponseType.getStatus().getStatusMessage(), statusResponseType) : result;
   }

   private static <T> Result<T> invokeRa(String payload, String soapAction, String endpoint, Class<T> clazz) {
      try {
         GenericRequest request = new GenericRequest();
         request.setPayload(payload);
         request.setEndpoint(endpoint);
         request.setSoapAction(soapAction);
         request.addDefaulHandlerChain();
         return new Result(ServiceFactory.getGenericWsSender().send(request).asObject(clazz));
      } catch (SOAPException var5) {
         return new Result("", var5);
      } catch (TechnicalConnectorException var6) {
         return new Result("", var6);
      }
   }
}
