package org.taktik.connector.business.chapterIV.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class KmehrBuilderFactory {
   private static final String PROP_PROVIDER = "chapterIV.kmehrbuilder.class";
   private static final String DEFAULT_PROVIDER = "org.taktik.connector.business.chapterIV.builders.impl.GenericKmehrBuilder";
   private static ConfigurableFactoryHelper<KmehrBuilder> helper = new ConfigurableFactoryHelper("chapterIV.kmehrbuilder.class", "org.taktik.connector.business.chapterIV.builders.impl.GenericKmehrBuilder");

   public static KmehrBuilder getKmehrBuilder() throws TechnicalConnectorException {
      return (KmehrBuilder)helper.getImplementation();
   }
}
