package be.ehealth.businessconnector.tarification.builder.impl;

import be.ehealth.business.mycarenetcommons.builders.util.RequestBuilderUtil;
import be.ehealth.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.businessconnector.tarification.builder.RequestBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
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
