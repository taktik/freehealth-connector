package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityListType",
   propOrder = {"insurabilityItems"}
)
public class InsurabilityListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsurabilityItem"
   )
   protected List<InsurabilityItemType> insurabilityItems;
   @XmlAttribute(
      name = "Truncated"
   )
   protected Boolean truncated;

   public List<InsurabilityItemType> getInsurabilityItems() {
      if (this.insurabilityItems == null) {
         this.insurabilityItems = new ArrayList();
      }

      return this.insurabilityItems;
   }

   public Boolean isTruncated() {
      return this.truncated;
   }

   public void setTruncated(Boolean value) {
      this.truncated = value;
   }
}
