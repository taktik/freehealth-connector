package be.fgov.ehealth.technicalconnector.ra.service.impl;

import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.impl.BeIDCredential;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.certra.protocol.v1.GenerateCertificateForRenewalRequest;
import be.fgov.ehealth.certra.protocol.v1.GenerateCertificateForRenewalResponse;
import be.fgov.ehealth.certra.protocol.v1.GenerateCertificateRequest;
import be.fgov.ehealth.certra.protocol.v1.GenerateCertificateResponse;
import be.fgov.ehealth.certra.protocol.v1.GetCertificateRequest;
import be.fgov.ehealth.certra.protocol.v1.GetCertificateResponse;
import be.fgov.ehealth.certra.protocol.v1.GetEHActorQualitiesRequest;
import be.fgov.ehealth.certra.protocol.v1.GetEHActorQualitiesResponse;
import be.fgov.ehealth.certra.protocol.v1.GetExistingApplicationIdsRequest;
import be.fgov.ehealth.certra.protocol.v1.GetExistingApplicationIdsResponse;
import be.fgov.ehealth.certra.protocol.v1.GetRevocableCertificatesRequest;
import be.fgov.ehealth.certra.protocol.v1.GetRevocableCertificatesResponse;
import be.fgov.ehealth.certra.protocol.v1.RevokeRequest;
import be.fgov.ehealth.certra.protocol.v1.RevokeResponse;
import be.fgov.ehealth.certra.protocol.v1.SearchCriteriumType;
import be.fgov.ehealth.etee.ra.aqdr._1_0.protocol.EHActorQualitiesDataRequest;
import be.fgov.ehealth.etee.ra.aqdr._1_0.protocol.EHActorQualitiesDataResponse;
import be.fgov.ehealth.etee.ra.aqdr._1_0.protocol.EntityType;
import be.fgov.ehealth.etee.ra.csr._1_0.protocol.ContactDataType;
import be.fgov.ehealth.etee.ra.csr._1_0.protocol.EHealthCertificateRequest;
import be.fgov.ehealth.etee.ra.csr._1_0.protocol.UsagesType;
import be.fgov.ehealth.etee.ra.revoke._1_0.protocol.RevocableCertificateType;
import be.fgov.ehealth.etee.ra.revoke._1_0.protocol.RevocableCertificatesDataRequest;
import be.fgov.ehealth.etee.ra.revoke._1_0.protocol.RevocableCertificatesDataResponse;
import be.fgov.ehealth.etee.ra.revoke._1_0.protocol.RevokeDataRequest;
import be.fgov.ehealth.technicalconnector.ra.domain.ContactData;
import be.fgov.ehealth.technicalconnector.ra.domain.NewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.RenewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.domain.RevokeCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.enumaration.Status;
import be.fgov.ehealth.technicalconnector.ra.enumaration.UsageType;
import be.fgov.ehealth.technicalconnector.ra.exceptions.RaException;
import be.fgov.ehealth.technicalconnector.ra.service.AuthenticationCertificateRegistrationService;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import be.fgov.ehealth.technicalconnector.ra.utils.RaUtils;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationCertificateRegistrationServiceImpl implements AuthenticationCertificateRegistrationService, ConfigurableImplementation, SessionServiceWithCache {
   private static final CertificateFactory CF;
   private static final Logger LOG;
   private Credential cred;
   private static Cache<String, byte[]> cacheQualities;
   private static Cache<String, byte[]> cacheRevokables;
   private static Map<String, List<X509Certificate>> cacheCertList;

   public String request(NewCertificateContract contract) throws TechnicalConnectorException {
      Validate.isTrue(contract.isContractViewed());
      EHealthCertificateRequest ehcsr = new EHealthCertificateRequest();
      ehcsr.setCsr(contract.getPkcs10DerEncoded());
      ehcsr.setContract(contract.getContract());
      ContactDataType contactDataType = map(contract.getContact());
      ehcsr.setContactData(contactDataType);
      if (null == ehcsr.getUsagesType()) {
         ehcsr.setUsagesType(new UsagesType());
      }

      Iterator i$ = contract.getUsageTypes().iterator();

      while(i$.hasNext()) {
         UsageType etkUsageType = (UsageType)i$.next();
         ehcsr.getUsagesType().getUsageTypes().add(etkUsageType.getType());
      }

      String nationalNumber = contract.getRequestor().getNationalNumber();
      if (contract.getDistinguishedName().isNaturalPerson()) {
         ehcsr.setEHActorQualitiesSignedData(this.getQualities(nationalNumber, EntityType.NATURAL));
      } else {
         if (!cacheQualities.containsKey(nationalNumber)) {
            cacheQualities.put(nationalNumber, this.getQualities(nationalNumber, EntityType.LEGAL));
         }

         ehcsr.setEHActorQualitiesSignedData((byte[])cacheQualities.get(nationalNumber));
      }

      if (LOG.isDebugEnabled()) {
         ConnectorXmlUtils.dump(ehcsr);
      }

      Result response;
      if (contract instanceof RenewCertificateContract) {
         GenerateCertificateForRenewalRequest payload = new GenerateCertificateForRenewalRequest();
         payload.setEhcsr(RaUtils.transform(this.cred, ehcsr, EHealthCertificateRequest.class));
         response = RaUtils.invokeCertRa(payload, "urn:be:fgov:ehealth:etee:certra:renewcertificate", GenerateCertificateForRenewalResponse.class);
         return ((GenerateCertificateForRenewalResponse)response.getResult()).getRequestId();
      } else {
         GenerateCertificateRequest payload = new GenerateCertificateRequest();
         payload.setEhcsr(RaUtils.transform(this.cred, ehcsr, EHealthCertificateRequest.class));
         response = RaUtils.invokeCertRa(payload, "urn:be:fgov:ehealth:etee:certra:generatecertificate", GenerateCertificateResponse.class);
         return ((GenerateCertificateResponse)response.getResult()).getRequestId();
      }
   }

   public String renew(RenewCertificateContract contract) throws TechnicalConnectorException {
      return this.request(contract);
   }

   public Result<Void> revoke(RevokeCertificateContract contract) throws TechnicalConnectorException {
      Validate.isTrue(contract.isContractViewed());
      String nationalNumber = BeIDInfo.getInstance().getIdentity().getNationalNumber();
      if (!cacheRevokables.containsKey(nationalNumber)) {
         cacheRevokables.put(nationalNumber, this.getRevocableCertificates(nationalNumber));
      }

      byte[] revocableCertificatesDataSignedResponse = (byte[])cacheRevokables.get(nationalNumber);
      RevocableCertificatesDataResponse response = (RevocableCertificatesDataResponse)RaUtils.transform(revocableCertificatesDataSignedResponse, RevocableCertificatesDataResponse.class);
      List<RevocableCertificateType> revocables = new ArrayList();
      revocables.addAll(response.getRevocablePersonalCertificates());
      revocables.addAll(response.getRevocableOrganizationCertificates());
      String requestId = null;
      Iterator i$ = revocables.iterator();

      while(i$.hasNext()) {
         RevocableCertificateType revocable = (RevocableCertificateType)i$.next();
         X509Certificate cert = contract.getX509Certificate();
         if (revocable.getAuthSerial().equals(cert.getSerialNumber().toString(10)) && cert.getIssuerX500Principal().equals(new X500Principal(revocable.getIssuerDN()))) {
            requestId = revocable.getRequestId();
            break;
         }
      }

      Validate.notNull(requestId);
      RevokeDataRequest revokeDataRequest = new RevokeDataRequest();
      revokeDataRequest.setRequestId(requestId);
      revokeDataRequest.setContract(contract.getContract());
      revokeDataRequest.setRevocableCertificatesDataSignedResponse(revocableCertificatesDataSignedResponse);
      RevokeRequest revokeReq = new RevokeRequest();
      revokeReq.setRevokeDataRequest(RaUtils.transform(this.cred, revokeDataRequest, RevokeDataRequest.class));
      Result<RevokeResponse> revokeResponse = RaUtils.invokeCertRa(revokeReq, "urn:be:fgov:ehealth:etee:certra:revokecertificate", RevokeResponse.class);
      return revokeResponse.hasStatusError() ? new Result("Unable to revoke certificate", revokeResponse.getCause()) : new Result((Void)null);
   }

   public Result<X509Certificate[]> poll(String requestId) throws TechnicalConnectorException {
      X509Certificate[] cert = new X509Certificate[0];
      GetCertificateRequest request = new GetCertificateRequest();
      request.setRequestId(requestId);
      Result<GetCertificateResponse> resp = RaUtils.invokeCertRa(request, "urn:be:fgov:ehealth:etee:certra:getcertificate", GetCertificateResponse.class);
      if (!resp.getStatus().equals(Status.OK)) {
         return resp.getStatus().equals(Status.PENDING) ? new Result(resp.getTime()) : new Result("Unable to obtain certificate", resp.getCause());
      } else {
         cert = (X509Certificate[])((X509Certificate[])ArrayUtils.add(cert, CertificateUtils.toX509Certificate(((GetCertificateResponse)resp.getResult()).getCertificate())));

         byte[] cacert;
         for(Iterator i$ = ((GetCertificateResponse)resp.getResult()).getCaCertificates().iterator(); i$.hasNext(); cert = (X509Certificate[])((X509Certificate[])ArrayUtils.add(cert, CertificateUtils.toX509Certificate(cacert)))) {
            cacert = (byte[])i$.next();
         }

         return new Result(cert);
      }
   }

   public Result<List<X509Certificate>> getRevokableCertificates(Identity identity) throws TechnicalConnectorException {
      String nationalNumber = identity.getNationalNumber();
      if (!cacheRevokables.containsKey(nationalNumber)) {
         cacheRevokables.put(nationalNumber, this.getRevocableCertificates(nationalNumber));
      }

      byte[] revocableCertificatesDataSignedResponse = (byte[])cacheRevokables.get(nationalNumber);
      if (!cacheCertList.containsKey(nationalNumber)) {
         cacheCertList.put(nationalNumber, this.convert(revocableCertificatesDataSignedResponse));
      }

      return new Result(cacheCertList.get(nationalNumber));
   }

   private List<X509Certificate> convert(byte[] revocableCertificatesDataSignedResponse) throws RaException, TechnicalConnectorException {
      RevocableCertificatesDataResponse response = (RevocableCertificatesDataResponse)RaUtils.transform(revocableCertificatesDataSignedResponse, RevocableCertificatesDataResponse.class);
      List<RevocableCertificateType> revocables = new ArrayList();
      revocables.addAll(response.getRevocablePersonalCertificates());
      revocables.addAll(response.getRevocableOrganizationCertificates());
      List<X509Certificate> result = new ArrayList();
      Iterator i$ = revocables.iterator();

      while(i$.hasNext()) {
         RevocableCertificateType revocable = (RevocableCertificateType)i$.next();

         try {
            X509Certificate[] certs = (X509Certificate[])this.poll(revocable.getRequestId()).getResult();
            CertPath certChain = CF.generateCertPath(Arrays.asList(certs));
            result.add((X509Certificate)certChain.getCertificates().get(0));
         } catch (CertificateException var9) {
            LOG.error("Unable to add revocable certificate", var9);
         }
      }

      return result;
   }

   private byte[] getRevocableCertificates(String nationalNumber) throws TechnicalConnectorException {
      RevocableCertificatesDataRequest data = new RevocableCertificatesDataRequest();
      data.setSSIN(nationalNumber);
      GetRevocableCertificatesRequest request = new GetRevocableCertificatesRequest();
      request.setRevocableCertificatesDataRequest(RaUtils.transform(this.cred, data, RevocableCertificatesDataRequest.class));
      return ((GetRevocableCertificatesResponse)RaUtils.invokeCertRa(request, "urn:be:fgov:ehealth:etee:certra:getrevocablecertificates", GetRevocableCertificatesResponse.class).getResult()).getRevocableCertificatesDataResponse();
   }

   public Result<List<Organization>> getOrganizationList(Identity identity) throws TechnicalConnectorException {
      String nationalNumber = identity.getNationalNumber();
      if (!cacheQualities.containsKey(nationalNumber)) {
         cacheQualities.put(nationalNumber, this.getQualities(nationalNumber, EntityType.LEGAL));
      }

      byte[] getQualityResponse = (byte[])cacheQualities.get(nationalNumber);
      EHActorQualitiesDataResponse response = (EHActorQualitiesDataResponse)RaUtils.transform(getQualityResponse, EHActorQualitiesDataResponse.class);
      List<Organization> orgList = new ArrayList();
      Iterator i$ = response.getLegalPerson().getOrganizations().iterator();

      while(i$.hasNext()) {
         be.fgov.ehealth.etee.ra.aqdr._1_0.protocol.Organization org = (be.fgov.ehealth.etee.ra.aqdr._1_0.protocol.Organization)i$.next();
         org.getIdentifier().getID();
         org.getIdentifier().getType();
         orgList.add(new Organization(org.getIdentifier().getID(), IdentifierType.lookup(org.getIdentifier().getType(), (String)null, 48), org.getNameNl()));
      }

      return new Result(orgList);
   }

   public Result<List<String>> getApplicationIdList(Organization organization) throws TechnicalConnectorException {
      GetExistingApplicationIdsRequest req = new GetExistingApplicationIdsRequest();
      SearchCriteriumType criteria = new SearchCriteriumType();
      criteria.setValue(organization.getId());
      criteria.setType(organization.getType().getType(48));
      req.setSearchCriterium(criteria);
      Result<GetExistingApplicationIdsResponse> response = RaUtils.invokeCertRa(req, "urn:be:fgov:ehealth:etee:certra:getexistingapplicationids", GetExistingApplicationIdsResponse.class);
      List<String> result = new ArrayList();
      Iterator i$ = ((GetExistingApplicationIdsResponse)response.getResult()).getSearchCriteria().iterator();

      while(i$.hasNext()) {
         SearchCriteriumType crit = (SearchCriteriumType)i$.next();
         result.add(crit.getApplicationID());
      }

      return new Result(result);
   }

   private byte[] getQualities(String nationalnumber, EntityType entity) throws TechnicalConnectorException {
      EHActorQualitiesDataRequest dataRequest = new EHActorQualitiesDataRequest();
      dataRequest.setSSIN(nationalnumber);
      dataRequest.setEntityType(entity);
      GetEHActorQualitiesRequest payload = new GetEHActorQualitiesRequest();
      payload.setEHActorQualitiesDataRequest(RaUtils.transform(this.cred, dataRequest, EHActorQualitiesDataRequest.class));
      Result<GetEHActorQualitiesResponse> response = RaUtils.invokeCertRa(payload, "urn:be:fgov:ehealth:etee:certra:getehactorqualities", GetEHActorQualitiesResponse.class);
      return ((GetEHActorQualitiesResponse)response.getResult()).getEHActorQualitiesDataResponse();
   }

   private static ContactDataType map(ContactData contactData) {
      ContactDataType contactDataType = new ContactDataType();
      contactDataType.setEmailPrivate(contactData.getEmailPrivate());
      contactDataType.setEmailGeneral(contactData.getEmailGeneral());
      contactDataType.setPhonePrivate(contactData.getPhonePrivate());
      contactDataType.setPhoneGeneral(contactData.getPhoneGeneral());
      return contactDataType;
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.cred = BeIDCredential.getInstance("CertRA", "Signature");
   }

   public void flushCache() {
      cacheQualities.clear();
   }

   static {
      try {
         CF = CertificateFactory.getInstance("X.509", "BC");
      } catch (NoSuchProviderException var1) {
         throw new IllegalArgumentException(var1);
      } catch (CertificateException var2) {
         throw new IllegalArgumentException(var2);
      }

      LOG = LoggerFactory.getLogger(AuthenticationCertificateRegistrationServiceImpl.class);
      cacheQualities = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);
      cacheRevokables = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);
      cacheCertList = new HashMap();
   }
}
