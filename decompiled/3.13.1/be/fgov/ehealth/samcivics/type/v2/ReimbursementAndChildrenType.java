package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementAndChildrenType",
   propOrder = {"price", "ampp", "copayments"}
)
public class ReimbursementAndChildrenType extends ReimbursementType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Price",
      required = true
   )
   protected PriceType price;
   @XmlElement(
      name = "Ampp",
      required = true
   )
   protected AmppType ampp;
   @XmlElement(
      name = "Copayment"
   )
   protected List<CopaymentType> copayments;

   public PriceType getPrice() {
      return this.price;
   }

   public void setPrice(PriceType value) {
      this.price = value;
   }

   public AmppType getAmpp() {
      return this.ampp;
   }

   public void setAmpp(AmppType value) {
      this.ampp = value;
   }

   public List<CopaymentType> getCopayments() {
      if (this.copayments == null) {
         this.copayments = new ArrayList();
      }

      return this.copayments;
   }
}
