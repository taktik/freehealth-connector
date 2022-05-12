package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.reimbursementlaw.submit.LegalReferenceKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultLegalReferenceTraceType"
)
public class ConsultLegalReferenceTraceType extends LegalReferenceKeyType implements Serializable {
   private static final long serialVersionUID = 1L;

   public ConsultLegalReferenceTraceType() {
   }
}
