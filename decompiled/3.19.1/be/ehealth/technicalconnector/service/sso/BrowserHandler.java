package be.ehealth.technicalconnector.service.sso;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.net.URI;

public interface BrowserHandler {
   void browse(URI var1) throws TechnicalConnectorException;
}
