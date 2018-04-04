package be.fgov.ehealth.chap4.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AskChap4MedicalAdvisorAgreementRequestType"
)
@XmlRootElement(
   name = "AskChap4MedicalAdvisorAgreementRequest"
)
public class AskChap4MedicalAdvisorAgreementRequest extends AbstractChap4MedicalAdvisorAgreementRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
