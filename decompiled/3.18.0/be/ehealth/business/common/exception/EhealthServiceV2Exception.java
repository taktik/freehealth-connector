package be.ehealth.business.common.exception;

import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.text.MessageFormat;

public abstract class EhealthServiceV2Exception extends Exception {
   private static final long serialVersionUID = 1L;
   private StatusResponseType response;

   public EhealthServiceV2Exception(StatusResponseType statusResponse) {
      super(MessageFormat.format(TechnicalConnectorExceptionValues.ERROR_WS.getMessage(), ConnectorXmlUtils.toString((Object)statusResponse.getStatus())));
      this.response = statusResponse;
   }

   public StatusResponseType getResponse() {
      return this.response;
   }
}
