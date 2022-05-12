package be.ehealth.technicalconnector.service.keydepot.impl;

import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotService;
import be.ehealth.technicalconnector.service.ws.ServiceFactory;
import be.ehealth.technicalconnector.utils.CertificateParser;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkRequest;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkResponse;
import be.fgov.ehealth.etkdepot._1_0.protocol.IdentifierType;
import be.fgov.ehealth.etkdepot._1_0.protocol.MatchingEtk;
import be.fgov.ehealth.etkdepot._1_0.protocol.SearchCriteriaType;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyDepotServiceImpl implements KeyDepotService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final long serialVersionUID = 1L;
   private static final Logger LOG = LoggerFactory.getLogger(KeyDepotServiceImpl.class);

   public KeyDepotServiceImpl() {
   }

   private GetEtkResponse getETK(SearchCriteriaType searchCriteria) throws TechnicalConnectorException {
      GetEtkRequest request = new GetEtkRequest();
      request.setSearchCriteria(searchCriteria);
      return this.getETK(request);
   }

   public GetEtkResponse getETK(GetEtkRequest etkRequest) throws TechnicalConnectorException {
      GenericRequest request = ServiceFactory.getETKService();
      request.setPayload((Object)etkRequest);

      try {
         return (GetEtkResponse)be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(request).asObject(GetEtkResponse.class);
      } catch (SOAPException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var4.getMessage(), var4});
      }
   }

   private SearchCriteriaType generatedSearchCreteriaType(String identifierName, String identifierValue, String application) {
      SearchCriteriaType searchCriteria = new SearchCriteriaType();
      List<IdentifierType> listIdentifiers = new ArrayList();
      IdentifierType identifier = new IdentifierType();
      if (application != null) {
         identifier.setApplicationID(application);
      }

      identifier.setType(identifierName);
      identifier.setValue(identifierValue);
      listIdentifiers.add(identifier);
      searchCriteria.getIdentifiers().addAll(listIdentifiers);
      return searchCriteria;
   }

   public Set<EncryptionToken> getETKSet(be.ehealth.technicalconnector.utils.IdentifierType identifierType, String identifierValue, String applicationId) throws TechnicalConnectorException {
      Set<EncryptionToken> result = new HashSet();
      SearchCriteriaType searchCriteria = this.generatedSearchCreteriaType(identifierType.getType(48), identifierValue, applicationId);
      GetEtkResponse response = this.getETK(searchCriteria);
      validate(response);
      if (response.getETK() != null) {
         result.add(toEncryptionToken(response.getETK()));
      } else if (applicationId == null && !response.getMatchingEtks().isEmpty()) {
         Iterator var7 = response.getMatchingEtks().iterator();

         while(var7.hasNext()) {
            MatchingEtk matchEtk = (MatchingEtk)var7.next();
            result.addAll(this.getEtk(matchEtk, identifierType, identifierValue));
         }
      } else {
         unableToFindEtk(searchCriteria);
      }

      return result;
   }

   private Set<EncryptionToken> getEtk(MatchingEtk matchEtk, be.ehealth.technicalconnector.utils.IdentifierType identifier, String id) throws TechnicalConnectorException {
      Set<EncryptionToken> result = new HashSet();
      SearchCriteriaType searchType = new SearchCriteriaType();
      if (matchEtk.getIdentifiers() != null && matchEtk.getIdentifiers().size() != 0) {
         searchType.getIdentifiers().add(matchEtk.getIdentifiers().get(0));
         GetEtkResponse response = this.getETK(searchType);
         validate(response);
         EncryptionToken etk = toEncryptionToken(response.getETK());
         CertificateParser etkParser = new CertificateParser(etk.getAuthenticationCertificate());
         if (etkParser.getIdentifier().equals(identifier) && etkParser.getId().equalsIgnoreCase(id)) {
            result.add(etk);
         } else if (etkParser.getIdentifier().equals(be.ehealth.technicalconnector.utils.IdentifierType.SSIN) && identifier.equals(be.ehealth.technicalconnector.utils.IdentifierType.NIHII)) {
            LOG.debug("Request was based on NIHII number [{}] but SSIN recieved.", id);
            result.add(etk);
         } else {
            LOG.warn("Ignoring etk with SubjectX509Name [{}]", etk.getAuthenticationCertificate().getSubjectX500Principal().getName("RFC2253"));
         }

         return result;
      } else {
         LOG.warn("Empty IdentifierList of MatchingETK");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
      }
   }

   private static void validate(GetEtkResponse response) throws TechnicalConnectorException {
      if (!response.getStatus().getCode().equalsIgnoreCase("200")) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND + ": " + ((LocalisedString)response.getStatus().getMessages().get(0)).getValue());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
      }
   }

   private static EncryptionToken toEncryptionToken(byte[] etk) throws TechnicalConnectorException {
      try {
         return new EncryptionToken(etk);
      } catch (GeneralSecurityException var2) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage(), var2);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, var2, new Object[0]);
      }
   }

   private static void unableToFindEtk(SearchCriteriaType searchType) throws TechnicalConnectorException {
      StringBuilder sb = new StringBuilder();
      if (LOG.isErrorEnabled()) {
         Iterator var2 = searchType.getIdentifiers().iterator();

         while(var2.hasNext()) {
            IdentifierType identifier = (IdentifierType)var2.next();
            sb.append("SearchCriteria:  type=[").append(identifier.getType()).append("] , value=[").append(identifier.getValue()).append("], appId=[").append(identifier.getApplicationID()).append("]");
         }

         LOG.error("No ETK found for {}", sb.toString());
      }

      throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetEtkRequest.class);
      JaxbContextFactory.initJaxbContext(GetEtkResponse.class);
   }
}
