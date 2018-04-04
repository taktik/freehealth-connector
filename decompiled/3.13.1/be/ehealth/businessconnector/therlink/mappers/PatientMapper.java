package be.ehealth.businessconnector.therlink.mappers;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public final class PatientMapper {
   public static Patient mapPatient(Patient businessPatient) throws TechnicalConnectorException {
      Patient patient = new Patient();
      patient.setEidCardNumber(businessPatient.getEidCardNumber());
      patient.setLastName(businessPatient.getLastName());
      patient.setFirstName(businessPatient.getFirstName());
      patient.setInss(businessPatient.getInss());
      patient.setEidCardNumber(businessPatient.getEidCardNumber());
      patient.setIsiCardNumber(businessPatient.getIsiCardNumber());
      return patient;
   }
}
