package be.fgov.ehealth.etee.commons.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EteeErrorParameterType",
   propOrder = {"name", "value"}
)
public class EteeErrorParameterType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected String value;

   public EteeErrorParameterType() {
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
