package be.ehealth.technicalconnector.beid.impl;

import be.ehealth.technicalconnector.beid.BeIDCardAdaptor;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.impl.beid.BeIDConnectorGuiFactory;
import be.ehealth.technicalconnector.service.sts.security.impl.beid.BeIDLogger;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.client.BeIDCards;
import be.fedict.commons.eid.client.CancelledException;
import be.fedict.commons.eid.client.spi.BeIDCardUI;
import be.fedict.commons.eid.client.spi.BeIDCardsUI;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonsEidAdaptor implements BeIDCardAdaptor {
   public static final String PROP_BEID_CON_GUI = "beidcardsgui.class";
   private static ConfigurableFactoryHelper<BeIDCardsUI> helper = new ConfigurableFactoryHelper("beidcardsgui.class", (String)null);
   private static final Logger LOG = LoggerFactory.getLogger(CommonsEidAdaptor.class);

   public BeIDCard getBeIDCard() throws TechnicalConnectorException {
      BeIDCardUI beIDCardGui = getBeIDCardUI();
      BeIDCardsUI beIDCardsGui = getBeIDCardsUI();
      BeIDCards beIDCards = null;
      if (beIDCardsGui != null) {
         beIDCards = new BeIDCards(new BeIDLogger(), beIDCardsGui);
      } else {
         beIDCards = new BeIDCards(new BeIDLogger());
      }

      if (!beIDCards.hasBeIDCards()) {
         LOG.error("No beIDCard found");
         ConnectorIOUtils.closeQuietly((Object)beIDCards);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_EID_NULL, new Object[0]);
      } else {
         try {
            BeIDCard beIDCard = beIDCards.getOneBeIDCard();
            if (beIDCardGui != null) {
               beIDCard.setUI(beIDCardGui);
            }

            return beIDCard;
         } catch (CancelledException var6) {
            ConnectorIOUtils.closeQuietly((Object)beIDCards);
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BEID_ERROR, var6, new Object[]{var6.getMessage()});
         }
      }
   }

   private static BeIDCardUI getBeIDCardUI() {
      try {
         return BeIDConnectorGuiFactory.getInstance();
      } catch (TechnicalConnectorException var1) {
         LOG.error("TechnicalConnectorException while trying to create the BeIDCardUI " + var1.getMessage(), var1);
         return null;
      }
   }

   private static BeIDCardsUI getBeIDCardsUI() {
      try {
         return (BeIDCardsUI)helper.getImplementation(new HashMap(), false, true);
      } catch (TechnicalConnectorException var1) {
         LOG.error("TechnicalConnectorException while trying to create the BeIDCardsUI " + var1.getMessage(), var1);
         return null;
      }
   }
}
