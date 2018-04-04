package org.taktik.connector.business.registration.builder;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;

public interface RequestBuilder {
   RegisterToMycarenetServiceRequest buildRegisterToMycarenetRequest(CommonInput var1, Routing var2, Blob var3, byte[] var4) throws TechnicalConnectorException;
}
