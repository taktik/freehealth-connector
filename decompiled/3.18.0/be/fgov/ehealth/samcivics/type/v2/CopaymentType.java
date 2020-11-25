package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CopaymentType",
   propOrder = {"regimeType", "copayAmnt", "solidFlatRateAmnt"}
)
public class CopaymentType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RegimeType",
      required = true
   )
   protected BigInteger regimeType;
   @XmlElement(
      name = "CopayAmnt"
   )
   protected AmountType copayAmnt;
   @XmlElement(
      name = "SolidFlatRateAmnt"
   )
   protected AmountType solidFlatRateAmnt;

   public BigInteger getRegimeType() {
      return this.regimeType;
   }

   public void setRegimeType(BigInteger value) {
      this.regimeType = value;
   }

   public AmountType getCopayAmnt() {
      return this.copayAmnt;
   }

   public void setCopayAmnt(AmountType value) {
      this.copayAmnt = value;
   }

   public AmountType getSolidFlatRateAmnt() {
      return this.solidFlatRateAmnt;
   }

   public void setSolidFlatRateAmnt(AmountType value) {
      this.solidFlatRateAmnt = value;
   }
}
