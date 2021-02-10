package be.ehealth.businessconnector.mycarenet.attest.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import be.ehealth.businessconnector.mycarenet.attest.domain.AttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attest.domain.InputReference;
import be.ehealth.businessconnector.mycarenet.attest.exception.AttestBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.joda.time.DateTime;

public interface RequestObjectBuilder {
   AttestBuilderRequest buildSendAttestationRequest(boolean var1, InputReference var2, Ssin var3, DateTime var4, Kmehrmessage var5) throws TechnicalConnectorException, AttestBusinessConnectorException, JAXBException, TransformerException, UnsupportedEncodingException;
}
