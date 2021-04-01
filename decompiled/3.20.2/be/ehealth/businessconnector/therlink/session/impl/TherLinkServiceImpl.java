package be.ehealth.businessconnector.therlink.session.impl;

import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.session.TherLinkService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse;

public class TherLinkServiceImpl implements TherLinkService {
   private be.ehealth.businessconnector.therlink.service.TherLinkService service = new be.ehealth.businessconnector.therlink.service.impl.TherLinkServiceImpl();

   public TherLinkServiceImpl() throws TechnicalConnectorException {
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(PutTherapeuticLinkRequest request) throws TherLinkBusinessConnectorException, TechnicalConnectorException {
      return this.service.putTherapeuticLink(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(GetTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      return this.service.getTherapeuticLink(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(RevokeTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      return this.service.revokeTherapeuticLink(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public HasTherapeuticLinkResponse hasTherapeuticLink(HasTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      return this.service.hasTherapeuticLink(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
