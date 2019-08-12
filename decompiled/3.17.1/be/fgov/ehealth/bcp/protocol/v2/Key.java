package be.fgov.ehealth.bcp.protocol.v2;

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
public class Key implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Tranform",
      required = true
   )
   protected String tranform;
   @XmlAttribute(
      name = "Inline",
      required = true
   )
   protected boolean inline;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getTranform() {
      return this.tranform;
   }

   public void setTranform(String value) {
      this.tranform = value;
   }

   public boolean isInline() {
      return this.inline;
   }

   public void setInline(boolean value) {
      this.inline = value;
   }
}
