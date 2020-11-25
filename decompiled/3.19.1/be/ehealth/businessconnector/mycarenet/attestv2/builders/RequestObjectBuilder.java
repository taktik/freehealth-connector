package be.ehealth.businessconnector.mycarenet.attestv2.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.CancelAttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.SendAttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.Kmehrmessage;
import org.joda.time.DateTime;

public interface RequestObjectBuilder {
   SendAttestBuilderRequest buildSendAttestationRequest(boolean var1, InputReference var2, Ssin var3, DateTime var4, Kmehrmessage var5) throws AttestBusinessConnectorException, TechnicalConnectorException;

   CancelAttestBuilderRequest buildCancelAttestationRequest(boolean var1, InputReference var2, Ssin var3, DateTime var4, Kmehrmessage var5) throws AttestBusinessConnectorException, TechnicalConnectorException;
}
