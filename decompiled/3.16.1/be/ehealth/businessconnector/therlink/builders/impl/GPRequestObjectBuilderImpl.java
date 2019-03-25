package be.ehealth.businessconnector.therlink.builders.impl;

import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.util.ConfigReader;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public final class GPRequestObjectBuilderImpl extends AbstractRequestObjectBuilderImpl {
   private static final String PROP_APPLIC_NAME = "therlink.application.name";
   private static final String PROP_APPLIC_ID = "therlink.application.id";

   public GPRequestObjectBuilderImpl() throws TherLinkBusinessConnectorException, TechnicalConnectorException, InstantiationException {
   }

   public List<HcParty> getAuthorHcParties() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      HcParty applic = this.createApplicationHcParty();
      HcParty hcp = ConfigReader.getCareProvider();
      List<HcParty> list = new ArrayList();
      list.add(applic);
      list.add(hcp);
      return list;
   }

   private HcParty createApplicationHcParty() {
      HcParty applic = new HcParty();
      applic.setName(getConfig().getProperty("therlink.application.name"));
      applic.setType("application");
      applic.setApplicationID(getConfig().getProperty("therlink.application.id"));
      return applic;
   }
}
