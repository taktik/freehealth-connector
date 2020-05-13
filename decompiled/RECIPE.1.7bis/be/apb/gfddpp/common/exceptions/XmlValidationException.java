package be.apb.gfddpp.common.exceptions;

import be.apb.gfddpp.common.log.Logger;

public class XmlValidationException extends Exception {
   private static final Logger LOG = Logger.getLogger(XmlValidationException.class);
   private static final long serialVersionUID = 1L;

   public XmlValidationException(String message, Throwable cause) {
      super(message, cause);
      LOG.error(message, this);
   }

   public XmlValidationException(String message) {
      super(message);
      LOG.error(message, this);
   }

   public XmlValidationException(Throwable cause) {
      super(cause);
      LOG.error("XmlValidationException", cause);
   }
}
