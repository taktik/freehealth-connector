package be.fgov.ehealth.dics.core.v4.reimbursement.submit;

import be.fgov.ehealth.dics.protocol.v4.ConsultCopaymentType;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CopaymentKeyType"
)
@XmlSeeAlso({ConsultCopaymentType.class})
public class CopaymentKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "RegimeType",
      required = true
   )
   protected BigInteger regimeType;

   public CopaymentKeyType() {
   }

   public BigInteger getRegimeType() {
      return this.regimeType;
   }

   public void setRegimeType(BigInteger value) {
      this.regimeType = value;
   }
}
