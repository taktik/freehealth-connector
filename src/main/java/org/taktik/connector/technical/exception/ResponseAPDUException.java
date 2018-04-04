package org.taktik.connector.technical.exception;

import javax.smartcardio.ResponseAPDU;

public class ResponseAPDUException extends Exception {
   private static final long serialVersionUID = -3573705690889181394L;
   private final ResponseAPDU apdu;

   public ResponseAPDUException(ResponseAPDU apdu) {
      this.apdu = apdu;
   }

   public ResponseAPDUException(String message, ResponseAPDU apdu) {
      super(message + " [" + Integer.toHexString(apdu.getSW()) + "]");
      this.apdu = apdu;
   }

   public ResponseAPDU getApdu() {
      return this.apdu;
   }
}
