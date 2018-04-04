package org.taktik.connector.business.mycarenetdomaincommons.builders.util;

import org.taktik.connector.business.mycarenetdomaincommons.domain.CareProvider;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Origin;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Party;
import org.taktik.connector.business.mycarenetdomaincommons.exception.ConnectorValidationException;
import org.taktik.connector.business.mycarenetdomaincommons.validator.CommonInputValidator;
import org.taktik.connector.technical.config.util.domain.PackageInfo;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;

public final class GeneralCommonInputBuilder {
   private CommonInput commonInput;
   private boolean validate = true;

   private GeneralCommonInputBuilder(boolean isTest, String inputReference) {
      this.commonInput = new CommonInput(isTest, new Origin((McnPackageInfo)null, (String)null, (Party)null), inputReference);
   }

   public static GeneralCommonInputBuilder create(boolean isTest, String inputReference) {
      return new GeneralCommonInputBuilder(isTest, inputReference);
   }

   GeneralCommonInputBuilder create(boolean isTest) throws TechnicalConnectorException {
      String inputReference = IdGeneratorFactory.getIdGenerator().generateId();
      return create(isTest, inputReference);
   }

   /** @deprecated */
   @Deprecated
   public GeneralCommonInputBuilder addPackageInfo(PackageInfo packageInfo) {
      this.commonInput.getOrigin().setPackageInfo(packageInfo);
      return this;
   }

   public GeneralCommonInputBuilder addMcnPackageInfo(McnPackageInfo packageInfo) {
      this.commonInput.getOrigin().setMcnPackageInfo(packageInfo);
      return this;
   }

   public GeneralCommonInputBuilder addPackageInfo(String name, String userName, String password) {
      McnPackageInfo packageInfo = new McnPackageInfo(userName, password, name);
      return this.addMcnPackageInfo(packageInfo);
   }

   public GeneralCommonInputBuilder addCareProvider(CareProvider careprovider) {
      this.commonInput.getOrigin().setCareProvider(careprovider);
      return this;
   }

   public GeneralCommonInputBuilder disableValidation() {
      this.validate = false;
      return this;
   }

   CommonInput build() throws ConnectorValidationException {
      if (this.validate) {
         CommonInputValidator.validate(this.commonInput);
      }

      return this.commonInput;
   }
}
