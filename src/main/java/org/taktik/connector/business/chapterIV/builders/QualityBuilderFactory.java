package org.taktik.connector.business.chapterIV.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

/** @deprecated */
@Deprecated
public final class QualityBuilderFactory {
   private static final String PROP_PROVIDER = "chapterIV.qualitybuilder.class";
   private static final String DEFAULT_PROVIDER = "org.taktik.connector.business.chapterIV.builders.impl.QualityBuilderPersPhysician";
   private static ConfigurableFactoryHelper<QualityBuilder> helper = new ConfigurableFactoryHelper("chapterIV.qualitybuilder.class", "org.taktik.connector.business.chapterIV.builders.impl.QualityBuilderPersPhysician", QualityBuilder.class);

   public static QualityBuilder getQualityBuilder() throws TechnicalConnectorException {
      return (QualityBuilder)helper.getImplementation();
   }
}
