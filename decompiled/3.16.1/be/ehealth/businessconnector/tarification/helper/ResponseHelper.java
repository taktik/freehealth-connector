package be.ehealth.businessconnector.tarification.helper;

import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResponseHelper implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseHelper.class);
   private static final String TARIFICATION_RESPONSE = "/ehealth-messageservices/XSD/messageservices_protocol-1_2.xsd";

   public ResponseHelper() {
      LOG.debug("creating ResponseHelper for ModuleBootstrapper");
   }

   public static void validateResponse(RetrieveTransactionResponse responseContent) throws TechnicalConnectorException {
      ValidatorHelper.validate((Object)responseContent, (String)"/ehealth-messageservices/XSD/messageservices_protocol-1_2.xsd");
   }

   public static RetrieveTransactionResponse toObject(byte[] responseContent) {
      MarshallerHelper<Object, Object> responseMarshaller = new MarshallerHelper(RetrieveTransactionResponse.class, RetrieveTransactionResponse.class);
      return (RetrieveTransactionResponse)responseMarshaller.toObject(responseContent);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(RetrieveTransactionResponse.class);
   }
}
