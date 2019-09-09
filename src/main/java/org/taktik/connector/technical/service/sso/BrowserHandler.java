package org.taktik.connector.technical.service.sso;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.net.URI;

public interface BrowserHandler {
   void browse(URI var1) throws TechnicalConnectorException;
}
