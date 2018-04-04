package be.fgov.ehealth.technicalconnector.ra.utils;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.certra.protocol.v1.ErrorType;
import be.fgov.ehealth.certra.protocol.v1.RaResponseType;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.exceptions.RaException;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.soap.SOAPException;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RaUtils {
   public static final String SOAPACTION_CERTRA_GEN_CERT = "urn:be:fgov:ehealth:etee:certra:generatecertificate";
   public static final String SOAPACTION_CERTRA_GET_CERT = "urn:be:fgov:ehealth:etee:certra:getcertificate";
   public static final String SOAPACTION_CERTRA_QUAL = "urn:be:fgov:ehealth:etee:certra:getehactorqualities";
   public static final String SOAPACTION_CERTRA_APPLICATIONIDS = "urn:be:fgov:ehealth:etee:certra:getexistingapplicationids";
   public static final String SOAPACTION_CERTRA_RENEW_CERT = "urn:be:fgov:ehealth:etee:certra:renewcertificate";
   public static final String SOAPACTION_CERTRA_REVOCABLES = "urn:be:fgov:ehealth:etee:certra:getrevocablecertificates";
   public static final String SOAPACTION_CERTRA_REVOKE = "urn:be:fgov:ehealth:etee:certra:revokecertificate";
   public static final String SOAPACTION_ETKRA_REGISTER_PK = "urn:be:fgov:ehealth:etee:etkra:registerpublickey";
   public static final String SOAPACTION_ETKRA_REGISTER_TOKEN = "urn:be:fgov:ehealth:etee:etkra:registertoken";
   public static final String SOAPACTION_ETKRA_ACTIVATE_TOKEN = "urn:be:fgov:ehealth:etee:etkra:activatetoken";
   private static final Logger LOG = LoggerFactory.getLogger(RaUtils.class);
   private static final String ENDPOINT_ETEE_CERTRA = "endpoint.etee.certra";
   private static final String ENDPOINT_ETEE_ETKRA = "endpoint.etee.etkra";
   private static ConfigValidator config = ConfigFactory.getConfigValidator();

   public static <T> byte[] transform(Credential cred, T object, Class<T> clazz) throws TechnicalConnectorException {
      MarshallerHelper<T, T> helper = new MarshallerHelper(clazz, clazz);
      return transform(cred, helper.toXMLByteArray(object));
   }

   public static <T> byte[] transform(Credential cred, byte[] data) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      options.put("signatureAlgorithm", RaPropertiesLoader.getProperty("csr.signature.algorithm"));
      options.put("encapsulate", Boolean.TRUE);
      return SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.CAdES).sign(cred, data, options);
   }

   public static <T> T transform(byte[] signedContent, Class<T> clazz) throws TechnicalConnectorException {
      try {
         CMSSignedData s = new CMSSignedData(signedContent);
         CMSProcessableByteArray cpb = (CMSProcessableByteArray)s.getSignedContent();
         byte[] unsignedContent = (byte[])((byte[])cpb.getContent());
         MarshallerHelper<T, T> helper = new MarshallerHelper(clazz, clazz);
         return helper.toObject(unsignedContent);
      } catch (CMSException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, var6, new Object[0]);
      }
   }

   public static <T extends RaResponseType> Result<T> invokeCertRa(Object payload, String soapAction, Class<T> clazz) throws RaException {
      Result<T> result = invokeRa(payload, soapAction, config.getProperty("endpoint.etee.certra", "$uddi{uddi:ehealth-fgov-be:business:certra:v1}"), clazz);
      if (!result.hasStatusError() && !((RaResponseType)result.getResult()).getErrors().isEmpty()) {
         Iterator i$ = ((RaResponseType)result.getResult()).getErrors().iterator();
         if (i$.hasNext()) {
            ErrorType error = (ErrorType)i$.next();
            if ("CERT_NOT_YET_DELIVERED".equals(error.getCode())) {
               return new Result((new DateTime()).plusSeconds(10));
            }

            if (StringUtils.startsWith(error.getCode(), "AUTH_CERT_NOT_YET_VALID")) {
               String between = StringUtils.substringBetween(error.getCode(), "[", "]");
               if (between == null) {
                  between = "";
               }

               Object[] vars = between.split(",");
               String msg = MessageFormat.format(error.getMessage(), vars);
               LOG.info(msg);
               return new Result(DateTimeFormat.forPattern("dd/MM/YYYY HH:mm").parseDateTime(vars[0].toString()).plusMinutes(1));
            }

            return new Result("(CertRA) " + error.getMessage(), (Throwable)null);
         }
      }

      return result;
   }

   public static <T extends be.fgov.ehealth.etkra.protocol.v1.RaResponseType> Result<T> invokeEtkRa(Object payload, String soapAction, Class<T> clazz) throws TechnicalConnectorException {
      Result<T> result = invokeRa(payload, soapAction, config.getProperty("endpoint.etee.etkra", "$uddi{uddi:ehealth-fgov-be:business:etkra:v1}"), clazz);
      if (!result.hasStatusError() && !((be.fgov.ehealth.etkra.protocol.v1.RaResponseType)result.getResult()).getErrors().isEmpty()) {
         Iterator i$ = ((be.fgov.ehealth.etkra.protocol.v1.RaResponseType)result.getResult()).getErrors().iterator();
         if (i$.hasNext()) {
            be.fgov.ehealth.etkra.protocol.v1.ErrorType error = (be.fgov.ehealth.etkra.protocol.v1.ErrorType)i$.next();
            return new Result("(EtkRA) " + error.getMessage(), (Throwable)null);
         }
      }

      return result;
   }

   private static <T> Result<T> invokeRa(Object payload, String soapAction, String endpoint, Class<T> clazz) {
      try {
         GenericRequest request = new GenericRequest();
         request.setPayload(payload);
         request.setEndpoint(endpoint);
         request.setSoapAction(soapAction);
         request.setDefaultHandlerChain();
         return new Result(ServiceFactory.getGenericWsSender().send(request).asObject(clazz));
      } catch (SOAPException var5) {
         return new Result("", var5);
      } catch (TechnicalConnectorException var6) {
         return new Result("", var6);
      }
   }
}
