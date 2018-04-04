package be.ehealth.apb.gfddpp.services.pcdh;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
   name = "PCDHPortType",
   targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1"
)
@SOAPBinding(
   parameterStyle = SOAPBinding.ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class})
public interface PCDHPortType {
   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:GetData"
   )
   @WebResult(
      name = "GetDataResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SealedResponseType getData(@WebParam(name = "GetDataRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:GetDataTypes"
   )
   @WebResult(
      name = "GetDataTypesResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SealedResponseType getDataTypes(@WebParam(name = "GetDataTypesRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:GetPharmacyDetails"
   )
   @WebResult(
      name = "GetPharmacyDetailsResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   SealedResponseType getPharmacyDetails(@WebParam(name = "GetPharmacyDetailsRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") SealedRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:UploadPerformanceMetric"
   )
   @WebResult(
      name = "UploadPerformanceMetricResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   ResponseType uploadPerformanceMetric(@WebParam(name = "UploadPerformanceMetricRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") UploadPerformanceMetricRequestType var1) throws SystemError_Exception;

   @WebMethod(
      action = "urn:be:fgov:ehealth:gfddpp:protocol:v1:CheckAlivePCDH"
   )
   @WebResult(
      name = "CheckAlivePCDHResponse",
      targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      partName = "body"
   )
   CheckAliveResponseType checkAlivePCDH(@WebParam(name = "CheckAlivePCDHRequest", targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1", partName = "body") CheckAliveRequestType var1) throws SystemError_Exception;
}
