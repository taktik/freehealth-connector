package be.ehealth.technicalconnector.service.sts.security.impl.beid;

import be.ehealth.technicalconnector.service.sts.security.ProviderAdaptor;
import be.fedict.commons.eid.jca.BeIDProvider;
import java.security.Provider;

public class BeIDProviderAdaptor implements ProviderAdaptor {
   public Provider getProvider() {
      return new BeIDProvider();
   }
}
