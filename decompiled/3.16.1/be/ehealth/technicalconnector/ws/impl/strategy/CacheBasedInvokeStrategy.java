package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.AbstractSOAPHandler;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.utils.CacheHelper;
import javax.xml.transform.dom.DOMSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheBasedInvokeStrategy implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(CacheBasedInvokeStrategy.class);
   private CacheInformation information;

   public CacheBasedInvokeStrategy(CacheInformation information) {
      this.information = information;
   }

   public boolean invoke(InvokeStrategyContext ctx) {
      try {
         GenericRequest genericRequest = ctx.getRequest();
         GenericResponse response = CacheHelper.get(new DOMSource(genericRequest.getPayload()), this.information);
         if (response != null) {
            ctx.setException((TechnicalConnectorException)null);
            ctx.setResponse(response);
            AbstractSOAPHandler.dumpMessage(response.getSOAPMessage(), "IN", LOG);
            return true;
         }
      } catch (Exception var4) {
         ctx.setException(new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var4, new Object[0]));
         LOG.debug("Unable to obtain response from cache", var4);
      }

      LOG.debug("No entry found in cache");
      return false;
   }
}
