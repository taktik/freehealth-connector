package be.ehealth.technicalconnector.beid.impl;

import be.ehealth.technicalconnector.beid.BeIDCardAdaptor;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.client.BeIDCards;
import be.fedict.commons.eid.client.CancelledException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonsEidAdaptor implements BeIDCardAdaptor {
   private static final Logger LOG = LoggerFactory.getLogger(CommonsEidAdaptor.class);

   public CommonsEidAdaptor() {
   }

   public BeIDCard getBeIDCard() throws TechnicalConnectorException {
      BeIDCards beIDCards = new BeIDCards(new CommonsEidLoggerWrapper());
      if (!beIDCards.hasBeIDCards()) {
         LOG.error("No beIDCard found");
         ConnectorIOUtils.closeQuietly((Object)beIDCards);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_EID_NULL, new Object[0]);
      } else {
         try {
            BeIDCard beIDCard = beIDCards.getOneBeIDCard();
            return beIDCard;
         } catch (CancelledException var4) {
            ConnectorIOUtils.closeQuietly((Object)beIDCards);
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BEID_ERROR, var4, new Object[]{var4.getMessage()});
         }
      }
   }

   private static class CommonsEidLoggerWrapper implements be.fedict.commons.eid.client.spi.Logger {
      private static final Logger LOG = LoggerFactory.getLogger(CommonsEidAdaptor.class);

      private CommonsEidLoggerWrapper() {
      }

      public void error(String message) {
         LOG.error(message);
      }

      public void error(String s, Throwable throwable) {
         LOG.error(s, throwable);
      }

      public void info(String s) {
         LOG.info(s);
      }

      public void debug(String message) {
         LOG.debug(message);
      }
   }
}
