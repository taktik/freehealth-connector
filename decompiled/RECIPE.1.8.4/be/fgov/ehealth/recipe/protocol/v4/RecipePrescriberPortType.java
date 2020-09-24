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
   name = "RecipePrescriberPortType"
)
@XmlSeeAlso({be.fgov.ehealth.recipe.core.v4.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v2.ObjectFactory.class, be.fgov.ehealth.commons.core.v1.ObjectFactory.class, be.fgov.ehealth.commons.core.v2.ObjectFactory.class, org.w3._2000._09.xmldsig.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v1.ObjectFactory.class, ObjectFactory.class, org.w3._2001._04.xmlenc.ObjectFactory.class, be.fgov.ehealth.insurability.protocol.v2.ObjectFactory.class, oasis.names.tc.saml._2_0.assertion.ObjectFactory.class, be.fgov.ehealth.insurability.core.v2.ObjectFactory.class})
@SOAPBinding(
   parameterStyle = ParameterStyle.BARE
)
public interface RecipePrescriberPortType {
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
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPrescriber"
   )
   @WebResult(
      name = "PutVisionForPrescriberResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   PutVisionForPrescriberResponse putVisionForPrescriber(@WebParam(partName = "body",name = "PutVisionForPrescriberRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") PutVisionForPrescriberRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:ListOpenRids"
   )
   @WebResult(
      name = "ListOpenRidsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListOpenRidsResponse listOpenRids(@WebParam(partName = "body",name = "ListOpenRidsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListOpenRidsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:sendNotification"
   )
   @WebResult(
      name = "SendNotificationResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   SendNotificationResponse sendNotification(@WebParam(partName = "body",name = "SendNotificationRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") SendNotificationRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:createPrescription"
   )
   @WebResult(
      name = "CreatePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   CreatePrescriptionResponse createPrescription(@WebParam(partName = "body",name = "CreatePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") CreatePrescriptionRequest var1);

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
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listFeedbacks"
   )
   @WebResult(
      name = "ListFeedbacksResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListFeedbacksResponse listFeedbacks(@WebParam(partName = "body",name = "ListFeedbacksRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListFeedbacksRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:putFeedbackFlag"
   )
   @WebResult(
      name = "PutFeedbackFlagResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   PutFeedbackFlagResponse putFeedbackFlag(@WebParam(partName = "body",name = "PutFeedbackFlagRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") PutFeedbackFlagRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getPrescription"
   )
   @WebResult(
      name = "GetPrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetPrescriptionResponse getPrescription(@WebParam(partName = "body",name = "GetPrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetPrescriptionRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getValidationProperties"
   )
   @WebResult(
      name = "GetValidationPropertiesResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetValidationPropertiesResponse getValidationProperties(@WebParam(partName = "body",name = "GetValidationPropertiesRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetValidationPropertiesRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus"
   )
   @WebResult(
      name = "GetPrescriptionStatusResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetPrescriptionStatusResponse getPrescriptionStatus(@WebParam(partName = "body",name = "GetPrescriptionStatusRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetPrescriptionStatusRequest var1);
}
