package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AllowedSupplementsType",
   propOrder = {"amountMaxAllowed", "allowedSupplementListType"}
)
public class AllowedSupplementsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AmountMaxAllowed"
   )
   protected Integer amountMaxAllowed;
   @XmlElement(
      name = "AllowedSupplementListType"
   )
   protected AllowedSupplementsListType allowedSupplementListType;

   public Integer getAmountMaxAllowed() {
      return this.amountMaxAllowed;
   }

   public void setAmountMaxAllowed(Integer value) {
      this.amountMaxAllowed = value;
   }

   public AllowedSupplementsListType getAllowedSupplementListType() {
      return this.allowedSupplementListType;
   }

   public void setAllowedSupplementListType(AllowedSupplementsListType value) {
      this.allowedSupplementListType = value;
   }
}
