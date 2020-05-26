package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TotalAmounts",
   propOrder = {"totalPublicPrice", "totalPaidPrice", "totalHomeopathie"}
)
public class TotalAmounts {
   @XmlElement(
      required = true
   )
   protected String totalPublicPrice;
   @XmlElement(
      required = true
   )
   protected String totalPaidPrice;
   @XmlElement(
      required = true,
      defaultValue = "0.00"
   )
   protected String totalHomeopathie;

   public String getTotalPublicPrice() {
      return this.totalPublicPrice;
   }

   public void setTotalPublicPrice(String value) {
      this.totalPublicPrice = value;
   }

   public String getTotalPaidPrice() {
      return this.totalPaidPrice;
   }

   public void setTotalPaidPrice(String value) {
      this.totalPaidPrice = value;
   }

   public String getTotalHomeopathie() {
      return this.totalHomeopathie;
   }

   public void setTotalHomeopathie(String value) {
      this.totalHomeopathie = value;
   }
}
