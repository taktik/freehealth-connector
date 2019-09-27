package org.taktik.connector.business.mycarenetdomaincommons.domain;

import org.joda.time.DateTime;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

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
