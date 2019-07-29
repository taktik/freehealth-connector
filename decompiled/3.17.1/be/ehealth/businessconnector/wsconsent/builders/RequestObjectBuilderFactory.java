package be.ehealth.businessconnector.wsconsent.builders;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   private static final String PROP_REQUESTOBJECTBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.RequestObjectBuilder.class";
   private static final String DEFAULT_REQUESTOBJECTBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.impl.RequestObjectBuilderImpl";
   private static final String PROP_CONSENTBUILDER_CLASS = "wsconsent.ConsentBuilder.class";
   private static final String PROP_PATIENTINFOBUILDER_CLASS = "wsconsent.patientinfobuilder.class";
   private static final String PROP_HCPARTYINFOBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.HcPartyInfoBuilder.class";
   private static final String PROP_AUTHORBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.AuthorBuilder.class";
   private static final String DEFAULT_PATIENTINFOBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.impl.PatientInfoBuilderImpl";
   private static final String DEFAULT_HCPARTYINFOBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.impl.DelegatingHcPartyInfoBuilderImpl";
   private static final String DEFAULT_CONSENTBUILDER_CLASS = "be.ehealth.businessconnector.wsconsent.builders.impl.ConsentBuilderImpl";
   private static final String DEFAULT_AUTHOR_CLASS = "be.ehealth.businessconnector.wsconsent.builders.impl.AuthorBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryRequestObjectBuilder = new ConfigurableFactoryHelper("be.ehealth.businessconnector.wsconsent.builders.RequestObjectBuilder.class", "be.ehealth.businessconnector.wsconsent.builders.impl.RequestObjectBuilderImpl");
   private static ConfigurableFactoryHelper<PatientInfoBuilder> helperFactoryPatientInfoBuilder = new ConfigurableFactoryHelper("wsconsent.patientinfobuilder.class", "be.ehealth.businessconnector.wsconsent.builders.impl.PatientInfoBuilderImpl");
   private static ConfigurableFactoryHelper<HcPartyInfoBuilder> helperFactoryHcPartyInfoBuilder = new ConfigurableFactoryHelper("be.ehealth.businessconnector.wsconsent.builders.HcPartyInfoBuilder.class", "be.ehealth.businessconnector.wsconsent.builders.impl.DelegatingHcPartyInfoBuilderImpl");
   private static ConfigurableFactoryHelper<ConsentBuilder> helperFactoryConsentBuilder = new ConfigurableFactoryHelper("wsconsent.ConsentBuilder.class", "be.ehealth.businessconnector.wsconsent.builders.impl.ConsentBuilderImpl");
   private static ConfigurableFactoryHelper<AuthorBuilder> helperFactoryAuthorBuilder = new ConfigurableFactoryHelper("be.ehealth.businessconnector.wsconsent.builders.AuthorBuilder.class", "be.ehealth.businessconnector.wsconsent.builders.impl.AuthorBuilderImpl");

   private RequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      return (RequestObjectBuilder)helperFactoryRequestObjectBuilder.getImplementation();
   }

   public static PatientInfoBuilder getPatientInfoBuilder() throws TechnicalConnectorException {
      return (PatientInfoBuilder)helperFactoryPatientInfoBuilder.getImplementation();
   }

   public static HcPartyInfoBuilder getHcPartyInfoBuilder() throws TechnicalConnectorException, WsConsentBusinessConnectorException {
      return (HcPartyInfoBuilder)helperFactoryHcPartyInfoBuilder.getImplementation();
   }

   public static ConsentBuilder getConsentBuilder() throws TechnicalConnectorException, WsConsentBusinessConnectorException {
      return (ConsentBuilder)helperFactoryConsentBuilder.getImplementation();
   }

   public static AuthorBuilder getAuthorBuilder() throws TechnicalConnectorException, WsConsentBusinessConnectorException {
      return (AuthorBuilder)helperFactoryAuthorBuilder.getImplementation();
   }
}
