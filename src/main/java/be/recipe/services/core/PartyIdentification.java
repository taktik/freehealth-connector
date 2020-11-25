package be.recipe.services.core;

import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "partyIdentification",
   propOrder = {"prescriberId", "executorId", "patientId", "type", "quality"}
)
@XmlSeeAlso({ListOpenRidsParam.class, UpdateFeedbackFlagParam.class, PutVisionParam.class, ListRidsHistoryParam.class, CreatePrescriptionParam.class, GetPrescriptionStatusParam.class, RevokePrescriptionParam.class, GetPrescriptionForPrescriberParam.class, ListFeedbacksParam.class, ValidationPropertiesParam.class, SendNotificationParam.class})
public class PartyIdentification {
   protected String prescriberId;
   protected String executorId;
   protected String patientId;
   protected String type;
   protected String quality;

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String value) {
      this.prescriberId = value;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String value) {
      this.executorId = value;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PartyIdentification)) return false;
      PartyIdentification that = (PartyIdentification) o;
      return Objects.equals(prescriberId, that.prescriberId) &&
              Objects.equals(executorId, that.executorId) &&
              Objects.equals(patientId, that.patientId) &&
              Objects.equals(type, that.type) &&
              Objects.equals(quality, that.quality);
   }

   @Override
   public int hashCode() {
      return Objects.hash(prescriberId, executorId, patientId, type, quality);
   }
}
