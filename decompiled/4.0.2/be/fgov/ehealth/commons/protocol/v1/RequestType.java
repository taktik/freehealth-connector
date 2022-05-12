package be.fgov.ehealth.commons.protocol.v1;

import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType"
)
@XmlSeeAlso({AskChap4MedicalAdvisorAgreementRequest.class, ConsultChap4MedicalAdvisorAgreementRequest.class})
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;

   public RequestType() {
   }
}
