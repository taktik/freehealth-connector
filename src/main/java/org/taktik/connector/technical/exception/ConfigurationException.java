package org.taktik.connector.technical.exception;

public class ConfigurationException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public ConfigurationException(String msg) {
      super(msg);
   }

   public ConfigurationException(Exception e) {
      super(e);
   }
}
