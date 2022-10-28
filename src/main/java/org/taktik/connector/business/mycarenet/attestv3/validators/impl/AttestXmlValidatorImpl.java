package org.taktik.connector.business.mycarenet.attestv3.validators.impl;

import org.taktik.connector.technical.validator.impl.XMLValidatorImpl;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;

public class AttestXmlValidatorImpl extends XMLValidatorImpl {
   private static final String COMMONS_CORE_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-4_0.xsd";
   private static final String COMMONS_PROTOCOL_XSD = "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-4_0.xsd";
   private static final String ATTEST_PROTOCOL_XSD = "/ehealth-mycarenet-attest/XSD/mycarenet-attest-protocol-3_0.xsd";

   static {
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(SendRequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-4_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(SendAttestationRequest.class, "/ehealth-mycarenet-attest/XSD/mycarenet-attest-protocol-3_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(SendAttestationResponse.class, "/ehealth-mycarenet-attest/XSD/mycarenet-attest-protocol-3_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(CancelAttestationRequest.class, "/ehealth-mycarenet-attest/XSD/mycarenet-attest-protocol-3_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(CancelAttestationResponse.class, "/ehealth-mycarenet-attest/XSD/mycarenet-attest-protocol-3_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(SendResponseType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-protocol-4_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(BlobType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-4_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(CommonInputType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-4_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(RequestType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-4_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(RoutingType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-4_0.xsd");
      Companion.getXSD_FILE_LOCATION_FOR_CLASS_MAP().put(OriginType.class, "/ehealth-mycarenetcommons/XSD/mycarenet-commons-core-4_0.xsd");
   }
}
