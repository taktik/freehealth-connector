package be.ehealth.business.mycarenetdomaincommons.builders.impl;

import be.ehealth.business.mycarenetdomaincommons.builders.util.CareProviderBuilder;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.util.PropertyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.util.ArrayList;
import java.util.Map;

public class GenericCommonBuilderImpl extends AbstractCommonBuilderImpl {
   private static final String VALUE = ".value";
   private static final String QUALITY = ".quality";
   private static final String PHYSICALPERSON = ".physicalperson";
   private static final String CAREPROVIDER = ".careprovider";
   private ConfigValidator config;

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      if (parameterMap != null && !parameterMap.isEmpty() && parameterMap.containsKey("projectName")) {
         this.projectName = (String)parameterMap.get("projectName");
         ArrayList<String> expectedProperties = new ArrayList();
         String careProviderIdKey = "mycarenet." + PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet..") + ".careprovider" + ".nihii" + ".value";
         String careProviderQualityKey = "mycarenet." + PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet..") + ".careprovider" + ".nihii" + ".quality";
         expectedProperties.add(careProviderIdKey);
         expectedProperties.add(careProviderQualityKey);
         this.config = ConfigFactory.getConfigValidator(expectedProperties);
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"missing config parameters for initialize of CommonBuilder , check factory method call"});
      }
   }

   protected CareProvider createCareProviderForOrigin() throws NumberFormatException, TechnicalConnectorException {
      String projectNameToUse = PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet..");
      String careProviderIdKey = "mycarenet." + projectNameToUse + ".careprovider" + ".nihii" + ".value";
      String careProviderQualityKey = "mycarenet." + projectNameToUse + ".careprovider" + ".nihii" + ".quality";
      CareProviderBuilder builder = new CareProviderBuilder(this.config.getProperty(careProviderQualityKey), this.config.getProperty(careProviderIdKey));
      String physicalPersonRootKey = "mycarenet." + projectNameToUse + ".careprovider" + ".physicalperson";
      String organizationRootKey = "mycarenet." + projectNameToUse + ".careprovider" + ".organization";
      builder.addPhysicalPersonIdentification(this.createPerson(physicalPersonRootKey));
      builder.addOrganisationIdentification(this.createOrganization(organizationRootKey));
      return builder.build();
   }
}
