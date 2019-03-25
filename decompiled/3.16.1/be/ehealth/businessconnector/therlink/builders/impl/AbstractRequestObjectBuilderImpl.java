package be.ehealth.businessconnector.therlink.builders.impl;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.therlink.builders.CommonObjectBuilder;
import be.ehealth.businessconnector.therlink.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.therlink.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.requests.GetTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.HasTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.PutTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.RevokeTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.therlink.util.ConfigReader;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRequestObjectBuilderImpl implements RequestObjectBuilder {
   private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequestObjectBuilderImpl.class);
   private int maxRows;
   private static ConfigValidator config;

   public AbstractRequestObjectBuilderImpl() throws TherLinkBusinessConnectorException, TechnicalConnectorException, InstantiationException {
      this.initializeConfig();
      this.maxRows = config.getIntegerProperty("therlink.maxrows", 1000);
   }

   private void initializeConfig() {
      config = ConfigFactory.getConfigValidator();
   }

   /** @deprecated */
   @Deprecated
   public PutTherapeuticLinkRequest createPutTherapeuticLinkRequest(Patient patient, HcParty concernedHealthCareProfessionel, Date endDate, String therLinkType, String comment, Proof... proofs) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      return this.createPutTherapeuticLinkRequest(this.mapToDateTime(endDate), patient, concernedHealthCareProfessionel, therLinkType, comment, proofs);
   }

   private DateTime mapToDateTime(Date endDate) {
      return endDate != null ? new DateTime(endDate) : null;
   }

   public PutTherapeuticLinkRequest createPutTherapeuticLinkRequest(DateTime endDate, Patient patient, HcParty concernedHealthCareProfessionel, String therLinkType, String comment, Proof... proofs) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      if (patient != null && therLinkType != null) {
         CommonObjectBuilder commonBuilder = RequestObjectBuilderFactory.getCommonBuilder();
         Author createAuthor = commonBuilder.createAuthor(this.getAuthorHcParties());
         TherapeuticLink createTherapeuticLink = commonBuilder.createTherapeuticLink(new DateTime(), endDate, patient, HcPartyUtil.getAuthorKmehrQuality(), therLinkType, comment, this.getHcp(concernedHealthCareProfessionel));
         PutTherapeuticLinkRequest request = new PutTherapeuticLinkRequest(new DateTime(), commonBuilder.createKmehrID(), createAuthor, createTherapeuticLink, proofs);
         return request;
      } else {
         String msg = "Patient and Therapeutic link type are required to create a PutTherapeuticLinkRequest";
         LOGGER.error(msg);
         throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{msg});
      }
   }

   /** @deprecated */
   @Deprecated
   public RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest(Patient patient, HcParty hcp, Date startDate, Date endDate, String therLinkType, String commentAboutRevokal, Proof... proofs) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      return this.createRevokeTherapeuticLinkRequest(this.mapToDateTime(startDate), this.mapToDateTime(endDate), patient, hcp, therLinkType, commentAboutRevokal, proofs);
   }

   public RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest(DateTime startDate, DateTime endDate, Patient patient, HcParty hcp, String therLinkType, String commentAboutRevokal, Proof... proofs) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      if (patient != null && hcp != null && therLinkType != null) {
         CommonObjectBuilder commonBuilder = RequestObjectBuilderFactory.getCommonBuilder();
         Author createAuthor = commonBuilder.createAuthor(this.getAuthorHcParties());
         DateTime newDate = new DateTime();
         DateTime startDateNotNull = startDate == null ? newDate : startDate;
         TherapeuticLink createTherapeuticLink = commonBuilder.createTherapeuticLink(startDateNotNull, endDate, patient, HcPartyUtil.getAuthorKmehrQuality(), therLinkType, commentAboutRevokal, this.getHcp(hcp));
         RevokeTherapeuticLinkRequest request = new RevokeTherapeuticLinkRequest(newDate, commonBuilder.createKmehrID(), createAuthor, createTherapeuticLink, proofs);
         return request;
      } else {
         TherLinkBusinessConnectorException therLinkBusinessConnectorException = new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"Patient, HcParty and Therapeutic link type are required to create a RevokeTherapeutiCLinkType"});
         LOGGER.error(therLinkBusinessConnectorException.getMessage());
         throw therLinkBusinessConnectorException;
      }
   }

   public RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest(TherapeuticLink link) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      return this.createRevokeTherapeuticLinkRequestWithProof(link);
   }

   public RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequestWithProof(TherapeuticLink link, Proof... proofs) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      DateTime startDate = link.getStartDate() == null ? null : link.getStartDate().toDateTime(LocalTime.MIDNIGHT);
      DateTime endDate = link.getEndDate() == null ? null : link.getEndDate().toDateTime(LocalTime.MIDNIGHT);
      return this.createRevokeTherapeuticLinkRequest(startDate, endDate, link.getPatient(), link.getHcParty(), link.getType(), link.getComment(), proofs);
   }

   public GetTherapeuticLinkRequest createGetTherapeuticLinkRequest(TherapeuticLink query, int maxRowsToUse, Proof... prooves) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      if (query == null) {
         TherLinkBusinessConnectorException therLinkBusinessConnectorException = new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"query is required to create a GetTherapeuticLinkRequest"});
         LOGGER.error(therLinkBusinessConnectorException.getMessage());
         throw therLinkBusinessConnectorException;
      } else {
         CommonObjectBuilder commonBuilder = RequestObjectBuilderFactory.getCommonBuilder();
         DateTime date = query.getStartDate() == null ? null : query.getStartDate().toDateTime(LocalTime.MIDNIGHT);
         Author createAuthor = commonBuilder.createAuthor(this.getAuthorHcParties());
         GetTherapeuticLinkRequest request = new GetTherapeuticLinkRequest(date, commonBuilder.createKmehrID(), createAuthor, query, maxRowsToUse, prooves);
         this.validateMaxRowsValue(request);
         return request;
      }
   }

   public PutTherapeuticLinkRequest createPutTherapeuticLinkRequest(Patient patient, HcParty hcp, String therLinkType, Proof proof) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      return this.createPutTherapeuticLinkRequest((DateTime)null, (Patient)patient, (HcParty)hcp, therLinkType, (String)null, proof);
   }

   public GetTherapeuticLinkRequest createGetTherapeuticLinkRequest(TherapeuticLink query, Proof... prooves) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      int maxRowsToUse = this.maxRows <= 1000 ? this.maxRows : 1000;
      return this.createGetTherapeuticLinkRequest(query, maxRowsToUse, prooves);
   }

   public HasTherapeuticLinkRequest createHasTherapeuticLinkRequest(TherapeuticLink query) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      if (query == null) {
         TherLinkBusinessConnectorException therLinkBusinessConnectorException = new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"query is required to create a GetTherapeuticLinkRequest"});
         LOGGER.error(therLinkBusinessConnectorException.getMessage());
         throw therLinkBusinessConnectorException;
      } else {
         CommonObjectBuilder commonBuilder = RequestObjectBuilderFactory.getCommonBuilder();
         DateTime date = query.getStartDate() == null ? null : query.getStartDate().toDateTime(LocalTime.MIDNIGHT);
         Author createAuthor = commonBuilder.createAuthor(this.getAuthorHcParties());
         HasTherapeuticLinkRequest request = new HasTherapeuticLinkRequest(date, commonBuilder.createKmehrID(), createAuthor, query);
         return request;
      }
   }

   private HcParty getHcp(HcParty hcParty) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      HcParty hcp = hcParty;
      if (hcParty == null) {
         hcp = ConfigReader.getCareProvider();
      }

      return hcp;
   }

   public void validateMaxRowsValue(GetTherapeuticLinkRequest request) throws TherLinkBusinessConnectorException {
      if (request.getMaxRows() > 1000) {
         TherLinkBusinessConnectorException therLinkBusinessConnectorException = new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.MAXROWS_INCORRECT, new Object[]{1000});
         LOGGER.error(therLinkBusinessConnectorException.getMessage());
         throw therLinkBusinessConnectorException;
      }
   }

   public abstract List<HcParty> getAuthorHcParties() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   /** @deprecated */
   @Deprecated
   public String getEnduserHcpType() {
      return HcPartyUtil.getAuthorKmehrQuality();
   }

   public int getMaxRows() {
      return this.maxRows;
   }

   public static ConfigValidator getConfig() {
      return config;
   }

   public static void setConfig(ConfigValidator config) {
      config = config;
   }
}
