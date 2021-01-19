package be.ehealth.businessconnector.therlink.service.impl;

import be.ehealth.businessconnector.therlink.domain.jaxb.Therapeuticlink;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.service.ServiceFactory;
import be.ehealth.businessconnector.therlink.service.TherLinkService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse;
import java.net.MalformedURLException;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TherLinkServiceImpl implements TherLinkService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(TherLinkServiceImpl.class);

   public PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken token, PutTherapeuticLinkRequest request) throws TherLinkBusinessConnectorException, TechnicalConnectorException {
      PutTherapeuticLinkResponse response = null;

      try {
         GenericRequest service = ServiceFactory.getTherLinkPort(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:PutTherapeuticLink");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         response = (PutTherapeuticLinkResponse)xmlResponse.asObject(PutTherapeuticLinkResponse.class);
         return response;
      } catch (MalformedURLException var6) {
         LOG.error(var6.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, new Object[]{var6.getMessage(), var6});
      } catch (SOAPException var7) {
         LOG.error(var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken token, GetTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      GetTherapeuticLinkResponse response = null;

      try {
         GenericRequest service = ServiceFactory.getTherLinkPort(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:GetTherapeuticLink");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         response = (GetTherapeuticLinkResponse)xmlResponse.asObject(GetTherapeuticLinkResponse.class);
         return response;
      } catch (MalformedURLException var6) {
         LOG.error(var6.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, new Object[]{var6.getMessage(), var6});
      } catch (SOAPException var7) {
         LOG.error(var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken token, RevokeTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      RevokeTherapeuticLinkResponse response = null;

      try {
         GenericRequest service = ServiceFactory.getTherLinkPort(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:RevokeTherapeuticLink");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         response = (RevokeTherapeuticLinkResponse)xmlResponse.asObject(RevokeTherapeuticLinkResponse.class);
         return response;
      } catch (MalformedURLException var6) {
         LOG.error(var6.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, new Object[]{var6.getMessage(), var6});
      } catch (SOAPException var7) {
         LOG.error(var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public HasTherapeuticLinkResponse hasTherapeuticLink(SAMLToken token, HasTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      HasTherapeuticLinkResponse response = null;

      try {
         GenericRequest service = ServiceFactory.getTherLinkPort(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:HasTherapeuticLink");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         response = (HasTherapeuticLinkResponse)xmlResponse.asObject(HasTherapeuticLinkResponse.class);
         return response;
      } catch (MalformedURLException var6) {
         LOG.error(var6.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, new Object[]{var6.getMessage(), var6});
      } catch (SOAPException var7) {
         LOG.error(var7.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(HasTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(HasTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(Therapeuticlink.class);
   }
}
