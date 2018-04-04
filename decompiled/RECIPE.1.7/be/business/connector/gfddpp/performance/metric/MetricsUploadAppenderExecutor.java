package be.business.connector.gfddpp.performance.metric;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.helper.PropertyHandler;
import be.business.connector.common.performance.metric.MetricsUploadAppenderCommon;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.STSHelper;
import org.taktik.connector.business.recipeprojects.common.services.pcdh.PcdhServiceImpl;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.session.Session;
import org.apache.log4j.Logger;

public class MetricsUploadAppenderExecutor extends MetricsUploadAppenderCommon {
   public static final String PROP_WSDL_EHEALTH_PCDH = "wsdl.ehealth.pcdh";
   private static final Logger LOG = Logger.getLogger(MetricsUploadAppenderExecutor.class);

   public MetricsUploadAppenderExecutor() throws GFDDPPException {
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
            this.uploadeMetrics(compressed);
         } else {
            LOG.debug("User does not blong to the selected group. No metrics will be uploaded.");
         }
      } else {
         this.uploadeMetrics(compressed);
      }

   }

   private void uploadeMetrics(byte[] compressed) throws SessionManagementException, IntegrationModuleException {
      if (Session.getInstance().hasValidSession()) {
         UploadPerformanceMetricRequestType request = this.buildPCDHRequest(compressed);
         PcdhServiceImpl.getInstance().uploadPerformanceMetric(request);
         LOG.info("Performance metric send!!!!!");
      } else {
         LOG.info("No session  available for the metrics upload");
      }

   }
}
