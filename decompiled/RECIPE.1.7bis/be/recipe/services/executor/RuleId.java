package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ruleId",
   propOrder = {"value"}
)
public class RuleId {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "IdType"
   )
   protected String idType;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getIdType() {
      return this.idType;
   }

   public void setIdType(String value) {
      this.idType = value;
   }
}
