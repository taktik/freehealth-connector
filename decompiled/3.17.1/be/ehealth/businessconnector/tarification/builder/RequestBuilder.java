package be.ehealth.businessconnector.tarification.builder;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;

public interface RequestBuilder {
   TarificationConsultationRequest buildConsultationRequest(CommonInput var1, Routing var2, Blob var3) throws TechnicalConnectorException;

   TarificationConsultationRequest buildConsultationRequest(Routing var1, byte[] var2, String var3) throws TechnicalConnectorException;
}
