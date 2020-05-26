package be.fgov.ehealth.insurability.core.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RecordCommonInputType",
   propOrder = {"reference", "userReference"}
)
public class RecordCommonInputType {
   @XmlElement(
      name = "Reference"
   )
   protected BigDecimal reference;
   @XmlElement(
      name = "UserReference"
   )
   protected String userReference;

   public BigDecimal getReference() {
      return this.reference;
   }

   public void setReference(BigDecimal value) {
      this.reference = value;
   }

   public String getUserReference() {
      return this.userReference;
   }

   public void setUserReference(String value) {
      this.userReference = value;
   }
}
