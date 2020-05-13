package be.ehealth.businessconnector.dmg.validators.impl;

import be.ehealth.technicalconnector.validator.impl.XMLValidatorImpl;
import be.fgov.ehealth.globalmedicalfile.core.v1.BlobType;
import be.fgov.ehealth.globalmedicalfile.core.v1.CommonInputType;
import be.fgov.ehealth.globalmedicalfile.core.v1.OriginType;
import be.fgov.ehealth.globalmedicalfile.core.v1.RequestType;
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.ConsultGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.NotifyGlobalMedicalFileRequest;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendRequestType;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendResponseType;

public class DmgXmlValidatorImpl extends XMLValidatorImpl {
   private static final String XSD_GMF_V1_GMF_SERVICES_CORE_XSD = "/ehealth-gmf/XSD/gmf_services_core-1_1.xsd";
   private static final String XSD_GMF_V1_GMF_SERVICES_PROTOCOL_XSD = "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd";

   static {
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(SendRequestType.class, "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(ConsultGlobalMedicalFileRequest.class, "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(NotifyGlobalMedicalFileRequest.class, "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(SendResponseType.class, "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(BlobType.class, "/ehealth-gmf/XSD/gmf_services_core-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(CommonInputType.class, "/ehealth-gmf/XSD/gmf_services_core-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(RequestType.class, "/ehealth-gmf/XSD/gmf_services_core-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(RoutingType.class, "/ehealth-gmf/XSD/gmf_services_core-1_1.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(OriginType.class, "/ehealth-gmf/XSD/gmf_services_core-1_1.xsd");
   }
}
