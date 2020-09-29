package be.apb.gfddpp.rtrn.registerdata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dispensation",
   propOrder = {"localReference", "dguid", "product", "magistral"}
)
public class Dispensation {
   @XmlElement(
      required = true
   )
   protected String localReference;
   @XmlElement(
      required = true
   )
   protected String dguid;
   protected Product product;
   protected Magistral magistral;

   public String getLocalReference() {
      return this.localReference;
   }

   public void setLocalReference(String value) {
      this.localReference = value;
   }

   public String getDguid() {
      return this.dguid;
   }

   public void setDguid(String value) {
      this.dguid = value;
   }

   public Product getProduct() {
      return this.product;
   }

   public void setProduct(Product value) {
      this.product = value;
   }

   public Magistral getMagistral() {
      return this.magistral;
   }

   public void setMagistral(Magistral value) {
      this.magistral = value;
   }
}
