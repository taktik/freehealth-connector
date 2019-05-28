package be.ehealth.technicalconnector.service.sts.security.impl.beid;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class BeIDConnectorGuiFactory {
   private static final String PROP_BEIDCONNECTORGUI_CLASS = "beidcardgui.class";
   private static final String DEFAULT_BEIDCONNECTORGUI_CLASS = "be.ehealth.technicalconnector.service.sts.security.impl.beid.impl.BeIDConnectorGuiSwing";
   private static ConfigurableFactoryHelper<BeIDConnectorGui> helperFactory = new ConfigurableFactoryHelper("beidcardgui.class", "be.ehealth.technicalconnector.service.sts.security.impl.beid.impl.BeIDConnectorGuiSwing");

   public static BeIDConnectorGui getInstance() throws TechnicalConnectorException {
      return (BeIDConnectorGui)helperFactory.getImplementation();
   }
}
