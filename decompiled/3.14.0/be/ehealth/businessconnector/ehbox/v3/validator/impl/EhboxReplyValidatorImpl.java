package be.ehealth.businessconnector.ehbox.v3.validator.impl;

import be.ehealth.businessconnector.ehbox.v3.exception.OoOPublicationException;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.Recipient;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;
import be.fgov.ehealth.ehbox.publication.protocol.v3.Substitute;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
               SendMessageResponse messageResponse = (SendMessageResponse)response;
               Map<BoxIdType, List<Substitute>> oooInformation = new HashMap();
               Iterator i$ = messageResponse.getRecipients().iterator();

               while(i$.hasNext()) {
                  Recipient recipient = (Recipient)i$.next();
                  if (recipient.getAbsentFrom() != null) {
                     BoxIdType receiver = new BoxIdType();
                     receiver.setId(recipient.getId());
                     receiver.setQuality(recipient.getQuality());
                     receiver.setType(recipient.getType());
                     receiver.setSubType(recipient.getSubType());
                     oooInformation.put(receiver, recipient.getSubstitutes());
                  }
               }

               throw new OoOPublicationException(TechnicalConnectorExceptionValues.ERROR_WS.getMessage(), "826", oooInformation);
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
