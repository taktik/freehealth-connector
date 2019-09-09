package org.taktik.connector.business.dicsv4.exception;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;

public class DicsDataNotFoundException extends AbstractDicsException {
   private static final long serialVersionUID = 1L;

   public DicsDataNotFoundException(StatusResponseType response) {
      super(response);
   }
}
