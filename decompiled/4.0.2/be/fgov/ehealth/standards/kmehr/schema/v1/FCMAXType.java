package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FCMAXType",
   propOrder = {"value"}
)
public class FCMAXType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "TIME"
   )
   protected String time;

   public FCMAXType() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getTIME() {
      return this.time;
   }

   public void setTIME(String value) {
      this.time = value;
   }
}
