package be.ehealth.businessconnector.therlink.service;

import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse;

public interface TherLinkService {
   PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken var1, PutTherapeuticLinkRequest var2) throws TherLinkBusinessConnectorException, TechnicalConnectorException;

   GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken var1, GetTherapeuticLinkRequest var2) throws TechnicalConnectorException, TherLinkBusinessConnectorException;

   RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken var1, RevokeTherapeuticLinkRequest var2) throws TechnicalConnectorException, TherLinkBusinessConnectorException;

   HasTherapeuticLinkResponse hasTherapeuticLink(SAMLToken var1, HasTherapeuticLinkRequest var2) throws TechnicalConnectorException, TherLinkBusinessConnectorException;
}
