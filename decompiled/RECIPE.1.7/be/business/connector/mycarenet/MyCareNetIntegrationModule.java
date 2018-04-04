package be.business.connector.mycarenet;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.business.connector.mycarenet.domain.Insurability;

public interface MyCareNetIntegrationModule {
   String getInsurability(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13) throws IntegrationModuleException;

   Insurability getInsurabilityForPharmacist(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13) throws IntegrationModuleException;
}
