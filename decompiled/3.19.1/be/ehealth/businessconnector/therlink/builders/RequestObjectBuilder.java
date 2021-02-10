package be.ehealth.businessconnector.therlink.builders;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.requests.GetTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.HasTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.PutTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.RevokeTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

public interface RequestObjectBuilder {
   int MAXROWS_DEFAULT = 1000;
   /** @deprecated */
   @Deprecated
   String PROP_HCP_TYPE = "therlink.enduser.hcpartytype";
   String MAXROWS_PROPERTY_KEY = "therlink.maxrows";

   /** @deprecated */
   @Deprecated
   PutTherapeuticLinkRequest createPutTherapeuticLinkRequest(Patient var1, HcParty var2, Date var3, String var4, String var5, Proof... var6) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   PutTherapeuticLinkRequest createPutTherapeuticLinkRequest(DateTime var1, Patient var2, HcParty var3, String var4, String var5, Proof... var6) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   /** @deprecated */
   @Deprecated
   RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest(Patient var1, HcParty var2, Date var3, Date var4, String var5, String var6, Proof... var7) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest(DateTime var1, DateTime var2, Patient var3, HcParty var4, String var5, String var6, Proof... var7) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   HasTherapeuticLinkRequest createHasTherapeuticLinkRequest(TherapeuticLink var1) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   GetTherapeuticLinkRequest createGetTherapeuticLinkRequest(TherapeuticLink var1, int var2, Proof... var3) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   GetTherapeuticLinkRequest createGetTherapeuticLinkRequest(TherapeuticLink var1, Proof... var2) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   List<HcParty> getAuthorHcParties() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   /** @deprecated */
   @Deprecated
   String getEnduserHcpType();

   RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequest(TherapeuticLink var1) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   RevokeTherapeuticLinkRequest createRevokeTherapeuticLinkRequestWithProof(TherapeuticLink var1, Proof... var2) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;

   int getMaxRows();

   PutTherapeuticLinkRequest createPutTherapeuticLinkRequest(Patient var1, HcParty var2, String var3, Proof var4) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException;
}
