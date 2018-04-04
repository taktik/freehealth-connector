package be.apb.gfddpp.productfilter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "productList",
   propOrder = {"whiteList", "blackList"}
)
public class ProductList {
   @XmlElement(
      name = "white-list"
   )
   protected WhiteList whiteList;
   @XmlElement(
      name = "black-list"
   )
   protected BlackList blackList;

   public WhiteList getWhiteList() {
      return this.whiteList;
   }

   public void setWhiteList(WhiteList value) {
      this.whiteList = value;
   }

   public BlackList getBlackList() {
      return this.blackList;
   }

   public void setBlackList(BlackList value) {
      this.blackList = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"medicines", "preparations"}
   )
   public static class WhiteList {
      protected Medicines medicines;
      protected Preparations preparations;

      public Medicines getMedicines() {
         return this.medicines;
      }

      public void setMedicines(Medicines value) {
         this.medicines = value;
      }

      public Preparations getPreparations() {
         return this.preparations;
      }

      public void setPreparations(Preparations value) {
         this.preparations = value;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"ranges", "medicines", "preparations"}
   )
   public static class BlackList {
      protected Ranges ranges;
      protected Medicines medicines;
      protected Preparations preparations;

      public Ranges getRanges() {
         return this.ranges;
      }

      public void setRanges(Ranges value) {
         this.ranges = value;
      }

      public Medicines getMedicines() {
         return this.medicines;
      }

      public void setMedicines(Medicines value) {
         this.medicines = value;
      }

      public Preparations getPreparations() {
         return this.preparations;
      }

      public void setPreparations(Preparations value) {
         this.preparations = value;
      }
   }
}
