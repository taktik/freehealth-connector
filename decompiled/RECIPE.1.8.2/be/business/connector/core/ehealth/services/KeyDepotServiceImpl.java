package be.business.connector.core.ehealth.services;

import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.ServiceFactory;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionTokenFactory;
import be.fgov.ehealth.etkdepot._1_0.protocol.ErrorType;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkRequest;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkResponse;
import be.fgov.ehealth.etkdepot._1_0.protocol.IdentifierType;
import be.fgov.ehealth.etkdepot._1_0.protocol.MatchingEtk;
import be.fgov.ehealth.etkdepot._1_0.protocol.SearchCriteriaType;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class KeyDepotServiceImpl implements KeyDepotService {
   private static final Logger LOG = Logger.getLogger(KeyDepotServiceImpl.class);
   private static KeyDepotService keyDepotService;

   private KeyDepotServiceImpl() {
   }

   public static KeyDepotService getInstance() {
      if (keyDepotService == null) {
         keyDepotService = new KeyDepotServiceImpl();
      }

      return keyDepotService;
   }

   public List<EncryptionToken> retrieveEtk(KgssIdentifierType identifierType, String identifierValue, String applicationName) throws IntegrationModuleException, GeneralSecurityException {
      GetEtkRequest request = new GetEtkRequest();
      SearchCriteriaType searchCriteria = new SearchCriteriaType();
      List<IdentifierType> listIdentifiers = new ArrayList();
      IdentifierType identifier = new IdentifierType();
      identifier.setApplicationID(applicationName);
      identifier.setType(identifierType.getName());
      identifier.setValue(identifierValue);
      listIdentifiers.add(identifier);
      searchCriteria.getIdentifiers().addAll(listIdentifiers);
      request.setSearchCriteria(searchCriteria);

      try {
         be.ehealth.technicalconnector.service.keydepot.KeyDepotService service = ServiceFactory.getKeyDepotService();
         GetEtkResponse response = service.getETK(request);
         List<EncryptionToken> encryptiontokens = new ArrayList();
         List<byte[]> etks = new ArrayList();
         if (response.getETK() == null) {
            List<ErrorType> errors = response.getErrors();

            for(int i = 0; i < errors.size(); ++i) {
               ErrorType error = (ErrorType)errors.get(i);
               if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("NO_MATCHING_ETK")) {
                  encryptiontokens = this.multiEtkResponse((List)encryptiontokens, identifierType, identifierValue, applicationName, etks);
                  break;
               }

               this.validateETKResponse(response, identifierType, identifierValue);
            }
         } else {
            this.validateETKResponse(response, identifierType, identifierValue);
            encryptiontokens = this.singleEtkResponse(response, (List)encryptiontokens, etks);
         }

         return (List)encryptiontokens;
      } catch (TechnicalConnectorException var15) {
         LOG.error("Error retrieving new key", var15);
         throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.new.key"), var15);
      }
   }

   private List<EncryptionToken> multiEtkResponse(List<EncryptionToken> encryptiontokens, KgssIdentifierType identifierType, String identifierValue, String application, List<byte[]> etks) throws IntegrationModuleException, GeneralSecurityException, TechnicalConnectorException {
      GetEtkResponse etkResponse = null;
      GetEtkRequest paramGetEtkRequest = this.createEtkRequest(identifierType, identifierValue, application, false);
      be.ehealth.technicalconnector.service.keydepot.KeyDepotService service = ServiceFactory.getKeyDepotService();
      etkResponse = service.getETK(paramGetEtkRequest);
      this.validateETKResponse(etkResponse, identifierType, identifierValue);
      List<MatchingEtk> matchingEtks = etkResponse.getMatchingEtks();

      for(int i = 0; i < matchingEtks.size(); ++i) {
         MatchingEtk matchingEtk = (MatchingEtk)matchingEtks.get(i);

         for(int j = 0; j < matchingEtk.getIdentifiers().size(); ++j) {
            IdentifierType iType = (IdentifierType)matchingEtk.getIdentifiers().get(j);
            String value = iType.getValue();
            String applicationid = iType.getApplicationID();
            KgssIdentifierType kgssType = KgssIdentifierType.lookup(iType.getType());
            if (kgssType != null && kgssType.getName().equals(identifierType.getName())) {
               GetEtkRequest etkRequest = this.createEtkRequest(kgssType, value, applicationid, true);
               GetEtkResponse resp = null;
               resp = service.getETK(etkRequest);
               this.validateETKResponse(resp, kgssType, value);
               encryptiontokens = this.singleEtkResponse(resp, encryptiontokens, etks);
            } else {
               LOG.debug("ETK - Not the correct identifier type: " + kgssType.getName() + " has to be " + identifierType.getName() + ". The value of this identifier is: " + value + " and the application id is: " + applicationid);
            }
         }
      }

      return encryptiontokens;
   }

   private List<EncryptionToken> singleEtkResponse(GetEtkResponse response, List<EncryptionToken> encryptiontokens, List<byte[]> etks) throws IntegrationModuleException, GeneralSecurityException {
      byte[] etk = response.getETK();
      if (etk == null) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.other"));
      } else {
         if (!etks.contains(etk)) {
            etks.add(etk);
            EncryptionToken encryptionToken = EncryptionTokenFactory.getInstance().create(etk);
            encryptiontokens.add(encryptionToken);
         }

         return encryptiontokens;
      }
   }

   private GetEtkRequest createEtkRequest(KgssIdentifierType identifierType, String identifierValue, String applicationid, boolean setapplicationid) {
      GetEtkRequest paramGetEtkRequest = new GetEtkRequest();
      SearchCriteriaType paramSearchCriteriaType = new SearchCriteriaType();
      IdentifierType it = new IdentifierType();
      it.setType(identifierType.getName());
      it.setValue(identifierValue);
      if (setapplicationid) {
         it.setApplicationID(applicationid);
      }

      paramSearchCriteriaType.getIdentifiers().add(it);
      paramGetEtkRequest.setSearchCriteria(paramSearchCriteriaType);
      return paramGetEtkRequest;
   }

   private void validateETKResponse(GetEtkResponse response, KgssIdentifierType identifierType, String identifierValue) throws IntegrationModuleException {
      List<ErrorType> errors = response.getErrors();

      for(int i = 0; i < errors.size(); ++i) {
         ErrorType error = (ErrorType)errors.get(i);
         if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("NO_MATCHING_ETK")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.notfound", new Object[]{identifierType.getName(), identifierValue}));
         }

         if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("INVALID_NIHII_NUMBER")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.invalid.nihii", new Object[]{identifierValue}));
         }

         if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("INVALID_CBE_NUMBER")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.invalid.cbe", new Object[]{identifierValue}));
         }

         if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("INVALID_SSIN_NUMBER")) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.invalid.ssin", new Object[]{identifierValue}));
         }
      }

   }
}
