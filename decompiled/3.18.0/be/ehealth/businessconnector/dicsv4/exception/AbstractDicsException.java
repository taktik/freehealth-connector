package be.ehealth.businessconnector.dicsv4.exception;

import be.ehealth.business.common.exception.EhealthServiceV2Exception;
import be.ehealth.business.common.helper.EhealthServiceHelper;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.dics.protocol.v4.Anomaly;
import java.util.List;

public abstract class AbstractDicsException extends EhealthServiceV2Exception {
   private static final long serialVersionUID = 1L;
   private List<Anomaly> anomalies;

   public AbstractDicsException(StatusResponseType response) {
      super(response);
      this.anomalies = EhealthServiceHelper.toList(response.getStatus().getStatusDetail(), Anomaly.class);
   }

   public List<Anomaly> getAnomalies() {
      return this.anomalies;
   }
}
