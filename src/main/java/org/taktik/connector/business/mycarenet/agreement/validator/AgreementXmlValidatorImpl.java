package org.taktik.connector.business.mycarenet.agreement.validator;

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import org.taktik.connector.technical.validator.impl.XMLValidatorImpl;

public class AgreementXmlValidatorImpl extends XMLValidatorImpl {
   private static final String COMMONS_CORE_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd";
   private static final String COMMONS_PROTOCOL_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd";
   private static final String AGREEMENT_PROTOCOL_XSD = "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd";

   public AgreementXmlValidatorImpl() {
   }

   static {
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(SendRequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(AskAgreementRequest.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(AskAgreementResponse.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(ConsultAgreementRequest.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(ConsultAgreementResponse.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(SendResponseType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(BlobType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(CommonInputType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(RequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(RoutingType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XMLValidatorImpl.Companion.putXsdFileLocationForXmlObject(OriginType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
   }
}
