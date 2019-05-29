package org.taktik.connector.business.mycarenet.attestv2.builders;

import org.taktik.connector.business.mycarenet.attestv2.domain.CancelAttestBuilderRequest;
import org.taktik.connector.business.mycarenet.attestv2.domain.InputReference;
import org.taktik.connector.business.mycarenet.attestv2.domain.SendAttestBuilderRequest;
import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import org.joda.time.DateTime;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Ssin;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;

public interface RequestObjectBuilder {
   SendAttestBuilderRequest buildSendAttestationRequest(boolean var1, InputReference var2, Ssin var3, DateTime var4, Kmehrmessage var5, Credential credential, String mcnLicense, String mcnPassword) throws AttestBusinessConnectorException, TechnicalConnectorException;

   CancelAttestBuilderRequest buildCancelAttestationRequest(boolean var1, InputReference var2, Ssin var3, DateTime var4, Kmehrmessage var5, String mcnLicense, String mcnPassword) throws AttestBusinessConnectorException, TechnicalConnectorException;
}
