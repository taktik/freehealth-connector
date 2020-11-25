package be.business.connector.projects.common.services.pcdh;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.CheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.ObjectFactory;
import be.ehealth.apb.gfddpp.services.pcdh.ResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;
import javax.xml.bind.JAXBElement;
import org.apache.log4j.Logger;

public class PcdhServiceImpl implements PcdhService {
   private static final Logger LOG = Logger.getLogger(PcdhServiceImpl.class);
   public static final String ENDPOINT_NAME = "endpoint.pcdh";
   private static PcdhService pcdhService;

   private PcdhServiceImpl() {
   }

   public static PcdhService getInstance() {
      if (pcdhService == null) {
         pcdhService = new PcdhServiceImpl();
      }

      return pcdhService;
   }

   public SealedResponseType getDataTypes(SealedRequestType sealedRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactoryPcdh = new ObjectFactory();
      JAXBElement<SealedRequestType> jb = objectFactoryPcdh.createGetDataTypesRequest(sealedRequestType);
      return (SealedResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.pcdh"), this.getClass().getName(), true, true, false, false);
   }

   public SealedResponseType getData(SealedRequestType sealedRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactoryPcdh = new ObjectFactory();
      JAXBElement<SealedRequestType> jb = objectFactoryPcdh.createGetDataRequest(sealedRequestType);
      return (SealedResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.pcdh"), this.getClass().getName(), true, true, false, false);
   }

   public SealedResponseType getPharmacyDetails(SealedRequestType sealedRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactoryPcdh = new ObjectFactory();
      JAXBElement<SealedRequestType> jb = objectFactoryPcdh.createGetPharmacyDetailsRequest(sealedRequestType);
      return (SealedResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.pcdh"), this.getClass().getName(), true, true, false, false);
   }

   public ResponseType uploadPerformanceMetric(UploadPerformanceMetricRequestType uploadPerformanceMetricRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactoryPcdh = new ObjectFactory();
      JAXBElement<UploadPerformanceMetricRequestType> jb = objectFactoryPcdh.createUploadPerformanceMetricRequest(uploadPerformanceMetricRequestType);
      return (ResponseType)GenericWebserviceCaller.callGenericWebservice(jb, UploadPerformanceMetricRequestType.class, SealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.pcdh"), this.getClass().getName(), true, true, false, false);
   }

   public CheckAliveResponseType checkAlivePCDH(CheckAliveRequestType checkAliveRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactoryPcdh = new ObjectFactory();
      JAXBElement<CheckAliveRequestType> jb = objectFactoryPcdh.createCheckAlivePCDHRequest(checkAliveRequestType);
      return (CheckAliveResponseType)GenericWebserviceCaller.callGenericWebservice(jb, CheckAliveRequestType.class, CheckAliveResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.pcdh"), this.getClass().getName(), true, true, false, false);
   }
}
