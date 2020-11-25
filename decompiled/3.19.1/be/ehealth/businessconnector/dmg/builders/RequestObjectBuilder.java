package be.ehealth.businessconnector.dmg.builders;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.dmg.domain.DMGReferences;
import be.ehealth.businessconnector.dmg.exception.DmgBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.messageservices.core.v1.SelectRetrieveTransaction;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import org.joda.time.DateTime;

public interface RequestObjectBuilder {
   ConsultGlobalMedicalFileRequest buildSendConsultRequest(boolean var1, String var2, Patient var3, DateTime var4, Blob var5, byte[] var6) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException;

   ConsultGlobalMedicalFileRequest buildSendConsultRequest(boolean var1, DMGReferences var2, Patient var3, DateTime var4, SelectRetrieveTransaction var5) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException;

   NotifyGlobalMedicalFileRequest buildSendNotifyRequest(boolean var1, String var2, Patient var3, DateTime var4, Blob var5, byte[] var6) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException;

   NotifyGlobalMedicalFileRequest buildSendNotifyRequest(boolean var1, DMGReferences var2, Patient var3, DateTime var4, Kmehrmessage var5) throws TechnicalConnectorException, DmgBusinessConnectorException, InstantiationException;
}
