package be.ehealth.businessconnector.hub.service;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.AccessRightListType;
import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.TransactionAccessListType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;

public interface IntraHubAccessRightService {
   void putAccessRight(AccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   AccessRightListType getAccessRight(TransactionIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeAccessRight(SelectRevokeAccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   TransactionAccessListType getPatientAuditTrail(SelectGetPatientAuditTrailType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
