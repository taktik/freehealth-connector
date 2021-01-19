package be.ehealth.businessconnector.wsconsent.builders.impl;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.wsconsent.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorExceptionValues;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;
import be.fgov.ehealth.hubservices.core.v2.ConsentType;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RequestType;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestObjectBuilderImpl implements RequestObjectBuilder {
   public static final String WSCONSENT_MAXROWS = "wsconsent.maxrows";
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);

   public RequestObjectBuilderImpl() throws WsConsentBusinessConnectorException, TechnicalConnectorException, InstantiationException {
   }

   public PutPatientConsentRequest createPutRequest(AuthorWithPatientAndPersonType author, ConsentType consent) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      if (author != null && consent != null) {
         PutPatientConsentRequest req = new PutPatientConsentRequest();
         req.setConsent(consent);
         RequestType request = this.createRequestType(author);
         req.setRequest(request);
         return req;
      } else {
         LOG.error("author and Consent type are required to create a PutPatientConsentRequest");
         throw new WsConsentBusinessConnectorException(WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"author and consent type  are required to create a PutPatientConsentRequest"});
      }
   }

   public GetPatientConsentRequest createGetRequest(AuthorWithPatientAndPersonType author, SelectGetPatientConsentType consent) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      if (author != null && consent != null) {
         GetPatientConsentRequest request = new GetPatientConsentRequest();
         RequestType req = this.createRequestType(author);
         String maxRows = ConfigFactory.getConfigValidator().getProperty("wsconsent.maxrows");
         if (maxRows != null) {
            req.setMaxrows(new BigDecimal(maxRows));
         }

         request.setRequest(req);
         request.setSelect(consent);
         return request;
      } else {
         LOG.error("author and consent type are required to create a GetPatientConsentRequest");
         throw new WsConsentBusinessConnectorException(WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"author and consent type are required to create a GetPatientConsentRequest"});
      }
   }

   public GetPatientConsentStatusRequest createGetStatusRequest(AuthorWithPatientAndPersonType author, SelectGetPatientConsentType consent) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      if (author != null && consent != null) {
         GetPatientConsentStatusRequest request = new GetPatientConsentStatusRequest();
         RequestType req = this.createRequestType(author);
         String maxRows = ConfigFactory.getConfigValidator().getProperty("wsconsent.maxrows");
         if (maxRows != null) {
            req.setMaxrows(new BigDecimal(maxRows));
         }

         request.setRequest(req);
         request.setSelect(consent);
         return request;
      } else {
         LOG.error("author and consent type are required to create a GetPatientConsentStatusRequest");
         throw new WsConsentBusinessConnectorException(WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"author and consent type are required to create a GetPatientConsentStatusRequest"});
      }
   }

   public RevokePatientConsentRequest createRevokeRequest(AuthorWithPatientAndPersonType author, ConsentType consent) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      if (author != null && consent != null) {
         RevokePatientConsentRequest request = new RevokePatientConsentRequest();
         request.setConsent(consent);
         RequestType req = this.createRequestType(author);
         request.setRequest(req);
         return request;
      } else {
         LOG.error("author and Consent type are required to create a RevokePatientConsentRequest");
         throw new WsConsentBusinessConnectorException(WsConsentBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"author and consent type  are required to create a RevokePatientConsentRequest"});
      }
   }

   private RequestType createRequestType(AuthorWithPatientAndPersonType author) throws TechnicalConnectorException {
      RequestType request = new RequestType();
      IDKMEHR idKmehr = new IDKMEHR();
      idKmehr.setS(IDKMEHRschemes.ID_KMEHR);
      idKmehr.setSV("1.0");
      String firstHcPartyIdOfAuthor = this.getFirstHcPartyIdFromAuthor(author);
      idKmehr.setValue(this.createKmehrID(firstHcPartyIdOfAuthor));
      request.setId(idKmehr);
      request.setAuthor(author);
      request.setDate(new DateTime());
      request.setTime(new DateTime());
      return request;
   }

   private String getFirstHcPartyIdFromAuthor(AuthorWithPatientAndPersonType author) {
      return ((IDHCPARTY)((HcpartyType)author.getHcparties().get(0)).getIds().get(0)).getValue();
   }

   public String createKmehrID(String firstHcPartyIdOfAuthor) throws TechnicalConnectorException {
      return firstHcPartyIdOfAuthor + "." + HcPartyUtil.createKmehrIdSuffix();
   }
}
