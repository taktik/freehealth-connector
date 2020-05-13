package be.ehealth.businessconnector.ehbox.v3.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;

public class OoOPublicationException extends ConnectorException {
   private static final long serialVersionUID = 1L;
   private SendMessageResponse response;

   public OoOPublicationException(String message, String errorCode, SendMessageResponse response) {
      super(message, errorCode);
      this.response = response;
   }

   public final SendMessageResponse getResponse() {
      return this.response;
   }
}
