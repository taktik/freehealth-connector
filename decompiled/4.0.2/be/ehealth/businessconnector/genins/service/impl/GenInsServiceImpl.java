package be.ehealth.businessconnector.genins.service.impl;

import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.businessconnector.genins.service.GenInsService;
import be.ehealth.businessconnector.genins.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsFlatRequest;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsFlatResponse;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityRequest;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse;
import java.net.MalformedURLException;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenInsServiceImpl implements GenInsService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(GenInsServiceImpl.class);
   private static final String GENINSBEGINNING = "GeninsServiceImpl : ";

   public GenInsServiceImpl() {
   }

   public GetInsurabilityAsFlatResponse getInsurabilityAsFlat(SAMLToken token, GetInsurabilityAsXmlOrFlatRequestType request) throws GenInsBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      GetInsurabilityAsFlatResponse response = null;
      GetInsurabilityAsFlatRequest genericReq = new GetInsurabilityAsFlatRequest();
      this.dozer(request, genericReq);

      try {
         GenericRequest service = ServiceFactory.getGeninsPort(token);
         service.setPayload((Object)genericReq);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         response = (GetInsurabilityAsFlatResponse)xmlResponse.asObject(GetInsurabilityAsFlatResponse.class);
         return response;
      } catch (MalformedURLException var7) {
         LOG.error("GeninsServiceImpl : " + var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, var7, new Object[]{"genins " + var7.getMessage()});
      } catch (SOAPException var8) {
         LOG.error("GeninsServiceImpl : " + var8.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }

   public GetInsurabilityResponse getInsurability(SAMLToken token, GetInsurabilityAsXmlOrFlatRequestType requestType) throws GenInsBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      GetInsurabilityResponse response = null;
      GetInsurabilityRequest request = new GetInsurabilityRequest();
      this.dozer(requestType, request);

      try {
         GenericRequest service = ServiceFactory.getGeninsPort(token);
         service.setPayload((Object)request);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         response = (GetInsurabilityResponse)xmlResponse.asObject(GetInsurabilityResponse.class);
         return response;
      } catch (MalformedURLException var7) {
         LOG.error("GeninsServiceImpl : " + var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, var7, new Object[]{"genins " + var7.getMessage()});
      } catch (SOAPException var8) {
         LOG.error("GeninsServiceImpl : " + var8.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }

   private void dozer(GetInsurabilityAsXmlOrFlatRequestType source, GetInsurabilityAsXmlOrFlatRequestType destination) {
      destination.setCommonInput(source.getCommonInput());
      destination.setRecordCommonInput(source.getRecordCommonInput());
      destination.setRequest(source.getRequest());
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetInsurabilityAsFlatRequest.class);
      JaxbContextFactory.initJaxbContext(GetInsurabilityAsFlatResponse.class);
      JaxbContextFactory.initJaxbContext(GetInsurabilityAsXmlOrFlatRequestType.class);
      JaxbContextFactory.initJaxbContext(GetInsurabilityRequest.class);
      JaxbContextFactory.initJaxbContext(GetInsurabilityResponse.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
   }
}
