package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GivenNameType",
   propOrder = {"value"}
)
public class GivenNameType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Sequence"
   )
   protected Integer sequence;

   public GivenNameType() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public Integer getSequence() {
      return this.sequence;
   }

   public void setSequence(Integer value) {
      this.sequence = value;
   }
}
