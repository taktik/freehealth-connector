package be.ehealth.businessconnector.wsconsent.builders.impl;

import be.ehealth.business.common.util.BeIDInfoUtil;
import be.ehealth.businessconnector.wsconsent.builders.PatientInfoBuilder;
import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.PatientIdType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.ObjectFactory;

public class PatientInfoBuilderImpl implements PatientInfoBuilder {
   public PatientInfoBuilderImpl() {
   }

   public PatientIdType readFromEidCard() throws TechnicalConnectorException {
      BeIDInfo beIdInfo = BeIDInfoUtil.getBeIDInfo("patient");
      PatientIdType patient = new PatientIdType();
      patient.setFamilyname(beIdInfo.getIdentity().getName());
      patient.setFirstname(beIdInfo.getIdentity().getFirstName());
      ObjectFactory objectFactoryIdPatient = new ObjectFactory();
      IDPATIENT idPatient = objectFactoryIdPatient.createIDPATIENT();
      idPatient.setSV("1.0");
      idPatient.setS(IDPATIENTschemes.INSS);
      idPatient.setValue(beIdInfo.getIdentity().getNationalNumber());
      IDPATIENT idPatient2 = objectFactoryIdPatient.createIDPATIENT();
      idPatient2.setSV("1.0");
      idPatient2.setS(IDPATIENTschemes.EID_CARDNO);
      idPatient2.setValue(beIdInfo.getIdentity().getCardNumber());
      patient.getIds().add(idPatient);
      patient.getIds().add(idPatient2);
      return patient;
   }
}
