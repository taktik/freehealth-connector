package be.fgov.ehealth.technicalconnector.services;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.services.daas.DataAttributeService;
import be.fgov.ehealth.technicalconnector.services.daas.impl.DataAttributeServiceImpl;
import be.fgov.ehealth.technicalconnector.services.schematron.SchematronValidator;
import be.fgov.ehealth.technicalconnector.services.schematron.impl.SchematronValidatorImpl;
import be.fgov.ehealth.technicalconnector.services.validation.ProjectMessageValidator;
import be.fgov.ehealth.technicalconnector.services.validation.impl.ProjectMessageValidatorImpl;
import java.util.HashMap;
import java.util.Map;

public final class ServiceFactory {
   public ServiceFactory() {
   }

   public static DataAttributeService getDataAttributeService() throws TechnicalConnectorException {
      return (DataAttributeService)(new ConfigurableFactoryHelper(DataAttributeService.class.getName(), DataAttributeServiceImpl.class.getName())).getImplementation();
   }

   public static SchematronValidator getSchematronValidator() throws TechnicalConnectorException {
      return (SchematronValidator)(new ConfigurableFactoryHelper(SchematronValidator.class.getName(), SchematronValidatorImpl.class.getName())).getImplementation();
   }

   public static ProjectMessageValidator getProjectMessageValidator(String projectMessage) throws TechnicalConnectorException {
      Map<String, Object> init = new HashMap();
      init.put("schematronProject", projectMessage);
      init.put("schematronValidator", getSchematronValidator());
      return (ProjectMessageValidator)(new ConfigurableFactoryHelper(ProjectMessageValidator.class.getName(), ProjectMessageValidatorImpl.class.getName())).getImplementation(init);
   }
}
