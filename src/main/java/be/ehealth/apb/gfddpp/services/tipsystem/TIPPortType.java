package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
   name = "TIPPortType",
   targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1"
)
@SOAPBinding(
   parameterStyle = SOAPBinding.ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class})
public interface TIPPortType {
   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:RegisterData"
   )
   @WebResult(
      name = "RegisterDataResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SimpleResponseType registerData(@WebParam(name = "RegisterDataRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedMessageRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:UpdateData"
   )
   @WebResult(
      name = "UpdateDataResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SimpleResponseType updateData(@WebParam(name = "UpdateDataRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedMessageRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:DeleteData"
   )
   @WebResult(
      name = "DeleteDataResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SimpleResponseType deleteData(@WebParam(name = "DeleteDataRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedMessageRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:GetProductFilter"
   )
   @WebResult(
      name = "GetProductFilterResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   RoutedSealedResponseType getProductFilter(@WebParam(name = "GetProductFilterRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") RoutedSealedRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:GetSystemServices"
   )
   @WebResult(
      name = "GetSystemServicesResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   RoutedSealedResponseType getSystemServices(@WebParam(name = "GetSystemServicesRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") RoutedSealedRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:RetrieveStatusMessages"
   )
   @WebResult(
      name = "RetrieveStatusMessagesResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   RoutedSealedResponseType retrieveStatusMessages(@WebParam(name = "RetrieveStatusMessagesRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") RoutedSealedRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:SendStatusMessages"
   )
   @WebResult(
      name = "SendStatusMessagesResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SimpleResponseType sendStatusMessages(@WebParam(name = "SendStatusMessagesRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedMessageRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:CheckAliveTIP"
   )
   @WebResult(
      name = "CheckAliveTIPResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   RoutedCheckAliveResponseType checkAliveTIP(@WebParam(name = "CheckAliveTIPRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") CheckAliveRequestType var1) throws SystemError_Exception;
}
