package org.taktik.connector.business.registration.builder.impl;

import org.taktik.connector.business.mycarenetcommons.builders.util.RequestBuilderUtil;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.business.registration.builder.RequestBuilder;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;

public class RequestBuilderImpl implements RequestBuilder {
   public RegisterToMycarenetServiceRequest buildRegisterToMycarenetRequest(CommonInput commonInput, Routing routing, Blob blob, byte[] xadesValue) throws TechnicalConnectorException {
      RegisterToMycarenetServiceRequest request = new RegisterToMycarenetServiceRequest();
      RequestBuilderUtil.fillInputToMycarenetRequest(request, commonInput, routing, blob, xadesValue, "mcn.registration");
      return request;
   }
}
