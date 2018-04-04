package org.taktik.connector.business.mycarenetcommons.builders.impl;

import org.taktik.connector.business.mycarenetdomaincommons.builders.impl.AbstractCommonBuilderImpl;
import org.taktik.connector.business.mycarenetdomaincommons.builders.util.CareProviderBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareProvider;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.SessionUtil;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public class IndependantProfessionalCommonBuilderImpl extends AbstractCommonBuilderImpl {
   private static final String MYCARENETCOMMONS_CAREPROVIDER_QUALITY_PROPERTY_KEY = "mycarenet.careprovider.quality";
   private ConfigValidator config;

   public IndependantProfessionalCommonBuilderImpl() throws TechnicalConnectorException {
      List<String> expectedProps = new ArrayList();
      expectedProps.add("mycarenet.careprovider.quality");
      this.config = ConfigFactory.getConfigValidator(expectedProps);
   }

   protected CareProvider createCareProviderForOrigin() throws TechnicalConnectorException {
      String quality = this.config.getProperty("mycarenet.careprovider.quality");
      CareProviderBuilder careProviderBuilder = new CareProviderBuilder(quality, SessionUtil.getNihii11());
      careProviderBuilder.addPhysicalPersonWithSsin(SessionUtil.getFullName(), SessionUtil.getNiss());
      return careProviderBuilder.build();
   }
}
