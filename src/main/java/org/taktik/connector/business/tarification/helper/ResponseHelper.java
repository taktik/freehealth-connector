package org.taktik.connector.business.tarification.helper;

import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.ValidatorHelper;
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
