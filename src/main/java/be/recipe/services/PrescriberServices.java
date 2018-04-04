package be.recipe.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(
   targetNamespace = "http://services.recipe.be",
   name = "PrescriberServices"
)
@XmlSeeAlso({ObjectFactory.class})
public interface PrescriberServices {
   @RequestWrapper(
      localName = "updateFeedbackFlag",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.UpdateFeedbackFlag"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "updateFeedbackFlagResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.UpdateFeedbackFlagResponse"
   )
   void updateFeedbackFlag(@WebParam(name = "UpdateFeedbackFlagParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @WebResult(
      name = "GetPrescriptionForPrescriberResultSealed",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "getPrescriptionForPrescriber",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.GetPrescriptionForPrescriber"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "getPrescriptionForPrescriberResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.GetPrescriptionForPrescriberResponse"
   )
   byte[] getPrescriptionForPrescriber(@WebParam(name = "GetPrescriptionForPrescriberParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @WebResult(
      name = "PingResult",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "ping",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.Ping"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "pingResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.PingResponse"
   )
   String ping();

   @RequestWrapper(
      localName = "sendNotification",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.SendNotification"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "sendNotificationResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.SendNotificationResponse"
   )
   void sendNotification(@WebParam(name = "SendNotificationParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @WebResult(
      name = "CreatePrescriptionResultSealed",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "createPrescription",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.CreatePrescription"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "createPrescriptionResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.CreatePrescriptionResponse"
   )
   byte[] createPrescription(@WebParam(name = "CreatePrescriptionParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PrescriptionType", targetNamespace = "") String var2, @WebParam(name = "DocumentId", targetNamespace = "") String var3, @WebParam(name = "KeyId", targetNamespace = "") String var4, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var5) throws RecipeException;

   @WebResult(
      name = "ListFeedbacksResultSealed",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "listFeedbacks",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.ListFeedbacks"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "listFeedbacksResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.ListFeedbacksResponse"
   )
   byte[] listFeedbacks(@WebParam(name = "ListFeedbacksParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @WebResult(
      name = "GetListOpenPrescriptionResultSealed",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "listOpenPrescription",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.ListOpenPrescription"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "listOpenPrescriptionResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.ListOpenPrescriptionResponse"
   )
   byte[] listOpenPrescription(@WebParam(name = "GetListOpenPrescriptionParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "uploadFile",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.UploadFile"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "uploadFileResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.UploadFileResponse"
   )
   void uploadFile(@WebParam(name = "UploadFileParam", targetNamespace = "") byte[] var1);

   @RequestWrapper(
      localName = "revokePrescription",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.RevokePrescription"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "revokePrescriptionResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.RevokePrescriptionResponse"
   )
   void revokePrescription(@WebParam(name = "RevokePrescriptionParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;
}
