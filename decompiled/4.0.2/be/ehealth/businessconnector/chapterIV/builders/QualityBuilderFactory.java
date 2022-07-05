package be.ehealth.businessconnector.chapterIV.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

/** @deprecated */
@Deprecated
public final class QualityBuilderFactory {
   private static final String PROP_PROVIDER = "chapterIV.qualitybuilder.class";
   private static final String DEFAULT_PROVIDER = "be.ehealth.businessconnector.chapterIV.builders.impl.QualityBuilderPersPhysician";
   private static ConfigurableFactoryHelper<QualityBuilder> helper = new ConfigurableFactoryHelper("chapterIV.qualitybuilder.class", "be.ehealth.businessconnector.chapterIV.builders.impl.QualityBuilderPersPhysician");

   private QualityBuilderFactory() {
   }

   public static QualityBuilder getQualityBuilder() throws TechnicalConnectorException {
      return (QualityBuilder)helper.getImplementation();
   }
}
