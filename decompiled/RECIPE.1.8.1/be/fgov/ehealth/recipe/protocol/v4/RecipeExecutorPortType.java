package be.fgov.ehealth.recipe.protocol.v4;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
   targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
   name = "RecipeExecutorPortType"
)
@XmlSeeAlso({be.fgov.ehealth.recipe.core.v4.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v2.ObjectFactory.class, be.fgov.ehealth.commons.core.v1.ObjectFactory.class, be.fgov.ehealth.commons.core.v2.ObjectFactory.class, org.w3._2000._09.xmldsig.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v1.ObjectFactory.class, ObjectFactory.class, org.w3._2001._04.xmlenc.ObjectFactory.class, be.fgov.ehealth.insurability.protocol.v2.ObjectFactory.class, oasis.names.tc.saml._2_0.assertion.ObjectFactory.class, be.fgov.ehealth.insurability.core.v2.ObjectFactory.class})
@SOAPBinding(
   parameterStyle = ParameterStyle.BARE
)
public interface RecipeExecutorPortType {
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription"
   )
   @WebResult(
      name = "RevokePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   RevokePrescriptionResponse revokePrescription(@WebParam(partName = "body",name = "RevokePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") RevokePrescriptionRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listOpenPrescriptions"
   )
   @WebResult(
      name = "ListOpenPrescriptionsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListOpenPrescriptionsResponse listOpenPrescriptions(@WebParam(partName = "body",name = "ListOpenPrescriptionsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListOpenPrescriptionsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:markAsDelivered"
   )
   @WebResult(
      name = "MarkAsDeliveredResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   MarkAsDeliveredResponse markAsDelivered(@WebParam(partName = "body",name = "MarkAsDeliveredRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") MarkAsDeliveredRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:markAsUnDelivered"
   )
   @WebResult(
      name = "MarkAsUnDeliveredResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   MarkAsUnDeliveredResponse markAsUnDelivered(@WebParam(partName = "body",name = "MarkAsUnDeliveredRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") MarkAsUnDeliveredRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listRelations"
   )
   @WebResult(
      name = "ListRelationsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListRelationsResponse listRelations(@WebParam(partName = "body",name = "ListRelationsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListRelationsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:putRidsInProcess"
   )
   @WebResult(
      name = "PutRidsInProcessResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   PutRidsInProcessResponse putRidsInProcess(@WebParam(partName = "body",name = "PutRidsInProcessRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") PutRidsInProcessRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:createFeedback"
   )
   @WebResult(
      name = "CreateFeedbackResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   CreateFeedbackResponse createFeedback(@WebParam(partName = "body",name = "CreateFeedbackRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") CreateFeedbackRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:markAsArchived"
   )
   @WebResult(
      name = "MarkAsArchivedResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   MarkAsArchivedResponse markAsArchived(@WebParam(partName = "body",name = "MarkAsArchivedRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") MarkAsArchivedRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus"
   )
   @WebResult(
      name = "GetPrescriptionStatusResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetPrescriptionStatusResponse getPrescriptionStatus(@WebParam(partName = "body",name = "GetPrescriptionStatusRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetPrescriptionStatusRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listNotifications"
   )
   @WebResult(
      name = "ListNotificationsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListNotificationsResponse listNotifications(@WebParam(partName = "body",name = "ListNotificationsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListNotificationsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionForExecutor"
   )
   @WebResult(
      name = "GetPrescriptionForExecutorResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetPrescriptionForExecutorResponse getPrescriptionForExecutor(@WebParam(partName = "body",name = "GetPrescriptionForExecutorRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetPrescriptionForExecutorRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listRidsHistory"
   )
   @WebResult(
      name = "ListRidsHistoryResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListRidsHistoryResponse listRidsHistory(@WebParam(partName = "body",name = "ListRidsHistoryRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListRidsHistoryRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listReservations"
   )
   @WebResult(
      name = "ListReservationsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListReservationsResponse listReservations(@WebParam(partName = "body",name = "ListReservationsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListReservationsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listRidsInProcess"
   )
   @WebResult(
      name = "ListRidsInProcessResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListRidsInProcessResponse listRidsInProcess(@WebParam(partName = "body",name = "ListRidsInProcessRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListRidsInProcessRequest var1);
}
