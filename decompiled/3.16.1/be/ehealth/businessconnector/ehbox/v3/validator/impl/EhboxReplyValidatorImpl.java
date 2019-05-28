package be.ehealth.businessconnector.ehbox.v3.validator.impl;

import be.ehealth.businessconnector.ehbox.v3.exception.OoOPublicationException;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhboxReplyValidatorImpl implements EhboxReplyValidator {
   private static final Logger LOG = LoggerFactory.getLogger(EhboxReplyValidatorImpl.class);
   public static final String EHEALTH_SUCCESS_CODE_100 = "100";
   public static final String EHEALTH_SUCCESS_CODE_200 = "200";
   public static final String EHBOX_OOO_EXCEPTION = "826";

   public boolean validateReplyStatus(ResponseType response) throws ConnectorException {
      if (!"100".equals(response.getStatus().getCode()) && !"200".equals(response.getStatus().getCode())) {
         if ("826".equals(response.getStatus().getCode())) {
            if (response instanceof SendMessageResponse) {
               throw new OoOPublicationException(TechnicalConnectorExceptionValues.ERROR_WS.getMessage(), "826", (SendMessageResponse)response);
            } else {
               throw this.generateError(response);
            }
         } else {
            throw this.generateError(response);
         }
      } else {
         return true;
      }
   }

   private TechnicalConnectorException generateError(ResponseType response) {
      StringBuilder reasonBuilder = new StringBuilder("Received Code[");
      reasonBuilder.append(response.getStatus().getCode());
      reasonBuilder.append("] Reason: ");
      Iterator i$ = response.getStatus().getMessages().iterator();

      while(i$.hasNext()) {
         LocalisedString localisedString = (LocalisedString)i$.next();
         reasonBuilder.append(" ").append(localisedString.getValue());
      }

      LOG.error(reasonBuilder.toString());
      return new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{reasonBuilder.toString()});
   }
}
