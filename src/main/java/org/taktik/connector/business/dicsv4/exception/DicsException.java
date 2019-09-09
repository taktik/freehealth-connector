package org.taktik.connector.business.dicsv4.exception;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public class DicsException extends AbstractDicsException {
   private static final long serialVersionUID = 1L;

   public DicsException(StatusResponseType response) {
      super(response);
   }
}
