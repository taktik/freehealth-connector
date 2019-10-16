package be.ehealth.businessconnector.chapterIV.builders.impl;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.chapterIV.builders.KmehrBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONACTORvalues;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYvalues;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public class KmehrBuilderPersPhysician extends AbstractKmehrBuilderImpl implements KmehrBuilder {
   private static final String SV_VERSION_1_0 = "1.0";

   String getKmerhIDPrefix() throws TechnicalConnectorException {
      return HcPartyUtil.retrieveMainAuthorId("chapterIV");
   }

   public List<HcpartyType> generateHcPartiesForTransactionAuthor() throws TechnicalConnectorException {
      HcpartyType hcpartytype = new HcpartyType();
      IDHCPARTY nihiiHcParty = new IDHCPARTY();
      IDHCPARTY nissHcParty = new IDHCPARTY();
      CDHCPARTY cdHcParty = new CDHCPARTY();
      cdHcParty.setS(CDHCPARTYschemes.CD_HCPARTY);
      cdHcParty.setSV("1.0");
      cdHcParty.setValue(CDHCPARTYvalues.PERSPHYSICIAN.value());
      nihiiHcParty.setS(IDHCPARTYschemes.ID_HCPARTY);
      nihiiHcParty.setSV("1.0");
      nihiiHcParty.setValue(IdentifierType.NIHII11.formatIdentifierValue(Long.parseLong(SessionUtil.getNihii11())));
      nissHcParty.setS(IDHCPARTYschemes.INSS);
      nissHcParty.setSV("1.0");
      nissHcParty.setValue(IdentifierType.SSIN.formatIdentifierValue(Long.parseLong(SessionUtil.getNiss())));
      hcpartytype.getIds().add(nihiiHcParty);
      hcpartytype.getIds().add(nissHcParty);
      hcpartytype.getCds().add(cdHcParty);
      hcpartytype.setFirstname(SessionUtil.getFirstname());
      hcpartytype.setFamilyname(SessionUtil.getLastname());
      ArrayList<HcpartyType> hcPartiesForAuthor = new ArrayList();
      hcPartiesForAuthor.add(hcpartytype);
      return hcPartiesForAuthor;
   }

   public List<HcpartyType> generateHcPartiesForSender() throws TechnicalConnectorException {
      return this.generateHcPartiesForTransactionAuthor();
   }

   private void generateEncryptionWithInss(IDHCPARTY idEncryptionHcParty, CDHCPARTY cdEncryptionHcParty) throws TechnicalConnectorException {
      idEncryptionHcParty.setS(IDHCPARTYschemes.ID_ENCRYPTION_ACTOR);
      idEncryptionHcParty.setSV("1.0");
      idEncryptionHcParty.setValue(IdentifierType.SSIN.formatIdentifierValue(Long.parseLong(SessionUtil.getNiss())));
      cdEncryptionHcParty.setS(CDHCPARTYschemes.CD_ENCRYPTION_ACTOR);
      cdEncryptionHcParty.setSV("1.0");
      cdEncryptionHcParty.setValue(CDENCRYPTIONACTORvalues.INSS.value());
   }
}
