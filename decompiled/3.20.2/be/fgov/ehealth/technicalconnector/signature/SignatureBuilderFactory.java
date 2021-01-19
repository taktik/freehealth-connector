package be.fgov.ehealth.technicalconnector.signature;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.signature.impl.CmsSignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.XmlSignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.impl.XadesBesSpecification;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.impl.XadesTSpecification;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SignatureBuilderFactory {
   private static final String PROP_SIGNATUREBUILDER_CLASS = "signature.signaturebuilder.class";
   private static final Logger LOG = LoggerFactory.getLogger(SignatureBuilderFactory.class);
   private static Map<AdvancedElectronicSignatureEnumeration, SignatureBuilder> builders = new EnumMap(AdvancedElectronicSignatureEnumeration.class);

   private SignatureBuilderFactory() {
      throw new UnsupportedOperationException();
   }

   private static void processBuilder(SignatureBuilder builder) {
      if (!builders.containsKey(builder.getSupportedAES())) {
         LOG.debug("Adding builder for AES: " + builder.getSupportedAES() + " [" + builder.getClass() + "]");
         builders.put(builder.getSupportedAES(), builder);
      } else {
         LOG.warn("Ignoring builder with AES : " + builder.getSupportedAES() + " [" + builder.getClass() + "]. Reason: AES already instantiated. [" + ((SignatureBuilder)builders.get(builder.getSupportedAES())).getClass() + "]");
      }

   }

   public static SignatureBuilder getSignatureBuilder(AdvancedElectronicSignatureEnumeration instance) throws TechnicalConnectorException {
      if (builders.containsKey(instance)) {
         return (SignatureBuilder)builders.get(instance);
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"Unsupported AdvancedElectricignature: " + instance});
      }
   }

   static {
      ConfigurableFactoryHelper configHelper = new ConfigurableFactoryHelper("signature.signaturebuilder.class", (String)null);

      try {
         List<SignatureBuilder> builderList = configHelper.getImplementations();
         if (builderList.isEmpty()) {
            LOG.warn("No Signature Builders configured reason: no valid config. Instantiating with default builders");
            processBuilder(new XmlSignatureBuilder(AdvancedElectronicSignatureEnumeration.XML, new XadesSpecification[0]));
            processBuilder(new XmlSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES, new XadesSpecification[]{new be.fgov.ehealth.technicalconnector.signature.impl.xades.impl.XadesSpecification(), new XadesBesSpecification()}));
            processBuilder(new XmlSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T, new XadesSpecification[]{new be.fgov.ehealth.technicalconnector.signature.impl.xades.impl.XadesSpecification(), new XadesTSpecification()}));
            processBuilder(new CmsSignatureBuilder(AdvancedElectronicSignatureEnumeration.CAdES));
         }

         Iterator i$ = builderList.iterator();

         while(i$.hasNext()) {
            SignatureBuilder builder = (SignatureBuilder)i$.next();
            processBuilder(builder);
         }
      } catch (TechnicalConnectorException var4) {
         LOG.warn("No Signature Builders configured reason:" + var4.getMessage(), var4);
      }

   }
}
