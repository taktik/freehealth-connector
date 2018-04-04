package org.taktik.connector.technical.beid;

import org.taktik.connector.technical.beid.impl.CommonsEidAdaptor;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import be.fedict.commons.eid.client.BeIDCard;

public final class BeIDCardFactory {
   public static final String PROP_BEID_ADAPTOR = "org.taktik.connector.technical.beid.beidcardadaptor.class";
   private static ConfigurableFactoryHelper<BeIDCardAdaptor> helper = new ConfigurableFactoryHelper("org.taktik.connector.technical.beid.beidcardadaptor.class", CommonsEidAdaptor.class.getName());

   public static BeIDCard getBeIDCard() throws TechnicalConnectorException {
      return ((BeIDCardAdaptor)helper.getImplementation()).getBeIDCard();
   }
}
