package org.taktik.connector.business.chapterIV.builders.impl;

import org.taktik.connector.business.kmehrcommons.HcPartyUtil;
import org.taktik.connector.business.chapterIV.builders.KmehrBuilder;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType;
import be.fgov.ehealth.standards.kmehr.schema.v1.SenderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.StandardType;
import java.util.List;

public class GenericKmehrBuilder extends AbstractKmehrBuilderImpl implements KmehrBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String TRANSACTION_AUTHOR = "chapterIV.transactionAuthor";
   private static final String SENDER = "chapterIV.sender";

   public List<HcpartyType> generateHcPartiesForTransactionAuthor() throws TechnicalConnectorException {
      return HcPartyUtil.createAuthorHcParties("chapterIV.transactionAuthor");
   }

   public List<HcpartyType> generateHcPartiesForSender() throws TechnicalConnectorException {
      return HcPartyUtil.createAuthorHcParties("chapterIV.sender");
   }

   String getKmerhIDPrefix() throws TechnicalConnectorException {
      return "";//TODO HcPartyUtil.retrieveMainAuthorId("chapterIV");
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(AuthorType.class);
      JaxbContextFactory.initJaxbContext(HcpartyType.class);
      JaxbContextFactory.initJaxbContext(HeaderType.class);
      JaxbContextFactory.initJaxbContext(RecipientType.class);
      JaxbContextFactory.initJaxbContext(SenderType.class);
      JaxbContextFactory.initJaxbContext(StandardType.class);
   }
}
