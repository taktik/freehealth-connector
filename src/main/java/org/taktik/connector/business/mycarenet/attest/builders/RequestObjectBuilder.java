package org.taktik.connector.business.mycarenet.attest.builders;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Ssin;
import org.taktik.connector.business.mycarenet.attest.domain.InputReference;
import org.taktik.connector.business.mycarenet.attest.exception.AttestBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.joda.time.DateTime;

public interface RequestObjectBuilder {
   SendAttestationRequest buildSendAttestationRequest(boolean var1, InputReference var2, Ssin var3, DateTime var4, Kmehrmessage var5) throws TechnicalConnectorException, AttestBusinessConnectorException, JAXBException, TransformerException, UnsupportedEncodingException;
}
