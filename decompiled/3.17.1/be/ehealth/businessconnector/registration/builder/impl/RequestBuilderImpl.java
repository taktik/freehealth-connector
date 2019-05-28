package be.ehealth.businessconnector.registration.builder.impl;

import be.ehealth.business.mycarenetcommons.builders.util.RequestBuilderUtil;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.businessconnector.registration.builder.RequestBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;

public class RequestBuilderImpl implements RequestBuilder {
   public RegisterToMycarenetServiceRequest buildRegisterToMycarenetRequest(CommonInput commonInput, Routing routing, Blob blob, byte[] xadesValue) throws TechnicalConnectorException {
      RegisterToMycarenetServiceRequest request = new RegisterToMycarenetServiceRequest();
      RequestBuilderUtil.fillInputToMycarenetRequest(request, commonInput, routing, blob, xadesValue, "mcn.registration");
      return request;
   }
}
