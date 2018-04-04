package org.taktik.connector.business.tarification.builder.impl;

import org.taktik.connector.business.mycarenetcommons.builders.util.RequestBuilderUtil;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil;
import org.taktik.connector.business.tarification.builder.RequestBuilder;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;

public class RequestBuilderImpl implements RequestBuilder {
   private static final String PROJECT_IDENTIFIER = "mcn.tarification";

   public TarificationConsultationRequest buildConsultationRequest(CommonInput commonInput, Routing routing, Blob blob) throws TechnicalConnectorException {
      TarificationConsultationRequest request = new TarificationConsultationRequest();
      RequestBuilderUtil.fillInputToMycarenetRequest(request, commonInput, routing, blob, (byte[])null, "mcn.tarification");
      return request;
   }

   public TarificationConsultationRequest buildConsultationRequest(Routing routing, byte[] content, String uniqueInputReference) throws TechnicalConnectorException {
      CommonInput commonInput = RequestBuilderFactory.getCommonBuilder("mcn.tarification").createCommonInput(McnConfigUtil.retrievePackageInfo("mcn.tarification"), true, uniqueInputReference);
      Blob blob = BlobBuilderFactory.getBlobBuilder("mcn.tarification").build(content);
      return this.buildConsultationRequest(commonInput, routing, blob);
   }
}
