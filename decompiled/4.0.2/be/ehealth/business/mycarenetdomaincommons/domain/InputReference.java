package be.ehealth.business.mycarenetdomaincommons.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.joda.time.DateTime;

public final class InputReference {
   private String inputReference;

   public InputReference() throws TechnicalConnectorException {
      DateTime currentDateTime = new DateTime();
      this.inputReference = currentDateTime.toString("yyyyMMddHHmmss");
   }

   public InputReference(String inputReference) {
      this.inputReference = inputReference;
   }

   public String getInputReference() {
      return this.inputReference;
   }
}
