package be.fgov.ehealth.commons.protocol.v1;

import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v1.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v1.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v1.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationResponse;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"status"}
)
@XmlSeeAlso({UploadFileResponseType.class, ListPatientPrescriptionsResponse.class, CreatePrescriptionResponse.class, GetPrescriptionForExecutorResponse.class, RevokePrescriptionResponse.class, GetPrescriptionForPrescriberResponse.class, ListFeedbacksResponse.class, AliveCheckResponse.class, ListOpenPrescriptionsResponse.class, ListNotificationsResponse.class, CreateFeedbackResponse.class, RevokePrescriptionForExecutorResponse.class, SendNotificationResponse.class, MarkAsDeliveredResponse.class, RevokePatientPrescriptionResponse.class, MarkAsArchivedResponse.class, MarkAsUnDeliveredResponse.class, GetPrescriptionForPatientResponse.class, UpdatePatientFeedbackFlagResponse.class, UpdateFeedbackFlagResponse.class})
public class ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      required = true
   )
   protected StatusType status;
   @XmlAttribute(
      name = "Id"
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
}
