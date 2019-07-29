package be.fgov.ehealth.chap4.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultChap4MedicalAdvisorAgreementResponseType"
)
@XmlRootElement(
   name = "ConsultChap4MedicalAdvisorAgreementResponse"
)
public class ConsultChap4MedicalAdvisorAgreementResponse extends AbstractChap4MedicalAdvisorAgreementResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
}
