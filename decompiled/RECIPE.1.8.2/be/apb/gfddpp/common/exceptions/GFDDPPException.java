package be.apb.gfddpp.common.exceptions;

import be.apb.gfddpp.common.status.StatusCode;
import javax.ejb.ApplicationException;

@ApplicationException(
   rollback = true
)
public class GFDDPPException extends Exception {
   private static final long serialVersionUID = 1L;
   private StatusCode code;
   private String[] placeHolders;

   public GFDDPPException(String string) {
      super(string);
   }

   public GFDDPPException(StatusCode code, String[] placeHolders) {
      this.code = code;
      this.placeHolders = placeHolders;
   }

   public GFDDPPException(StatusCode code) {
      this.code = code;
   }

   public StatusCode getStatusCode() {
      return this.code;
   }

   public String[] getPlaceHolders() {
      return this.placeHolders;
   }
}
