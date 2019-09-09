package org.taktik.connector.technical.ws.impl.strategy;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.AbstractSOAPHandler;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.utils.CacheHelper;
import javax.xml.transform.dom.DOMSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.technical.ws.impl.strategy.InvokeStrategy;

public class CacheBasedInvokeStrategy implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(CacheBasedInvokeStrategy.class);
   private CacheInformation information;

   public CacheBasedInvokeStrategy(CacheInformation information) {
      this.information = information;
   }

   public boolean invoke(org.taktik.connector.technical.ws.impl.strategy.InvokeStrategyContext ctx) {
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
