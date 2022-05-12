package be.ehealth.business.common.util;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public final class EidUtils {
   private EidUtils() {
   }

   public static Patient readFromEidCard() throws TechnicalConnectorException {
      return readFromEidCard("patient");
   }

   public static Patient readFromEidCard(String scope) throws TechnicalConnectorException {
      BeIDInfo beIdInfo = BeIDFactory.getBeIDInfo(scope, false);
      Identity identity = beIdInfo.getIdentity();
      Patient patient = new Patient();
      patient.setEidCardNumber(identity.getCardNumber());
      patient.setLastName(identity.getName());
      patient.setMiddleName(identity.getMiddleName());
      patient.setFirstName(identity.getFirstName());
      patient.setInss(identity.getNationalNumber());
      return patient;
   }
}
