package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   namespace = "urn:be:fgov:ehealth:commons:protocol:v1"
)
@XmlSeeAlso({RoutedSealedRequestType.class, UploadPerformanceMetricRequestType.class, SealedMessageRequestType.class, SealedRequestType.class, CheckAliveRequestType.class})
public class RequestType {
}
