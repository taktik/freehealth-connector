package org.taktik.connector.business.tarification.builder;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;

public interface RequestBuilder {
   TarificationConsultationRequest buildConsultationRequest(CommonInput var1, Routing var2, Blob var3) throws TechnicalConnectorException;

   TarificationConsultationRequest buildConsultationRequest(Routing var1, byte[] var2, String var3) throws TechnicalConnectorException;
}
