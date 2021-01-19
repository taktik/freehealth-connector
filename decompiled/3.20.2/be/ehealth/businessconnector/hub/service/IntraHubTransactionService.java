package be.ehealth.businessconnector.hub.service;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderGetTransactionList;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.util.List;

public interface IntraHubTransactionService {
   List<IDKMEHR> declareTransaction(KmehrHeaderDeclareTransaction var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putTransaction(Kmehrmessage var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeTransaction(PatientIdType var1, TransactionIdType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   KmehrHeaderGetTransactionList getTransactionList(PatientIdType var1, LocalSearchType var2, TransactionWithPeriodType var3) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   Kmehrmessage getTransaction(PatientIdType var1, TransactionBaseType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void requestPublication(PatientIdType var1, TransactionWithPeriodType var2, String var3) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
