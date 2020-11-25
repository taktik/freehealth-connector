package be.recipe.services.core;

import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.RevokePrescriptionResult;
import be.recipe.services.prescriber.SendNotificationResult;
import be.recipe.services.prescriber.UpdateFeedbackFlagResult;
import be.recipe.services.prescriber.ValidationPropertiesResult;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"status"}
)
@XmlSeeAlso({ListRidsHistoryResult.class, GetPrescriptionForPrescriberResult.class, UpdateFeedbackFlagResult.class, RevokePrescriptionResult.class, ValidationPropertiesResult.class, ListOpenRidsResult.class, SendNotificationResult.class, PutVisionResult.class, GetPrescriptionStatusResult.class, ListFeedbacksResult.class, CreatePrescriptionResult.class})
public class ResponseType {
   @XmlElement(
      required = true
   )
   protected StatusType status;
   @XmlAttribute(
      name = "id"
   )
   protected String id;

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType value) {
      this.status = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ResponseType)) return false;
      ResponseType that = (ResponseType) o;
      return Objects.equals(status, that.status) &&
              Objects.equals(id, that.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(status, id);
   }
}
