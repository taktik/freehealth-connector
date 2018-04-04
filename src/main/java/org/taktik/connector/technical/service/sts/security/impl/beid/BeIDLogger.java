package org.taktik.connector.technical.service.sts.security.impl.beid;

import be.fedict.commons.eid.client.spi.Logger;
import org.slf4j.LoggerFactory;

public class BeIDLogger implements Logger {
   private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BeIDLogger.class);

   public void error(String message) {
      LOG.error(message);
   }

   public void debug(String message) {
      LOG.debug(message);
   }
}
