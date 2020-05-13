package be.fgov.ehealth.insurability.core.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RecordCommonOutputType",
   propOrder = {"reference", "ioReference", "userReference"}
)
public class RecordCommonOutputType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Reference"
   )
   protected BigDecimal reference;
   @XmlElement(
      name = "IoReference"
   )
   protected BigDecimal ioReference;
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

   public BigDecimal getIoReference() {
      return this.ioReference;
   }

   public void setIoReference(BigDecimal value) {
      this.ioReference = value;
   }

   public String getUserReference() {
      return this.userReference;
   }

   public void setUserReference(String value) {
      this.userReference = value;
   }
}
