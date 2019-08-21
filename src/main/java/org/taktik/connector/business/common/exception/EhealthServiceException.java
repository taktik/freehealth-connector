package org.taktik.connector.business.common.exception;

import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import be.fgov.ehealth.commons._1_0.core.Status;
import java.text.MessageFormat;

public abstract class EhealthServiceException extends Exception {
   private static final long serialVersionUID = 1L;

   public EhealthServiceException(Status status) {
      super(MessageFormat.format(TechnicalConnectorExceptionValues.ERROR_WS.getMessage(), ConnectorXmlUtils.toString(status)));
   }
}
