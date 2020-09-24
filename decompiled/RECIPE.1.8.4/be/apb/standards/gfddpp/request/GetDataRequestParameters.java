package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"patient", "requestor", "dataType", "version", "dataSpecificParametersGetData", "dataSpecificParametersGetDataTypes", "dataSpecificParametersPharmacyDetails", "dataSpecificParametersGetStatus", "dataSpecificParametersMedicationSchemeTimestamps", "dataSpecificParametersMedicationSchemeEntries"}
)
@XmlRootElement(
   name = "getDataRequestParameters"
)
public class GetDataRequestParameters {
   protected PatientType patient;
   @XmlElement(
      required = true
   )
   protected RequestorType requestor;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dataType;
   protected String version;
   protected DataSpecificParametersGetData dataSpecificParametersGetData;
   protected DataSpecificParametersGetDataTypes dataSpecificParametersGetDataTypes;
   protected DataSpecificParametersPharmacyDetails dataSpecificParametersPharmacyDetails;
   protected DataSpecificParametersGetStatus dataSpecificParametersGetStatus;
   protected DataSpecificParametersMedicationSchemeTimestamps dataSpecificParametersMedicationSchemeTimestamps;
   protected DataSpecificParametersMedicationSchemeEntries dataSpecificParametersMedicationSchemeEntries;

   public PatientType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientType value) {
      this.patient = value;
   }

   public RequestorType getRequestor() {
      return this.requestor;
   }

   public void setRequestor(RequestorType value) {
      this.requestor = value;
   }

   public String getDataType() {
      return this.dataType;
   }

   public void setDataType(String value) {
      this.dataType = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public DataSpecificParametersGetData getDataSpecificParametersGetData() {
      return this.dataSpecificParametersGetData;
   }

   public void setDataSpecificParametersGetData(DataSpecificParametersGetData value) {
      this.dataSpecificParametersGetData = value;
   }

   public DataSpecificParametersGetDataTypes getDataSpecificParametersGetDataTypes() {
      return this.dataSpecificParametersGetDataTypes;
   }

   public void setDataSpecificParametersGetDataTypes(DataSpecificParametersGetDataTypes value) {
      this.dataSpecificParametersGetDataTypes = value;
   }

   public DataSpecificParametersPharmacyDetails getDataSpecificParametersPharmacyDetails() {
      return this.dataSpecificParametersPharmacyDetails;
   }

   public void setDataSpecificParametersPharmacyDetails(DataSpecificParametersPharmacyDetails value) {
      this.dataSpecificParametersPharmacyDetails = value;
   }

   public DataSpecificParametersGetStatus getDataSpecificParametersGetStatus() {
      return this.dataSpecificParametersGetStatus;
   }

   public void setDataSpecificParametersGetStatus(DataSpecificParametersGetStatus value) {
      this.dataSpecificParametersGetStatus = value;
   }

   public DataSpecificParametersMedicationSchemeTimestamps getDataSpecificParametersMedicationSchemeTimestamps() {
      return this.dataSpecificParametersMedicationSchemeTimestamps;
   }

   public void setDataSpecificParametersMedicationSchemeTimestamps(DataSpecificParametersMedicationSchemeTimestamps value) {
      this.dataSpecificParametersMedicationSchemeTimestamps = value;
   }

   public DataSpecificParametersMedicationSchemeEntries getDataSpecificParametersMedicationSchemeEntries() {
      return this.dataSpecificParametersMedicationSchemeEntries;
   }

   public void setDataSpecificParametersMedicationSchemeEntries(DataSpecificParametersMedicationSchemeEntries value) {
      this.dataSpecificParametersMedicationSchemeEntries = value;
   }
}
