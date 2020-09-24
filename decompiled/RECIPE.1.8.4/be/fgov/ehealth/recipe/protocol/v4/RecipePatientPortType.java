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
   name = "RecipePatientPortType"
)
@XmlSeeAlso({be.fgov.ehealth.commons.protocol.v2.ObjectFactory.class, be.fgov.ehealth.recipe.core.v4.ObjectFactory.class, be.fgov.ehealth.commons.core.v1.ObjectFactory.class, be.fgov.ehealth.commons.core.v2.ObjectFactory.class, org.w3._2000._09.xmldsig.ObjectFactory.class, be.fgov.ehealth.commons.protocol.v1.ObjectFactory.class, ObjectFactory.class, org.w3._2001._04.xmlenc.ObjectFactory.class, be.fgov.ehealth.insurability.protocol.v2.ObjectFactory.class, oasis.names.tc.saml._2_0.assertion.ObjectFactory.class, be.fgov.ehealth.insurability.core.v2.ObjectFactory.class})
@SOAPBinding(
   parameterStyle = ParameterStyle.BARE
)
public interface RecipePatientPortType {
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
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription"
   )
   @WebResult(
      name = "RevokePrescriptionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   RevokePrescriptionResponse revokePrescription(@WebParam(partName = "body",name = "RevokePrescriptionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") RevokePrescriptionRequest var1);

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
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPatient"
   )
   @WebResult(
      name = "PutVisionForPatientResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   PutVisionForPatientResponse putVisionForPatient(@WebParam(partName = "body",name = "PutVisionForPatientRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") PutVisionForPatientRequest var1);

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
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus"
   )
   @WebResult(
      name = "GetPrescriptionStatusResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetPrescriptionStatusResponse getPrescriptionStatus(@WebParam(partName = "body",name = "GetPrescriptionStatusRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetPrescriptionStatusRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:createRelation"
   )
   @WebResult(
      name = "CreateRelationResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   CreateRelationResponse createRelation(@WebParam(partName = "body",name = "CreateRelationRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") CreateRelationRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:listOpenRids"
   )
   @WebResult(
      name = "ListOpenRidsResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   ListOpenRidsResponse listOpenRids(@WebParam(partName = "body",name = "ListOpenRidsRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") ListOpenRidsRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:getVision"
   )
   @WebResult(
      name = "GetVisionResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   GetVisionResponse getVision(@WebParam(partName = "body",name = "GetVisionRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") GetVisionRequest var1);

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
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:putFeedbackFlag"
   )
   @WebResult(
      name = "PutFeedbackFlagResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   PutFeedbackFlagResponse putFeedbackFlag(@WebParam(partName = "body",name = "PutFeedbackFlagRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") PutFeedbackFlagRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:revokeRelation"
   )
   @WebResult(
      name = "RevokeRelationResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   RevokeRelationResponse revokeRelation(@WebParam(partName = "body",name = "RevokeRelationRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") RevokeRelationRequest var1);

   @WebMethod(
      action = "urn:be:fgov:ehealth:recipe:protocol:v4:createReservation"
   )
   @WebResult(
      name = "CreateReservationResponse",
      targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4",
      partName = "body"
   )
   CreateReservationResponse createReservation(@WebParam(partName = "body",name = "CreateReservationRequest",targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4") CreateReservationRequest var1);
}
