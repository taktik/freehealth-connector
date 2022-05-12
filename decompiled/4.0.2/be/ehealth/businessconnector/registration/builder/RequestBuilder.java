package be.ehealth.businessconnector.registration.builder;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;

public interface RequestBuilder {
   RegisterToMycarenetServiceRequest buildRegisterToMycarenetRequest(CommonInput var1, Routing var2, Blob var3, byte[] var4) throws TechnicalConnectorException;
}
