package org.taktik.connector.business.dmg.builders;

import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.dmg.domain.DMGReferences;
import org.taktik.connector.business.dmg.exception.DmgBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
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
