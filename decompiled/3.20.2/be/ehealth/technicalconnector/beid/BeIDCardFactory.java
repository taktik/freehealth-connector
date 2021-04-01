package be.ehealth.technicalconnector.beid;

import be.ehealth.technicalconnector.beid.impl.CommonsEidAdaptor;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fedict.commons.eid.client.BeIDCard;

public final class BeIDCardFactory {
   public static final String PROP_BEID_ADAPTOR = "be.ehealth.technicalconnector.beid.beidcardadaptor.class";
   private static ConfigurableFactoryHelper<BeIDCardAdaptor> helper = new ConfigurableFactoryHelper("be.ehealth.technicalconnector.beid.beidcardadaptor.class", CommonsEidAdaptor.class.getName());

   private BeIDCardFactory() {
   }

   public static BeIDCard getBeIDCard() throws TechnicalConnectorException {
      return ((BeIDCardAdaptor)helper.getImplementation()).getBeIDCard();
   }
}
