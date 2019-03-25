package be.ehealth.businessconnector.therlink.session;

import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse;

public interface TherLinkService {
   PutTherapeuticLinkResponse putTherapeuticLink(PutTherapeuticLinkRequest var1) throws TherLinkBusinessConnectorException, TechnicalConnectorException;

   GetTherapeuticLinkResponse getTherapeuticLink(GetTherapeuticLinkRequest var1) throws TechnicalConnectorException, TherLinkBusinessConnectorException;

   RevokeTherapeuticLinkResponse revokeTherapeuticLink(RevokeTherapeuticLinkRequest var1) throws TechnicalConnectorException, TherLinkBusinessConnectorException;

   HasTherapeuticLinkResponse hasTherapeuticLink(HasTherapeuticLinkRequest var1) throws TechnicalConnectorException, TherLinkBusinessConnectorException;
}
