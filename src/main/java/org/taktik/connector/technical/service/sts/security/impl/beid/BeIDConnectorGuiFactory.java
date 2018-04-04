package org.taktik.connector.technical.service.sts.security.impl.beid;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class BeIDConnectorGuiFactory {
   private static final String PROP_BEIDCONNECTORGUI_CLASS = "beidcardgui.class";
   private static final String DEFAULT_BEIDCONNECTORGUI_CLASS = "org.taktik.connector.technical.service.sts.security.impl.beid.impl.BeIDConnectorGuiSwing";
   private static ConfigurableFactoryHelper<BeIDConnectorGui> helperFactory = new ConfigurableFactoryHelper("beidcardgui.class", "org.taktik.connector.technical.service.sts.security.impl.beid.impl.BeIDConnectorGuiSwing");

   public static BeIDConnectorGui getInstance() throws TechnicalConnectorException {
      return (BeIDConnectorGui)helperFactory.getImplementation();
   }
}
