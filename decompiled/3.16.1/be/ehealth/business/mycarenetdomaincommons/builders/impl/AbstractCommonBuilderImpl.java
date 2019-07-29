package be.ehealth.business.mycarenetdomaincommons.builders.impl;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.mycarenetdomaincommons.builders.CommonBuilder;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.business.mycarenetdomaincommons.util.PropertyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.util.Map;
import org.joda.time.DateTime;

public abstract class AbstractCommonBuilderImpl implements CommonBuilder {
   private static final String SENDER_PROPERTIES_ARE_NOT_COHERENT = " Sender properties are not coherent";
   protected static final String NIHII = ".nihii";
   protected static final String CBE = ".cbe";
   protected static final String NAME = ".name";
   protected static final String SSIN = ".ssin";
   protected static final String ORGANIZATION = ".organization";
   private static final String SENDER = ".sender";
   private static final String SITE_ID = ".site.id";
   protected static final String MYCARENET = "mycarenet.";
   private static final String QUALITY = ".quality";
   private static final String PHYSICALPERSON = ".physicalperson";
   private static final String VALUE = ".value";
   protected String projectName = "default";
   private ConfigValidator config = ConfigFactory.getConfigValidator();

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      if (parameterMap != null && !parameterMap.isEmpty() && parameterMap.containsKey("projectName")) {
         this.projectName = (String)parameterMap.get("projectName");
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"missing config parameters for initialize of CommonBuilder , check factory method call"});
      }
   }

   /** @deprecated */
   @Deprecated
   public CommonInput createCommonInput(PackageInfo packageInfo, boolean isTest, String inputReference) throws TechnicalConnectorException {
      Origin origin = this.createOrigin(packageInfo);
      return new CommonInput(isTest, origin, inputReference);
   }

   public CommonInput createCommonInput(McnPackageInfo packageInfo, boolean isTest, String inputReference) throws TechnicalConnectorException {
      Origin origin = this.createOrigin(packageInfo);
      return new CommonInput(isTest, origin, inputReference);
   }

   /** @deprecated */
   @Deprecated
   public Origin createOrigin(PackageInfo packageInfo) throws TechnicalConnectorException {
      Origin origin = new Origin(packageInfo, this.createCareProviderForOrigin());
      origin.setSender(this.createSenderForOrigin());
      origin.setSiteId(this.getSiteId());
      return origin;
   }

   public Origin createOrigin(McnPackageInfo packageInfo) throws TechnicalConnectorException {
      Origin origin = new Origin(packageInfo, this.createCareProviderForOrigin());
      origin.setSender(this.createSenderForOrigin());
      origin.setSiteId(this.getSiteId());
      return origin;
   }

   private String getSiteId() throws TechnicalConnectorException {
      if (this.projectName == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"projectName"});
      } else {
         String projectNameToUse = PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet.");
         String siteIdKey = "mycarenet." + projectNameToUse + ".site.id";
         String siteId = this.config.getProperty(siteIdKey);
         return siteId;
      }
   }

   Party createSenderForOrigin() throws TechnicalConnectorException {
      Party party = new Party();
      String senderRootKey = "mycarenet." + PropertyUtil.retrieveProjectNameToUse(this.projectName, "mycarenet..") + ".sender";
      party.setPhysicalPerson(this.createPerson(senderRootKey + ".physicalperson"));
      party.setOrganization(this.createOrganization(senderRootKey + ".organization"));
      return party;
   }

   public Routing createRouting(Patient patientInfo, DateTime refDate) {
      Routing routing = new Routing();
      routing.setPeriod((Period)null);
      routing.setCareReceiver(this.createCareReceiver(patientInfo));
      routing.setReferenceDate(refDate);
      return routing;
   }

   public Routing createRoutingToMutuality(String mutuality, DateTime refDate) {
      Routing routing = new Routing();
      routing.setPeriod((Period)null);
      routing.setCareReceiver(this.createCareReceiverForMutuality(mutuality));
      routing.setReferenceDate(refDate);
      return routing;
   }

   protected final CareReceiverId createCareReceiver(Patient patientInfo) {
      CareReceiverId careReceiver = new CareReceiverId(patientInfo.getInss(), patientInfo.getRegNrWithMut(), patientInfo.getMutuality());
      return careReceiver;
   }

   protected final CareReceiverId createCareReceiverForMutuality(String mutuality) {
      CareReceiverId careReceiver = new CareReceiverId((String)null, (String)null, mutuality);
      return careReceiver;
   }

   protected Identification createOrganization(String key) throws TechnicalConnectorException {
      Identification identification = this.getIdentification(key);
      boolean containsOrganization = identification != null;
      if (containsOrganization && identification.getCbe() == null && identification.getNihii() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Organization Sender properties are not coherent"});
      } else {
         return identification;
      }
   }

   protected Identification createPerson(String key) throws TechnicalConnectorException {
      Identification identification = this.getIdentification(key);
      boolean containsPhysicalPerson = identification != null;
      if (containsPhysicalPerson && identification.getSsin() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Physical person Sender properties are not coherent"});
      } else {
         return identification;
      }
   }

   private Identification getIdentification(String key) {
      Identification identification = new Identification();
      String name = this.config.getProperty(key + ".name");
      if (name != null) {
         identification.setName(name);
      }

      String ssin = this.config.getProperty(key + ".ssin");
      if (ssin != null) {
         identification.setSsin(ssin);
      }

      String nihii = this.config.getProperty(key + ".nihii" + ".value");
      String cbe;
      if (nihii != null) {
         cbe = this.config.getProperty(key + ".nihii" + ".quality");
         Nihii nihiiObject = new Nihii(cbe, nihii);
         identification.setNihii(nihiiObject);
      }

      cbe = this.config.getProperty(key + ".cbe");
      if (cbe != null) {
         identification.setCbe(cbe);
      }

      return this.isIdentificationEmpty(identification) ? null : identification;
   }

   private boolean isIdentificationEmpty(Identification identification) {
      return identification.getCbe() == null && identification.getName() == null && identification.getNihii() == null && identification.getSsin() == null;
   }

   protected abstract CareProvider createCareProviderForOrigin() throws NumberFormatException, TechnicalConnectorException;
}
