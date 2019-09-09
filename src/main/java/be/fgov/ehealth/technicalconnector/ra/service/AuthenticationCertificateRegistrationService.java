package be.fgov.ehealth.technicalconnector.ra.service;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.certra.core.v2.CertificateInfoType;
import be.fgov.ehealth.certra.protocol.v2.GetGenericOrganizationTypesResponse;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorQualities;
import be.fgov.ehealth.technicalconnector.ra.domain.Certificate;
import be.fgov.ehealth.technicalconnector.ra.domain.ContractRequest;
import be.fgov.ehealth.technicalconnector.ra.domain.ForeignerRequest;
import be.fgov.ehealth.technicalconnector.ra.domain.GeneratedContract;
import be.fgov.ehealth.technicalconnector.ra.domain.GeneratedRevocationContract;
import be.fgov.ehealth.technicalconnector.ra.domain.NewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.domain.RevocationContractRequest;
import be.fgov.ehealth.technicalconnector.ra.domain.RevocationRequest;
import be.fgov.ehealth.technicalconnector.ra.domain.SubmitCSRForForeignerResponseInfo;
import java.security.cert.X509Certificate;
import java.util.List;

public interface AuthenticationCertificateRegistrationService {
   Result<Certificate> generateCertificate(NewCertificateContract var1) throws TechnicalConnectorException;

   Result<X509Certificate[]> getCertificate(byte[] var1) throws TechnicalConnectorException;

   Result<Void> revokeCertificate(RevocationRequest var1) throws TechnicalConnectorException;

   Result<GetGenericOrganizationTypesResponse> getOrganizationList() throws TechnicalConnectorException;

   Result<List<String>> getApplicationIdList(Organization var1) throws TechnicalConnectorException;

   Result<GeneratedContract> generateContract(ContractRequest var1) throws TechnicalConnectorException;

   Result<ActorQualities> getActorQualities() throws TechnicalConnectorException;

   Result<CertificateInfoType> getCertificateInfoForAuthenticationCertificate(Credential var1) throws TechnicalConnectorException;

   Result<List<CertificateInfoType>> getCertificateInfoForCitizen() throws TechnicalConnectorException;

   Result<SubmitCSRForForeignerResponseInfo> submitCSRForForeigner(ForeignerRequest var1) throws TechnicalConnectorException;

   Result<GeneratedRevocationContract> generateRevocationContract(RevocationContractRequest var1) throws TechnicalConnectorException;
}
