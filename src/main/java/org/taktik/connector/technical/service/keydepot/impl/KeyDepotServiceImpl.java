package org.taktik.connector.technical.service.keydepot.impl;

import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.ws.ServiceFactory;
import org.taktik.connector.technical.utils.CertificateParser;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
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

   private GetEtkResponse getETK(SearchCriteriaType searchCriteria) throws TechnicalConnectorException {
      GetEtkRequest request = new GetEtkRequest();
      request.setSearchCriteria(searchCriteria);
      return this.getETK(request);
   }

   public GetEtkResponse getETK(GetEtkRequest etkRequest) throws TechnicalConnectorException {
      GenericRequest request = ServiceFactory.getETKService();
      request.setPayload(etkRequest);

      try {
         return org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(request).asObject(GetEtkResponse.class);
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e.getMessage(), e);
      }
   }

   private SearchCriteriaType generatedSearchCreteriaType(String identifierName, String identifierValue, String application) {
      SearchCriteriaType searchCriteria = new SearchCriteriaType();
      List<IdentifierType> listIdentifiers = new ArrayList<>();
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

   public Set<EncryptionToken> getETKSet(org.taktik.connector.technical.utils.IdentifierType identifierType, String identifierValue, String applicationId) throws TechnicalConnectorException {
      Set<EncryptionToken> result = new HashSet<>();
      SearchCriteriaType searchCriteria = this.generatedSearchCreteriaType(identifierType.getType(48), identifierValue, applicationId);
      GetEtkResponse response = this.getETK(searchCriteria);
      validate(response);
      if (response.getETK() != null) {
         result.add(toEncryptionToken(response.getETK()));
      } else if (applicationId == null && !response.getMatchingEtks().isEmpty()) {
         for (MatchingEtk matchEtk : response.getMatchingEtks()) {
            result.addAll(this.getEtk(matchEtk, identifierType, identifierValue));
         }
      } else {
         unableToFindEtk(searchCriteria);
      }

      return result;
   }

   private Set<EncryptionToken> getEtk(MatchingEtk matchEtk, org.taktik.connector.technical.utils.IdentifierType identifier, String id) throws TechnicalConnectorException {
      Set<EncryptionToken> result = new HashSet<>();
      SearchCriteriaType searchType = new SearchCriteriaType();
      if (matchEtk.getIdentifiers() != null && matchEtk.getIdentifiers().size() != 0) {
         searchType.getIdentifiers().add(matchEtk.getIdentifiers().get(0));
         GetEtkResponse response = this.getETK(searchType);
         validate(response);
         EncryptionToken etk = toEncryptionToken(response.getETK());
         CertificateParser etkParser = new CertificateParser(etk.getAuthenticationCertificate());
         if (etkParser.getIdentifier().equals(identifier) && etkParser.getId().equalsIgnoreCase(id)) {
            result.add(etk);
         } else if (etkParser.getIdentifier().equals(org.taktik.connector.technical.utils.IdentifierType.SSIN) && identifier.equals(org.taktik.connector.technical.utils.IdentifierType.NIHII)) {
            LOG.debug("Request was based on NIHII number [" + id + "] but SSIN recieved.");
            result.add(etk);
         } else {
            LOG.warn("Ignoring etk with SubjectX509Name [" + etk.getAuthenticationCertificate().getSubjectX500Principal().getName("RFC2253") + "]");
         }

         return result;
      } else {
         LOG.warn("Empty IdentifierList of MatchingETK");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND);
      }
   }

   private static void validate(GetEtkResponse response) throws TechnicalConnectorException {
      if (!response.getStatus().getCode().equalsIgnoreCase("200")) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND + ": " + response.getStatus().getMessages().get(0).getValue());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND);
      }
   }

   private static EncryptionToken toEncryptionToken(byte[] etk) throws TechnicalConnectorException {
      try {
         return new EncryptionToken(etk);
      } catch (GeneralSecurityException e) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage(), e);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, e);
      }
   }

   private static void unableToFindEtk(SearchCriteriaType searchType) throws TechnicalConnectorException {
      StringBuilder sb = new StringBuilder();

      for (IdentifierType identifier : searchType.getIdentifiers()) {
         sb.append("SearchCriteria:  type=[").append(identifier.getType()).append("] , value=[").append(identifier.getValue()).append("], appId=[").append(identifier.getApplicationID()).append("]");
      }

      LOG.error("No ETK found for " + sb.toString());
      throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetEtkRequest.class);
      JaxbContextFactory.initJaxbContext(GetEtkResponse.class);
   }
}
