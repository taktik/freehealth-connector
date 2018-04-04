package be.fgov.ehealth.commons._1_0.protocol;

import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequest;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkRequest;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType"
)
@XmlSeeAlso({GetEtkRequest.class, GetKeyRequest.class, GetNewKeyRequest.class})
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
