package be.ehealth.businessconnector.chapterIV.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class KmehrBuilderFactory {
   private static final String PROP_PROVIDER = "chapterIV.kmehrbuilder.class";
   private static final String DEFAULT_PROVIDER = "be.ehealth.businessconnector.chapterIV.builders.impl.GenericKmehrBuilder";
   private static ConfigurableFactoryHelper<KmehrBuilder> helper = new ConfigurableFactoryHelper("chapterIV.kmehrbuilder.class", "be.ehealth.businessconnector.chapterIV.builders.impl.GenericKmehrBuilder");

   public static KmehrBuilder getKmehrBuilder() throws TechnicalConnectorException {
      return (KmehrBuilder)helper.getImplementation();
   }
}
