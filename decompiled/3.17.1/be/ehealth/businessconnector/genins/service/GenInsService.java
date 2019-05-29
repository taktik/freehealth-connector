package be.ehealth.businessconnector.genins.service;

import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsFlatResponse;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse;

public interface GenInsService {
   GetInsurabilityResponse getInsurability(SAMLToken var1, GetInsurabilityAsXmlOrFlatRequestType var2) throws GenInsBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   GetInsurabilityAsFlatResponse getInsurabilityAsFlat(SAMLToken var1, GetInsurabilityAsXmlOrFlatRequestType var2) throws GenInsBusinessConnectorException, TechnicalConnectorException, SessionManagementException;
}
