package be.ehealth.technicalconnector.service.sso;

import be.ehealth.technicalconnector.enumeration.SsoProfile;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public interface SingleSignOnService {
   void signin(SsoProfile var1, String var2) throws TechnicalConnectorException;

   void signin(SsoProfile var1) throws TechnicalConnectorException;

   void setHandler(BrowserHandler var1);
}
