package be.ehealth.businessconnector.genins.session.impl;

import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.businessconnector.genins.service.ServiceFactory;
import be.ehealth.businessconnector.genins.session.GenInsService;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsFlatResponse;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse;

public class GenInsServiceImpl implements GenInsService {
   public GetInsurabilityAsFlatResponse getInsurabilityAsFlat(GetInsurabilityAsXmlOrFlatRequestType request) throws GenInsBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      be.ehealth.businessconnector.genins.service.GenInsService service = ServiceFactory.getGeninsService();
      return service.getInsurabilityAsFlat(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public GetInsurabilityResponse getInsurability(GetInsurabilityAsXmlOrFlatRequestType request) throws GenInsBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      be.ehealth.businessconnector.genins.service.GenInsService service = ServiceFactory.getGeninsService();
      return service.getInsurability(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
