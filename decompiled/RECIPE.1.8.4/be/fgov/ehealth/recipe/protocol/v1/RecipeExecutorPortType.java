package be.fgov.ehealth.recipe.protocol.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
   targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
   name = "RecipeExecutorPortType"
)
@XmlSeeAlso({be.fgov.ehealth.commons.core.v1.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v1.ObjectFactory.class, be.fgov.ehealth.recipe.core.v1.ObjectFactory.class, ObjectFactory.class})
@SOAPBinding(
   parameterStyle = ParameterStyle.BARE
)
public interface RecipeExecutorPortType {
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:MarkAsArchived"
   )
   @WebResult(
      name = "MarkAsArchivedResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   MarkAsArchivedResponse markAsArchived(@WebParam(partName = "body",name = "MarkAsArchivedRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") MarkAsArchivedRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:CreateFeedback"
   )
   @WebResult(
      name = "CreateFeedbackResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   CreateFeedbackResponse createFeedback(@WebParam(partName = "body",name = "CreateFeedbackRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") CreateFeedbackRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:MarkAsUnDelivered"
   )
   @WebResult(
      name = "MarkAsUnDeliveredResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   MarkAsUnDeliveredResponse markAsUnDelivered(@WebParam(partName = "body",name = "MarkAsUnDeliveredRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") MarkAsUnDeliveredRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:AliveCheck"
   )
   @WebResult(
      name = "AliveCheckResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   AliveCheckResponse aliveCheck(@WebParam(partName = "body",name = "AliveCheckRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") AliveCheckRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:GetPrescriptionForExecutor"
   )
   @WebResult(
      name = "GetPrescriptionForExecutorResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   GetPrescriptionForExecutorResponse getPrescriptionForExecutor(@WebParam(partName = "body",name = "GetPrescriptionForExecutorRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") GetPrescriptionForExecutorRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:MarkAsDelivered"
   )
   @WebResult(
      name = "MarkAsDeliveredResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   MarkAsDeliveredResponse markAsDelivered(@WebParam(partName = "body",name = "MarkAsDeliveredRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") MarkAsDeliveredRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:RevokePrescriptionForExecutor"
   )
   @WebResult(
      name = "RevokePrescriptionForExecutorResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(@WebParam(partName = "body",name = "RevokePrescriptionForExecutorRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") RevokePrescriptionForExecutorRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:ListNotifications"
   )
   @WebResult(
      name = "ListNotificationsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   ListNotificationsResponse listNotifications(@WebParam(partName = "body",name = "ListNotificationsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") ListNotificationsRequest var1);
}
