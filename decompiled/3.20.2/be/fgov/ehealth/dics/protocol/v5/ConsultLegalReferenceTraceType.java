package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultLegalReferenceTraceType"
)
public class ConsultLegalReferenceTraceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Key",
      required = true
   )
   protected String key;

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }
}
