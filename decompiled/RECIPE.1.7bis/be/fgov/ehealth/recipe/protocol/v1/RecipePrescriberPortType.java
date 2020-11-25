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
@XmlSeeAlso({ObjectFactory.class, be.fgov.ehealth.commons.protocol.v1.ObjectFactory.class, be.fgov.ehealth.commons.core.v1.ObjectFactory.class, be.fgov.ehealth.recipe.core.v1.ObjectFactory.class})
@SOAPBinding(
   parameterStyle = ParameterStyle.BARE
)
public interface RecipePrescriberPortType {
   @WebResult(
      name = "GetPrescriptionForPrescriberResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:GetPrescriptionForPrescriber"
   )
   GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(@WebParam(partName = "body",name = "GetPrescriptionForPrescriberRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") GetPrescriptionForPrescriberRequest var1);

   @WebResult(
      name = "ListOpenPrescriptionsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:ListOpenPrescriptions"
   )
   ListOpenPrescriptionsResponse listOpenPrescriptions(@WebParam(partName = "body",name = "ListOpenPrescriptionsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") ListOpenPrescriptionsRequest var1);

   @WebResult(
      name = "ListFeedbacksResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:ListFeedbacks"
   )
   ListFeedbacksResponse listFeedbacks(@WebParam(partName = "body",name = "ListFeedbacksRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") ListFeedbacksRequest var1);

   @WebResult(
      name = "CreatePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:CreatePrescription"
   )
   CreatePrescriptionResponse createPrescription(@WebParam(partName = "body",name = "CreatePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") CreatePrescriptionRequest var1);

   @WebResult(
      name = "SendNotificationResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:SendNotification"
   )
   SendNotificationResponse sendNotification(@WebParam(partName = "body",name = "SendNotificationRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") SendNotificationRequest var1);

   @WebResult(
      name = "UpdateFeedbackFlagResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:UpdateFeedbackFlag"
   )
   UpdateFeedbackFlagResponse updateFeedbackFlag(@WebParam(partName = "body",name = "UpdateFeedbackFlagRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") UpdateFeedbackFlagRequest var1);

   @WebResult(
      name = "AliveCheckResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:AliveCheck"
   )
   AliveCheckResponse aliveCheck(@WebParam(partName = "body",name = "AliveCheckRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") AliveCheckRequest var1);

   @WebResult(
      name = "RevokePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      partName = "body"
   )
   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v1:RevokePrescription"
   )
   RevokePrescriptionResponse revokePrescription(@WebParam(partName = "body",name = "RevokePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1") RevokePrescriptionRequest var1);
}
