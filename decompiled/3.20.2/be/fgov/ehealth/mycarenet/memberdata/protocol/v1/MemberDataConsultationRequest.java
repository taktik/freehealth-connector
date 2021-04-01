package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendRequestMemberDataType"
)
@XmlRootElement(
   name = "MemberDataConsultationRequest"
)
public class MemberDataConsultationRequest extends SendRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
