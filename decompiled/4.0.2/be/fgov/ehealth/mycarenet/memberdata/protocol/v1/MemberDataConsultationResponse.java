package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendResponseMemberDataType"
)
@XmlRootElement(
   name = "MemberDataConsultationResponse"
)
public class MemberDataConsultationResponse extends SendResponseType implements Serializable {
   private static final long serialVersionUID = 1L;

   public MemberDataConsultationResponse() {
   }
}
