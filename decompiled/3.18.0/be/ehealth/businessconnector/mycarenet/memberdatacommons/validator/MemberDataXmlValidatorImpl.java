package be.ehealth.businessconnector.mycarenet.memberdatacommons.validator;

import be.ehealth.technicalconnector.validator.impl.XMLValidatorImpl;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;

public class MemberDataXmlValidatorImpl extends XMLValidatorImpl {
   private static final String COMMONS_CORE_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd";
   private static final String COMMONS_PROTOCOL_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd";
   private static final String MEMBERDATASYNC_PROTOCOL_XSD = "/ehealth-mycarenet-memberdata/XSD/mycarenet-memberdata-protocol-1_0.xsd";

   static {
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(SendRequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(MemberDataConsultationRequest.class, "/ehealth-mycarenet-memberdata/XSD/mycarenet-memberdata-protocol-1_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(MemberDataConsultationResponse.class, "/ehealth-mycarenet-memberdata/XSD/mycarenet-memberdata-protocol-1_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(SendResponseType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(BlobType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(CommonInputType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(RequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(RoutingType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(OriginType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-3_0.xsd");
   }
}
