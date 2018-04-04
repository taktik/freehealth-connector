package be.fgov.ehealth.commons.protocol.v1;

import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v1.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v1.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v1.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType"
)
@XmlSeeAlso({UploadFileRequestType.class, CreateFeedbackRequest.class, SendNotificationRequest.class, RevokePrescriptionRequest.class, UpdateFeedbackFlagRequest.class, MarkAsUnDeliveredRequest.class, GetPrescriptionForPatientRequest.class, RevokePatientPrescriptionRequest.class, ListOpenPrescriptionsRequest.class, AliveCheckRequest.class, RevokePrescriptionForExecutorRequest.class, GetPrescriptionForExecutorRequest.class, CreatePrescriptionRequest.class, ListPatientPrescriptionsRequest.class, UpdatePatientFeedbackFlagRequest.class, MarkAsArchivedRequest.class, GetPrescriptionForPrescriberRequest.class, ListFeedbacksRequest.class, MarkAsDeliveredRequest.class, ListNotificationsRequest.class})
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
