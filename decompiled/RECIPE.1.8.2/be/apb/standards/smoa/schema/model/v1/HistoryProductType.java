package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HistoryProductType"
)
public class HistoryProductType extends MinSetProductType {
   @XmlAttribute(
      name = "prescribed",
      required = true
   )
   protected boolean prescribed;
   @XmlAttribute(
      name = "onSubstanceName"
   )
   protected Boolean onSubstanceName;

   public boolean isPrescribed() {
      return this.prescribed;
   }

   public void setPrescribed(boolean value) {
      this.prescribed = value;
   }

   public Boolean isOnSubstanceName() {
      return this.onSubstanceName;
   }

   public void setOnSubstanceName(Boolean value) {
      this.onSubstanceName = value;
   }
}
