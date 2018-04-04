package be.fgov.ehealth.technicalconnector.signature.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.signature.transformers.NippinOptionalDeflateTransformer;
import org.apache.xml.security.exceptions.AlgorithmAlreadyRegisteredException;
import org.apache.xml.security.transforms.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleRegisterTransformers implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleRegisterTransformers.class);
   private static boolean loaded;

   public void init(Configuration config) throws TechnicalConnectorException {
      if (!loaded) {
         try {
            Transform.register("urn:nippin:xml:sig:transform:optional-deflate", NippinOptionalDeflateTransformer.class);
            loaded = true;
         } catch (AlgorithmAlreadyRegisteredException var3) {
            LOG.error("Algorihm [{}] already loaded.", "urn:nippin:xml:sig:transform:optional-deflate", var3);
         }
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.warn("Unable to unregister the transformer with uri:urn:nippin:xml:sig:transform:optional-deflate");
   }
}
