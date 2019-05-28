package be.ehealth.business.common.exception;

import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.commons._1_0.core.Status;
import java.text.MessageFormat;

public abstract class EhealthServiceException extends Exception {
   private static final long serialVersionUID = 1L;

   public EhealthServiceException(Status status) {
      super(MessageFormat.format(TechnicalConnectorExceptionValues.ERROR_WS.getMessage(), ConnectorXmlUtils.toString((Object)status)));
   }
}
