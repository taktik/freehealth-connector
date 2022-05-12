package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AllowedSupplementsType",
   propOrder = {"amountMaxAllowed", "supplementTypeList"}
)
public class AllowedSupplementsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AmountMaxAllowed"
   )
   protected Integer amountMaxAllowed;
   @XmlElement(
      name = "SupplementTypeList"
   )
   protected SupplementTypeListType supplementTypeList;

   public AllowedSupplementsType() {
   }

   public Integer getAmountMaxAllowed() {
      return this.amountMaxAllowed;
   }

   public void setAmountMaxAllowed(Integer value) {
      this.amountMaxAllowed = value;
   }

   public SupplementTypeListType getSupplementTypeList() {
      return this.supplementTypeList;
   }

   public void setSupplementTypeList(SupplementTypeListType value) {
      this.supplementTypeList = value;
   }
}
