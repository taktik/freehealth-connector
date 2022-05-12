package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityListType",
   propOrder = {"insurabilityItems"}
)
@XmlRootElement(
   name = "InsurabilityList"
)
public class InsurabilityList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsurabilityItem"
   )
   protected List<InsurabilityItem> insurabilityItems;
   @XmlAttribute(
      name = "Truncated"
   )
   protected Boolean truncated;

   public InsurabilityList() {
   }

   public List<InsurabilityItem> getInsurabilityItems() {
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
