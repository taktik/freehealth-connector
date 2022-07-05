package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import org.joda.time.DateTime;

public final class SubmitCSRForForeignerResponseInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String validationUrl;
   protected DateTime expirationDate;

   public SubmitCSRForForeignerResponseInfo() {
   }

   public String getValidationUrl() {
      return this.validationUrl;
   }

   public void setValidationUrl(String validationUrl) {
      this.validationUrl = validationUrl;
   }

   public DateTime getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(DateTime expirationDate) {
      this.expirationDate = expirationDate;
   }
}
