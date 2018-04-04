package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AgreementTermType",
   propOrder = {"value"}
)
public class AgreementTermType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected BigInteger value;
   @XmlAttribute(
      name = "unit",
      required = true
   )
   protected ValidityPeriodUnit unit;

   public BigInteger getValue() {
      return this.value;
   }

   public void setValue(BigInteger value) {
      this.value = value;
   }

   public ValidityPeriodUnit getUnit() {
      return this.unit;
   }

   public void setUnit(ValidityPeriodUnit value) {
      this.unit = value;
   }
}
