package org.taktik.connector.business.mycarenet.agreement.validator;

import be.ehealth.technicalconnector.validator.impl.XMLValidatorImpl;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;

public class AgreementXmlValidatorImpl extends XMLValidatorImpl {
   private static final String COMMONS_CORE_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd";
   private static final String COMMONS_PROTOCOL_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd";
   private static final String AGREEMENT_PROTOCOL_XSD = "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd";

   public AgreementXmlValidatorImpl() {
   }

   static {
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(SendRequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(AskAgreementRequest.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(AskAgreementResponse.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(ConsultAgreementRequest.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(ConsultAgreementResponse.class, "/ehealth-mycarenet-agreement/XSD/mycarenet-agreement-protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(SendResponseType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(BlobType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(CommonInputType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(RequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(RoutingType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(OriginType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
   }
}
