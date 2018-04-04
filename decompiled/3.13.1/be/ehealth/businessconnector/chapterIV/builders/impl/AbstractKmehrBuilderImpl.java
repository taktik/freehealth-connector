package be.ehealth.businessconnector.chapterIV.builders.impl;

import be.ehealth.businessconnector.chapterIV.builders.KmehrBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYvalues;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType;
import be.fgov.ehealth.standards.kmehr.schema.v1.SenderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.StandardType;
import java.util.List;
import org.joda.time.DateTime;

public abstract class AbstractKmehrBuilderImpl implements KmehrBuilder {
   private static final String VERSION_KMEHR = "20121001";
   private static final String CD_STANDARD = "CD-STANDARD";

   abstract String getKmerhIDPrefix() throws TechnicalConnectorException;

   public abstract List<HcpartyType> generateHcPartiesForTransactionAuthor() throws TechnicalConnectorException;

   public abstract List<HcpartyType> generateHcPartiesForSender() throws TechnicalConnectorException;

   public HeaderType generateHeader(String generatedKmehrIdSuffix) throws TechnicalConnectorException {
      HeaderType header = new HeaderType();
      header.getIds().add(this.generateKmehrId(generatedKmehrIdSuffix));
      header.setStandard(this.generateStandardVersion());
      header.getRecipients().add(this.generateChapterIVRecipient());
      header.setSender(this.generateSender());
      header.setTime(new DateTime());
      header.setDate(new DateTime());
      return header;
   }

   private IDKMEHR generateKmehrId(String generatedKmehrIdSuffix) throws TechnicalConnectorException {
      IDKMEHR idKmehr = new IDKMEHR();
      idKmehr.setS(IDKMEHRschemes.ID_KMEHR);
      idKmehr.setSV("1.0");
      idKmehr.setValue(this.getKmerhIDPrefix() + "." + generatedKmehrIdSuffix);
      return idKmehr;
   }

   private StandardType generateStandardVersion() {
      StandardType standard = new StandardType();
      CDSTANDARD cdStandard = new CDSTANDARD();
      cdStandard.setS("CD-STANDARD");
      cdStandard.setSV("1.3");
      cdStandard.setValue("20121001");
      standard.setCd(cdStandard);
      return standard;
   }

   private SenderType generateSender() throws TechnicalConnectorException {
      SenderType sender = new SenderType();
      sender.getHcparties().addAll(this.generateHcPartiesForSender());
      return sender;
   }

   private RecipientType generateChapterIVRecipient() throws TechnicalConnectorException {
      RecipientType recipientType = new RecipientType();
      HcpartyType hcPartyType = new HcpartyType();
      CDHCPARTY cdHcParty = new CDHCPARTY();
      cdHcParty.setS(CDHCPARTYschemes.CD_HCPARTY);
      cdHcParty.setSV("1.1");
      cdHcParty.setValue(CDHCPARTYvalues.APPLICATION.value());
      hcPartyType.setName("MyCarenet");
      hcPartyType.getCds().add(cdHcParty);
      recipientType.getHcparties().add(hcPartyType);
      return recipientType;
   }

   public AuthorType generateAuthor() throws TechnicalConnectorException {
      AuthorType authorType = new AuthorType();
      authorType.getHcparties().addAll(this.generateHcPartiesForTransactionAuthor());
      return authorType;
   }
}
