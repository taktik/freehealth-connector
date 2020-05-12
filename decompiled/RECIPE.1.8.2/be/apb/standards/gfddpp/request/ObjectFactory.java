package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public GetDataRequestParameters createGetDataRequestParameters() {
      return new GetDataRequestParameters();
   }

   public PatientType createPatientType() {
      return new PatientType();
   }

   public RequestorType createRequestorType() {
      return new RequestorType();
   }

   public DataSpecificParametersGetData createDataSpecificParametersGetData() {
      return new DataSpecificParametersGetData();
   }

   public DataSpecificParametersGetDataTypes createDataSpecificParametersGetDataTypes() {
      return new DataSpecificParametersGetDataTypes();
   }

   public DataSpecificParametersPharmacyDetails createDataSpecificParametersPharmacyDetails() {
      return new DataSpecificParametersPharmacyDetails();
   }

   public DataSpecificParametersGetStatus createDataSpecificParametersGetStatus() {
      return new DataSpecificParametersGetStatus();
   }

   public DataSpecificParametersMedicationSchemeTimestamps createDataSpecificParametersMedicationSchemeTimestamps() {
      return new DataSpecificParametersMedicationSchemeTimestamps();
   }

   public DataSpecificParametersMedicationSchemeEntries createDataSpecificParametersMedicationSchemeEntries() {
      return new DataSpecificParametersMedicationSchemeEntries();
   }

   public Motivation createMotivation() {
      return new Motivation();
   }
}
