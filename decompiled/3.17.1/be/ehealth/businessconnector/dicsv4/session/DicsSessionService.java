package be.ehealth.businessconnector.dicsv4.session;

import be.ehealth.businessconnector.dicsv4.exception.DicsDataNotFoundException;
import be.ehealth.businessconnector.dicsv4.exception.DicsException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.dics.protocol.v4.FindAmpRequest;
import be.fgov.ehealth.dics.protocol.v4.FindAmpResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCommentedClassificationRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCommentedClassificationResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCompanyRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCompanyResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingFormulaRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingFormulaResponse;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingIngredientRequest;
import be.fgov.ehealth.dics.protocol.v4.FindCompoundingIngredientResponse;
import be.fgov.ehealth.dics.protocol.v4.FindLegislationTextRequest;
import be.fgov.ehealth.dics.protocol.v4.FindLegislationTextResponse;
import be.fgov.ehealth.dics.protocol.v4.FindReferencesRequest;
import be.fgov.ehealth.dics.protocol.v4.FindReferencesResponse;
import be.fgov.ehealth.dics.protocol.v4.FindReimbursementRequest;
import be.fgov.ehealth.dics.protocol.v4.FindReimbursementResponse;
import be.fgov.ehealth.dics.protocol.v4.FindVmpGroupRequest;
import be.fgov.ehealth.dics.protocol.v4.FindVmpGroupResponse;
import be.fgov.ehealth.dics.protocol.v4.FindVmpRequest;
import be.fgov.ehealth.dics.protocol.v4.FindVmpResponse;
import be.fgov.ehealth.dics.protocol.v4.FindVtmRequest;
import be.fgov.ehealth.dics.protocol.v4.FindVtmResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductPackagesRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductPackagesResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductsRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfActualMedicinalProductsResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVirtualMedicinalProductsRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVirtualMedicinalProductsResponse;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVmpGroupsRequest;
import be.fgov.ehealth.dics.protocol.v4.GetListOfVmpGroupsResponse;

public interface DicsSessionService {
   FindAmpResponse findAmp(FindAmpRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindCommentedClassificationResponse findCommentedClassification(FindCommentedClassificationRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindCompanyResponse findCompany(FindCompanyRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindCompoundingIngredientResponse findIngredient(FindCompoundingIngredientRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindVmpGroupResponse findVmpGroup(FindVmpGroupRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindVtmResponse findVtm(FindVtmRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindCompoundingFormulaResponse findFormula(FindCompoundingFormulaRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindLegislationTextResponse findLegislationText(FindLegislationTextRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindReferencesResponse findReferences(FindReferencesRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindReimbursementResponse findReimbursement(FindReimbursementRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   FindVmpResponse findVmp(FindVmpRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   GetListOfVirtualMedicinalProductsResponse getListOfVirtualMedicinalProducts(GetListOfVirtualMedicinalProductsRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   GetListOfVmpGroupsResponse getListOfVmpGroups(GetListOfVmpGroupsRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   GetListOfActualMedicinalProductPackagesResponse getListOfActualMedicinalProductPackages(GetListOfActualMedicinalProductPackagesRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;

   GetListOfActualMedicinalProductsResponse getListOfActualMedicinalProducts(GetListOfActualMedicinalProductsRequest var1) throws TechnicalConnectorException, DicsException, DicsDataNotFoundException;
}
