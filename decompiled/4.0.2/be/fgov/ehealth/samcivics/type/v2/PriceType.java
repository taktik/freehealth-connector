package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PriceType",
   propOrder = {"priceAmnt", "reimbBasePrice", "referenceBasePrice"}
)
public class PriceType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PriceAmnt"
   )
   protected AmountType priceAmnt;
   @XmlElement(
      name = "ReimbBasePrice"
   )
   protected AmountType reimbBasePrice;
   @XmlElement(
      name = "ReferenceBasePrice"
   )
   protected AmountType referenceBasePrice;

   public PriceType() {
   }

   public AmountType getPriceAmnt() {
      return this.priceAmnt;
   }

   public void setPriceAmnt(AmountType value) {
      this.priceAmnt = value;
   }

   public AmountType getReimbBasePrice() {
      return this.reimbBasePrice;
   }

   public void setReimbBasePrice(AmountType value) {
      this.reimbBasePrice = value;
   }

   public AmountType getReferenceBasePrice() {
      return this.referenceBasePrice;
   }

   public void setReferenceBasePrice(AmountType value) {
      this.referenceBasePrice = value;
   }
}
