package be.business.connector.projects.common.services.recipe;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v1.ObjectFactory;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileResponseType;
import javax.xml.bind.JAXBElement;
import org.apache.log4j.Logger;

public class RecipeTechnicalServiceImpl implements RecipeTechnicalService {
   private static final Logger LOG = Logger.getLogger(RecipeTechnicalServiceImpl.class);
   private static final String ENDPOINT_NAME = "endpoint.technical";
   private static RecipeTechnicalService recipeTechnicalService;

   private RecipeTechnicalServiceImpl() {
   }

   public static RecipeTechnicalService getInstance() {
      if (recipeTechnicalService == null) {
         recipeTechnicalService = new RecipeTechnicalServiceImpl();
      }

      return recipeTechnicalService;
   }

   public UploadFileResponseType uploadFilePatient(UploadFileRequestType uploadFileRequestType) throws IntegrationModuleException {
      LOG.info("Entered uploadFilePatient");
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<UploadFileRequestType> jb = objectFactory.createUploadFilePatientRequest(uploadFileRequestType);
      return (UploadFileResponseType)GenericWebserviceCaller.callGenericWebservice(jb, UploadFileRequestType.class, UploadFileResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.technical"), this.getClass().getName(), true, true, true, false);
   }

   public UploadFileResponseType uploadFilePrescriber(UploadFileRequestType uploadFileRequestType) throws IntegrationModuleException {
      LOG.info("Entered uploadFilePrescriber");
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<UploadFileRequestType> jb = objectFactory.createUploadFilePrescriberRequest(uploadFileRequestType);
      return (UploadFileResponseType)GenericWebserviceCaller.callGenericWebservice(jb, UploadFileRequestType.class, UploadFileResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.technical"), this.getClass().getName(), true, true, true, false);
   }

   public UploadFileResponseType uploadFileExecutor(UploadFileRequestType uploadFileRequestType) throws IntegrationModuleException {
      LOG.info("Entered uploadFileExecutor");
      ObjectFactory objectFactory = new ObjectFactory();
      JAXBElement<UploadFileRequestType> jb = objectFactory.createUploadFileExecutorRequest(uploadFileRequestType);
      return (UploadFileResponseType)GenericWebserviceCaller.callGenericWebservice(jb, UploadFileRequestType.class, UploadFileResponseType.class, EndpointResolver.getEndpointUrlString("endpoint.technical"), this.getClass().getName(), true, true, true, false);
   }
}
