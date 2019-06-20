package be.fgov.ehealth.technicalconnector.ra.service.impl;

import org.taktik.connector.technical.beid.BeIDInfo;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.impl.BeIDCredential;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.IdentifierType;
import be.fgov.ehealth.certra.core.v2.CertificateInfoType;
import be.fgov.ehealth.certra.core.v2.ContactDataType;
import be.fgov.ehealth.certra.core.v2.ContractType;
import be.fgov.ehealth.certra.core.v2.EHealthCertificateSigningRequestType;
import be.fgov.ehealth.certra.protocol.v2.GenerateCertificateRequest;
import be.fgov.ehealth.certra.protocol.v2.GenerateCertificateResponse;
import be.fgov.ehealth.certra.protocol.v2.GenerateContractRequest;
import be.fgov.ehealth.certra.protocol.v2.GenerateContractResponse;
import be.fgov.ehealth.certra.protocol.v2.GenerateRevocationContractRequest;
import be.fgov.ehealth.certra.protocol.v2.GenerateRevocationContractResponse;
import be.fgov.ehealth.certra.protocol.v2.GetActorQualitiesRequest;
import be.fgov.ehealth.certra.protocol.v2.GetActorQualitiesResponse;
import be.fgov.ehealth.certra.protocol.v2.GetCertificateInfoForAuthenticationCertificateRequest;
import be.fgov.ehealth.certra.protocol.v2.GetCertificateInfoForAuthenticationCertificateResponse;
import be.fgov.ehealth.certra.protocol.v2.GetCertificateInfoForCitizenRequest;
import be.fgov.ehealth.certra.protocol.v2.GetCertificateInfoForCitizenResponse;
import be.fgov.ehealth.certra.protocol.v2.GetCertificateRequest;
import be.fgov.ehealth.certra.protocol.v2.GetCertificateResponse;
import be.fgov.ehealth.certra.protocol.v2.GetExistingApplicationIdsRequest;
import be.fgov.ehealth.certra.protocol.v2.GetExistingApplicationIdsResponse;
import be.fgov.ehealth.certra.protocol.v2.GetGenericOrganizationTypesRequest;
import be.fgov.ehealth.certra.protocol.v2.GetGenericOrganizationTypesResponse;
import be.fgov.ehealth.certra.protocol.v2.RevokeRequest;
import be.fgov.ehealth.certra.protocol.v2.RevokeResponse;
import be.fgov.ehealth.certra.protocol.v2.SubmitCSRForForeignerRequest;
import be.fgov.ehealth.certra.protocol.v2.SubmitCSRForForeignerResponse;
import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorId;
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
import be.fgov.ehealth.technicalconnector.ra.enumaration.Status;
import be.fgov.ehealth.technicalconnector.ra.enumaration.UsageType;
import be.fgov.ehealth.technicalconnector.ra.mapper.MapperFactory;
import be.fgov.ehealth.technicalconnector.ra.service.AuthenticationCertificateRegistrationService;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import be.fgov.ehealth.technicalconnector.ra.utils.RaUtils;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationCertificateRegistrationServiceImpl implements AuthenticationCertificateRegistrationService, ConfigurableImplementation {
   private static final Logger LOG = LoggerFactory.getLogger(AuthenticationCertificateRegistrationServiceImpl.class);
   private Credential authCred;
   private Credential signCred;

   public Result<Certificate> generateCertificate(NewCertificateContract contract) throws TechnicalConnectorException {
      ContactDataType contactDataType = MapperFactory.mapper().asContactDataType(contract.getContact());
      GenerateCertificateRequest payload = new GenerateCertificateRequest();
      RaUtils.setCommonAttributes(payload);
      EHealthCertificateSigningRequestType eHealthCSR = new EHealthCertificateSigningRequestType();
      eHealthCSR.setCSR(contract.getPkcs10DerEncoded());
      Iterator i$ = contract.getUsageTypes().iterator();

      while(i$.hasNext()) {
         UsageType usageType = (UsageType)i$.next();
         eHealthCSR.getBaseServiceUsages().add(usageType.getServiceName());
      }

      eHealthCSR.setContactData(contactDataType);
      ContractType contractType = MapperFactory.mapper().asContractType(contract);
      eHealthCSR.setContract(contractType);
      payload.setEHealthCSR(eHealthCSR);
      String signedPayload = RaUtils.sign(payload, payload.getId(), this.signCred);
      return new Result(MapperFactory.mapper().asCertificate((GenerateCertificateResponse)RaUtils.invokeCertRa(signedPayload, "urn:be:fgov:ehealth:etee:certra:protocol:v2:generatecertificate", GenerateCertificateResponse.class).getResult()));
   }

   public Result<X509Certificate[]> getCertificate(byte[] publicKeyIdentifier) throws TechnicalConnectorException {
      GetCertificateRequest request = new GetCertificateRequest();
      RaUtils.setCommonAttributes(request);
      request.setPublicKeyIdentifier(publicKeyIdentifier);
      Result<GetCertificateResponse> resp = RaUtils.invokeCertRa(ConnectorXmlUtils.toString((Object)request), "urn:be:fgov:ehealth:etee:certra:protocol:v2:getcertificate", GetCertificateResponse.class);
      if (!resp.getStatus().equals(Status.OK)) {
         return resp.getStatus().equals(Status.PENDING) ? new Result(resp.getTime()) : new Result("Unable to obtain certificate", resp.getCause());
      } else {
         X509Certificate[] x509Certificates = new X509Certificate[0];

         byte[] x509Certificate;
         for(Iterator i$ = ((GetCertificateResponse)resp.getResult()).getX509Certificates().iterator(); i$.hasNext(); x509Certificates = (X509Certificate[])((X509Certificate[])ArrayUtils.add(x509Certificates, CertificateUtils.toX509Certificate(x509Certificate)))) {
            x509Certificate = (byte[])i$.next();
         }

         return new Result(x509Certificates);
      }
   }

   public Result<GeneratedContract> generateContract(ContractRequest contractRequest) throws TechnicalConnectorException {
      GenerateContractRequest generateContractRequest = MapperFactory.mapper().asGenerateContractRequest(contractRequest);
      RaUtils.setIssueInstant(generateContractRequest);
      GenerateContractResponse generateContractResponse = (GenerateContractResponse)RaUtils.invokeCertRa(ConnectorXmlUtils.toString((Object)generateContractRequest), "urn:be:fgov:ehealth:certra:protocol:v2:generateContract", GenerateContractResponse.class).getResult();
      GeneratedContract contract = MapperFactory.mapper().asGeneratedContract(generateContractResponse.getContract());
      contract.setContactData(contractRequest.getContactData());
      contract.setIdentifierType(IdentifierType.lookup(((ActorId)contractRequest.getCertificateIdentifier().getActor().getIds().get(0)).getType(), (String)null, 48));
      return new Result(contract, generateContractResponse);
   }

   public Result<Void> revokeCertificate(RevocationRequest revocationRequest) throws TechnicalConnectorException {
      RevokeRequest revokeRequest = MapperFactory.mapper().asRevokeRequest(revocationRequest);
      RaUtils.setIssueInstant(revokeRequest);
      String signedPayload = RaUtils.sign(revokeRequest, revokeRequest.getId(), this.signCred);
      RevokeResponse revokeResponse = (RevokeResponse)RaUtils.invokeCertRa(signedPayload, "urn:be:fgov:ehealth:etee:certra:protocol:v2:revoke", RevokeResponse.class).getResult();
      return new Result((Void)null, revokeResponse);
   }

   public Result<GetGenericOrganizationTypesResponse> getOrganizationList() throws TechnicalConnectorException {
      GetGenericOrganizationTypesRequest req = new GetGenericOrganizationTypesRequest();
      RaUtils.setCommonAttributes(req);
      return RaUtils.invokeCertRa(ConnectorXmlUtils.toString((Object)req), "urn:be:fgov:ehealth:certra:protocol:v2:getGenericOrganizationTypes", GetGenericOrganizationTypesResponse.class);
   }

   public Result<ActorQualities> getActorQualities() throws TechnicalConnectorException {
      GetActorQualitiesRequest payload = new GetActorQualitiesRequest();
      RaUtils.setCommonAttributes(payload);
      Id id = new Id();
      id.setValue(BeIDInfo.getInstance().getIdentity().getNationalNumber());
      id.setType(IdentifierType.SSIN.name());
      payload.setSSIN(id);
      String signedPayload = RaUtils.sign(payload, payload.getId(), this.authCred);
      GetActorQualitiesResponse response = (GetActorQualitiesResponse)RaUtils.invokeCertRa(signedPayload, "urn:be:fgov:ehealth:certra:protocol:v2:getActorQualities", GetActorQualitiesResponse.class).getResult();
      return new Result(MapperFactory.mapper().asActorQualities(response), response);
   }

   public Result<List<String>> getApplicationIdList(Organization organization) throws TechnicalConnectorException {
      GetExistingApplicationIdsRequest req = new GetExistingApplicationIdsRequest();
      RaUtils.setCommonAttributes(req);
      Id id = new Id();
      id.setType(organization.getType().getType(48));
      id.setValue(organization.getId());
      req.setOrganizationIdentifier(id);
      Result<GetExistingApplicationIdsResponse> response = RaUtils.invokeCertRa(ConnectorXmlUtils.toString((Object)req), "urn:be:fgov:ehealth:etee:certra:protocol:v2:getexistingapplicationids", GetExistingApplicationIdsResponse.class);
      return new Result(((GetExistingApplicationIdsResponse)response.getResult()).getApplicationIds());
   }

   public Result<CertificateInfoType> getCertificateInfoForAuthenticationCertificate(Credential credential) throws TechnicalConnectorException {
      GetCertificateInfoForAuthenticationCertificateRequest req = new GetCertificateInfoForAuthenticationCertificateRequest();
      RaUtils.setCommonAttributes(req);
      String signedPayload = RaUtils.sign(req, req.getId(), credential);
      GetCertificateInfoForAuthenticationCertificateResponse response = (GetCertificateInfoForAuthenticationCertificateResponse)RaUtils.invokeCertRa(signedPayload, "urn:be:fgov:ehealth:etee:certra:protocol:v2:getCertificateInfoForAuthenticationCertificate", GetCertificateInfoForAuthenticationCertificateResponse.class).getResult();
      return new Result(response.getCertificateInfo(), response);
   }

   public Result<List<CertificateInfoType>> getCertificateInfoForCitizen() throws TechnicalConnectorException {
      GetCertificateInfoForCitizenRequest req = new GetCertificateInfoForCitizenRequest();
      RaUtils.setCommonAttributes(req);
      String signedPayload = RaUtils.sign(req, req.getId(), this.authCred);
      GetCertificateInfoForCitizenResponse response = (GetCertificateInfoForCitizenResponse)RaUtils.invokeCertRa(signedPayload, "urn:be:fgov:ehealth:certra:protocol:v2:getCertificateInfoForCitizen", GetCertificateInfoForCitizenResponse.class).getResult();
      return new Result(response.getCertificateInfos(), response);
   }

   public Result<SubmitCSRForForeignerResponseInfo> submitCSRForForeigner(ForeignerRequest foreignerRequest) throws TechnicalConnectorException {
      SubmitCSRForForeignerRequest req = MapperFactory.mapper().asSubmitCSRForForeignerRequest(foreignerRequest);
      RaUtils.setIssueInstant(req);
      SubmitCSRForForeignerResponse response = (SubmitCSRForForeignerResponse)RaUtils.invokeCertRa(ConnectorXmlUtils.toString((Object)req), "urn:be:fgov:ehealth:certra:protocol:v2:submitCSRForForeigner", SubmitCSRForForeignerResponse.class).getResult();
      return new Result(MapperFactory.mapper().asSubmitCSRForForeignerResponseInfo(response), response);
   }

   public Result<GeneratedRevocationContract> generateRevocationContract(RevocationContractRequest revocationContractRequest) throws TechnicalConnectorException {
      GenerateRevocationContractRequest req = MapperFactory.mapper().asGenerateContractRequest(revocationContractRequest);
      RaUtils.setIssueInstant(req);
      GenerateRevocationContractResponse response = (GenerateRevocationContractResponse)RaUtils.invokeCertRa(ConnectorXmlUtils.toString((Object)req), "urn:be:fgov:ehealth:certra:protocol:v2:generateRevocationContract", GenerateRevocationContractResponse.class).getResult();
      return new Result(MapperFactory.mapper().asRevocationContract(response.getContract()), response);
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.authCred = BeIDCredential.getInstance("CertRA", "Authentication");
      this.signCred = BeIDCredential.getInstance("CertRA", "Signature");
   }
}
