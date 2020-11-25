package be.ehealth.businessconnector.therlink.builders;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

public interface CommonObjectBuilder {
   /** @deprecated */
   @Deprecated
   TherapeuticLink createTherapeuticLink(Patient var1, String var2, String var3, Date var4, Date var5, String var6, HcParty var7) throws TechnicalConnectorException;

   TherapeuticLink createTherapeuticLink(DateTime var1, DateTime var2, Patient var3, String var4, String var5, String var6, HcParty var7) throws TechnicalConnectorException;

   Author createAuthor(List<HcParty> var1) throws TechnicalConnectorException;

   String createKmehrID() throws TechnicalConnectorException;
}
