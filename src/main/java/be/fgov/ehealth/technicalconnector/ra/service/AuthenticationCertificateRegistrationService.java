package be.fgov.ehealth.technicalconnector.ra.service;

import org.taktik.connector.technical.beid.domain.Identity;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.domain.NewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.RenewCertificateContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import be.fgov.ehealth.technicalconnector.ra.domain.RevokeCertificateContract;
import java.security.cert.X509Certificate;
import java.util.List;

public interface AuthenticationCertificateRegistrationService {
   String request(NewCertificateContract var1) throws TechnicalConnectorException;

   String renew(RenewCertificateContract var1) throws TechnicalConnectorException;

   Result<Void> revoke(RevokeCertificateContract var1) throws TechnicalConnectorException;

   Result<X509Certificate[]> poll(String var1) throws TechnicalConnectorException;

   Result<List<Organization>> getOrganizationList(Identity var1) throws TechnicalConnectorException;

   Result<List<String>> getApplicationIdList(Organization var1) throws TechnicalConnectorException;

   Result<List<X509Certificate>> getRevokableCertificates(Identity var1) throws TechnicalConnectorException;
}
