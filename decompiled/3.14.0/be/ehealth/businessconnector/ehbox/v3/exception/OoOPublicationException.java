package be.ehealth.businessconnector.ehbox.v3.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import be.fgov.ehealth.ehbox.publication.protocol.v3.Substitute;
import java.util.List;
import java.util.Map;

public class OoOPublicationException extends ConnectorException {
   private static final long serialVersionUID = 1L;
   private Map<BoxIdType, List<Substitute>> oooForwardInformation;

   public OoOPublicationException(String message, String errorCode, Map<BoxIdType, List<Substitute>> oooForwardInformation) {
      super(message, errorCode);
      this.oooForwardInformation = oooForwardInformation;
   }

   public final Map<BoxIdType, List<Substitute>> getOooForwardInformation() {
      return this.oooForwardInformation;
   }
}
