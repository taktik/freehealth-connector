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
   name = "RecipePrescriberPortType"
)
@XmlSeeAlso({be.fgov.ehealth.commons.core.v1.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v1.ObjectFactory.class, be.fgov.ehealth.recipe.core.v1.ObjectFactory.class, ObjectFactory.class})
@SOAPBinding(
   parameterStyle = ParameterStyle.BARE
)
public interface RecipePrescriberPortType {
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:ListFeedbacks"
   )
   @WebResult(
      name = "ListFeedbacksResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   ListFeedbacksResponse listFeedbacks(@WebParam(partName = "body",name = "ListFeedbacksRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") ListFeedbacksRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:GetPrescriptionForPrescriber"
   )
   @WebResult(
      name = "GetPrescriptionForPrescriberResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(@WebParam(partName = "body",name = "GetPrescriptionForPrescriberRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") GetPrescriptionForPrescriberRequest var1);

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
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:CreatePrescription"
   )
   @WebResult(
      name = "CreatePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   CreatePrescriptionResponse createPrescription(@WebParam(partName = "body",name = "CreatePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") CreatePrescriptionRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:SendNotification"
   )
   @WebResult(
      name = "SendNotificationResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   SendNotificationResponse sendNotification(@WebParam(partName = "body",name = "SendNotificationRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") SendNotificationRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:RevokePrescription"
   )
   @WebResult(
      name = "RevokePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   RevokePrescriptionResponse revokePrescription(@WebParam(partName = "body",name = "RevokePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") RevokePrescriptionRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:ListOpenPrescriptions"
   )
   @WebResult(
      name = "ListOpenPrescriptionsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   ListOpenPrescriptionsResponse listOpenPrescriptions(@WebParam(partName = "body",name = "ListOpenPrescriptionsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") ListOpenPrescriptionsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:UpdateFeedbackFlag"
   )
   @WebResult(
      name = "UpdateFeedbackFlagResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   UpdateFeedbackFlagResponse updateFeedbackFlag(@WebParam(partName = "body",name = "UpdateFeedbackFlagRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") UpdateFeedbackFlagRequest var1);
}
