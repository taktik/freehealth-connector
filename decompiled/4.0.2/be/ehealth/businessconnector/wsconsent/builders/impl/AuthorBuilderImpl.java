package be.ehealth.businessconnector.wsconsent.builders.impl;

import be.ehealth.business.common.util.BeIDInfoUtil;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.wsconsent.builders.AuthorBuilder;
import be.ehealth.businessconnector.wsconsent.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;

public class AuthorBuilderImpl implements AuthorBuilder {
   public AuthorBuilderImpl() {
   }

   public AuthorWithPatientAndPersonType createAuthor() throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      AuthorWithPatientAndPersonType author = new AuthorWithPatientAndPersonType();
      author.getHcparties().addAll(HcPartyUtil.createAuthorHcParties("wsconsent"));
      return author;
   }

   public AuthorWithPatientAndPersonType readFromEidCard() throws TechnicalConnectorException {
      BeIDInfo beIdInfo = BeIDInfoUtil.getBeIDInfo("patient");
      AuthorWithPatientAndPersonType author = new AuthorWithPatientAndPersonType();
      HcpartyType hcParty = new HcpartyType();
      hcParty.getIds().add(HcPartyUtil.buildId("1.0", beIdInfo.getIdentity().getNationalNumber(), IDHCPARTYschemes.LOCAL, "application_ID"));
      hcParty.getCds().add(HcPartyUtil.buildCd("1.1", "application", CDHCPARTYschemes.CD_HCPARTY, (String)null));
      hcParty.setName("Patient software name");
      author.getHcparties().add(hcParty);
      author.setPatient(RequestObjectBuilderFactory.getPatientInfoBuilder().readFromEidCard());
      return author;
   }
}
