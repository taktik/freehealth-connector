package be.ehealth.technicalconnector.service.sts;

import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttribute;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttributeDesignator;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import java.util.List;
import org.w3c.dom.Element;

public interface STSService {
   /** @deprecated */
   @Deprecated
   Element getToken(Credential var1, Credential var2, List<SAMLAttribute> var3, List<SAMLAttributeDesignator> var4, String var5, int var6) throws TechnicalConnectorException;

   Element getToken(Credential var1, Credential var2, List<SAMLAttribute> var3, List<SAMLAttributeDesignator> var4, String var5, Duration var6) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Element getToken(Credential var1, Credential var2, List<SAMLAttribute> var3, List<SAMLAttributeDesignator> var4, String var5, String var6, String var7, String var8, int var9) throws TechnicalConnectorException;

   Element getToken(Credential var1, Credential var2, List<SAMLAttribute> var3, List<SAMLAttributeDesignator> var4, String var5, String var6, String var7, String var8, Duration var9) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Element renewToken(Credential var1, Credential var2, Element var3, int var4) throws TechnicalConnectorException;

   Element renewToken(Credential var1, Credential var2, Element var3, Duration var4) throws TechnicalConnectorException;
}
