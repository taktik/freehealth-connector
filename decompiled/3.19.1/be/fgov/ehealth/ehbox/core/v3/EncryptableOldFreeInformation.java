package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"value"}
)
public class EncryptableOldFreeInformation implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected byte[] value;
   @XmlAttribute(
      name = "Render",
      required = true
   )
   protected String render;

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public String getRender() {
      return this.render;
   }

   public void setRender(String value) {
      this.render = value;
   }
}
