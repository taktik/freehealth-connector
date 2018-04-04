package org.taktik.connector.business.recipeprojects.common.services.tipsystem;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.ehealth.apb.gfddpp.services.tipsystem.CheckAliveRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.ObjectFactory;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedCheckAliveResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;
import javax.xml.bind.JAXBElement;
import org.apache.log4j.Logger;

public class TipSystemServiceImpl implements TipSystemService {
   private static final Logger LOG = Logger.getLogger(TipSystemServiceImpl.class);
   public static final String ENDPOINT_NAME = "endpoint.tipsystem";
   private static TipSystemService tipSystemService;

   public static TipSystemService getInstance() {
      if (tipSystemService == null) {
         tipSystemService = new TipSystemServiceImpl();
      }

      return tipSystemService;
   }

   public SimpleResponseType registerData(SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<SealedMessageRequestType> jb = objectFactory.createRegisterDataRequest(sealedMessageRequestType);
      return (SimpleResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public SimpleResponseType updateData(SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<SealedMessageRequestType> jb = objectFactory.createUpdateDataRequest(sealedMessageRequestType);
      return (SimpleResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public SimpleResponseType deleteData(SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<SealedMessageRequestType> jb = objectFactory.createDeleteDataRequest(sealedMessageRequestType);
      return (SimpleResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public RoutedSealedResponseType getProductFilter(RoutedSealedRequestType routedSealedRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<RoutedSealedRequestType> jb = objectFactory.createGetProductFilterRequest(routedSealedRequestType);
      return (RoutedSealedResponseType)GenericWebserviceCaller.callGenericWebservice(jb, RoutedSealedRequestType.class, RoutedSealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public RoutedSealedResponseType getSystemServices(RoutedSealedRequestType routedSealedRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<RoutedSealedRequestType> jb = objectFactory.createGetSystemServicesRequest(routedSealedRequestType);
      return (RoutedSealedResponseType)GenericWebserviceCaller.callGenericWebservice(jb, RoutedSealedRequestType.class, RoutedSealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public RoutedSealedResponseType retrieveStatusMessages(RoutedSealedRequestType routedSealedRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<RoutedSealedRequestType> jb = objectFactory.createRetrieveStatusMessagesRequest(routedSealedRequestType);
      return (RoutedSealedResponseType)GenericWebserviceCaller.callGenericWebservice(jb, RoutedSealedRequestType.class, RoutedSealedResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public SimpleResponseType sendStatusMessages(SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<SealedMessageRequestType> jb = objectFactory.createSendStatusMessagesRequest(sealedMessageRequestType);
      return (SimpleResponseType)GenericWebserviceCaller.callGenericWebservice(jb, SealedMessageRequestType.class, SimpleResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }

   public RoutedCheckAliveResponseType checkAliveTIP(CheckAliveRequestType checkAliveRequestType) throws IntegrationModuleException {
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<CheckAliveRequestType> jb = objectFactory.createCheckAliveTIPRequest(checkAliveRequestType);
      return (RoutedCheckAliveResponseType)GenericWebserviceCaller.callGenericWebservice(jb, CheckAliveRequestType.class, RoutedCheckAliveResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.tipsystem"), this.getClass().getName(), true, false, false, false);
   }
}
