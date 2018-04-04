package be.business.connector.common.performance.metric;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.performancemetrics.MetricsUploadAppender;
import org.taktik.connector.business.recipeprojects.common.services.pcdh.PcdhServiceImpl;
import org.taktik.connector.business.recipeprojects.common.services.recipe.RecipeTechnicalServiceImpl;
import be.ehealth.apb.gfddpp.services.pcdh.UploadPerformanceMetricRequestType;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v1.UploadFileRequestType;
import org.apache.log4j.Logger;

public class MetricsUploadAppenderCommon extends MetricsUploadAppender {
   private static final Logger LOG = Logger.getLogger(MetricsUploadAppenderCommon.class);
   public static final String PROP_WSDL_EHEALTH_PCDH = "wsdl.ehealth.pcdh";

   public MetricsUploadAppenderCommon() throws GFDDPPException {
   }

   public void uploadFile(byte[] compressed) throws Exception {
      if (Session.getInstance().hasValidSession()) {
         LOG.info("A session is available for the metric upload ");
         UploadPerformanceMetricRequestType requestPCDH = this.buildPCDHRequest(compressed);
         UploadFileRequestType requestRecipe = this.buildRecipeRequest(compressed);
         PcdhServiceImpl.getInstance().uploadPerformanceMetric(requestPCDH);
         RecipeTechnicalServiceImpl.getInstance().uploadFileExecutor(requestRecipe);
      } else {
         LOG.info("No session  available for the metrics upload");
      }

   }

   protected UploadFileRequestType buildRecipeRequest(byte[] compressed) {
      UploadFileRequestType request = new UploadFileRequestType();
      SecuredContentType content = new SecuredContentType();
      content.setSecuredContent(compressed);
      request.setSecuredUploadFileRequest(content);
      return request;
   }

   protected UploadPerformanceMetricRequestType buildPCDHRequest(byte[] compressed) {
      UploadPerformanceMetricRequestType request = new UploadPerformanceMetricRequestType();
      request.setUploadPerformanceMetricParamSealed(compressed);
      return request;
   }
}
