package be.ehealth.technicalconnector.service.sts.security.impl.beid;

import be.ehealth.technicalconnector.beid.BeIDCardFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.KeyStoreAdaptor;
import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.jca.BeIDKeyStoreParameter;
import java.security.KeyStore;
import java.security.KeyStoreException;

public class BeIDKeyStoreAdaptor implements KeyStoreAdaptor {
   public static final String PROP_AUTO_RECOVERY = "be.ehealth.technicalconnector.service.sts.security.impl.beid.autorecovery";
   public static final String PROP_LOGOFF = "be.ehealth.technicalconnector.service.sts.security.impl.beid.logoff";
   public static final String PROP_CARDREADERSTICKINESS = "be.ehealth.technicalconnector.service.sts.security.impl.beid.cardreaderstickiness";
   private KeyStore keyStore;
   private static ConfigValidator conf = ConfigFactory.getConfigValidator();

   private void init() throws KeyStoreException, TechnicalConnectorException {
      BeIDCard beIDCard = BeIDCardFactory.getBeIDCard();
      this.keyStore = KeyStore.getInstance("BeID");
      BeIDKeyStoreParameter keyStoreParameter = new BeIDKeyStoreParameter();
      keyStoreParameter.setBeIDCard(beIDCard);
      keyStoreParameter.setAutoRecovery(conf.getBooleanProperty("be.ehealth.technicalconnector.service.sts.security.impl.beid.autorecovery", true).booleanValue());
      keyStoreParameter.setLogoff(conf.getBooleanProperty("be.ehealth.technicalconnector.service.sts.security.impl.beid.logoff", false).booleanValue());
      keyStoreParameter.setCardReaderStickiness(conf.getBooleanProperty("be.ehealth.technicalconnector.service.sts.security.impl.beid.cardreaderstickiness", false).booleanValue());

      try {
         this.keyStore.load(keyStoreParameter);
      } catch (Exception var4) {
         throw new KeyStoreException(var4);
      }
   }

   public KeyStore getKeyStore() throws KeyStoreException, TechnicalConnectorException {
      if (this.keyStore == null) {
         this.init();
      }

      return this.keyStore;
   }
}
