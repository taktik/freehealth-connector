package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RecordCommonInputType",
   propOrder = {"inputReference"}
)
public class RecordCommonInputType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InputReference"
   )
   protected BigDecimal inputReference;

   public RecordCommonInputType() {
   }

   public BigDecimal getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(BigDecimal value) {
      this.inputReference = value;
   }
}
