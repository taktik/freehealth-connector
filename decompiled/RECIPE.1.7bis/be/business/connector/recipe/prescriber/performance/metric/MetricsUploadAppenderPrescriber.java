package be.business.connector.recipe.prescriber.performance.metric;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.helper.PropertyHandler;
import be.business.connector.common.performance.metric.MetricsUploadAppenderCommon;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.STSHelper;
import be.business.connector.projects.common.services.recipe.RecipeTechnicalServiceImpl;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import org.apache.log4j.Logger;
import org.apache.log4j.component.helpers.MessageFormatter;

public class MetricsUploadAppenderPrescriber extends MetricsUploadAppenderCommon {
   private static Logger LOG = Logger.getLogger(MetricsUploadAppenderPrescriber.class);

   public MetricsUploadAppenderPrescriber() throws GFDDPPException {
   }

   public void uploadFile(byte[] compressed) throws Exception {
      PropertyHandler propertyHandler = PropertyHandler.getInstance();

      String nihii;
      try {
         nihii = STSHelper.getNihii(Session.getInstance().getSession().getSAMLToken().getAssertion());
      } catch (Throwable var6) {
         LOG.warn("Nihii in session not found: ", var6);
         nihii = propertyHandler.getProperty("metrics.select.nihii");
         LOG.debug("NIHII found in proerperty file is [" + nihii + "]");
      }

      boolean metricsFilteringEnabled = Boolean.parseBoolean(propertyHandler.getProperty("metrics.select.nihii.enable", "false"));
      String groupOfNihiiEnabled = propertyHandler.getProperty("metrics.select.nihii.group");
      if (metricsFilteringEnabled) {
         if (nihii.endsWith(groupOfNihiiEnabled)) {
            this.uploadMetrics(compressed);
         } else {
            LOG.debug(MessageFormatter.format("Info will not be logged because the nihii {} does not ends up with {}", nihii, groupOfNihiiEnabled));
         }
      } else {
         this.uploadMetrics(compressed);
      }

   }

   private void uploadMetrics(byte[] compressed) throws SessionManagementException, IntegrationModuleException {
      if (Session.getInstance().hasValidSession()) {
         UploadFileRequestType request = this.buildRecipeRequest(compressed);
         RecipeTechnicalServiceImpl.getInstance().uploadFilePrescriber(request);
      } else {
         LOG.info("No session  available for the metrics upload");
      }

   }
}
