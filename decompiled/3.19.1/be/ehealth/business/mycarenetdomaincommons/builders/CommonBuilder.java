package be.ehealth.business.mycarenetdomaincommons.builders;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import org.joda.time.DateTime;

public interface CommonBuilder extends ConfigurableImplementation {
   String PROJECT_NAME_KEY = "projectName";

   Routing createRouting(Patient var1, DateTime var2);

   /** @deprecated */
   @Deprecated
   CommonInput createCommonInput(PackageInfo var1, boolean var2, String var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Origin createOrigin(PackageInfo var1) throws TechnicalConnectorException;

   CommonInput createCommonInput(McnPackageInfo var1, boolean var2, String var3) throws TechnicalConnectorException;

   Origin createOrigin(McnPackageInfo var1) throws TechnicalConnectorException;

   Routing createRoutingToMutuality(String var1, DateTime var2);
}
