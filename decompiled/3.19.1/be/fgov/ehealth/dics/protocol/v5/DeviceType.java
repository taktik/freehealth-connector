package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"name", "edqmCode", "edqmDefinition"}
)
public class DeviceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "EdqmCode"
   )
   protected String edqmCode;
   @XmlElement(
      name = "EdqmDefinition"
   )
   protected String edqmDefinition;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public String getEdqmCode() {
      return this.edqmCode;
   }

   public void setEdqmCode(String value) {
      this.edqmCode = value;
   }

   public String getEdqmDefinition() {
      return this.edqmDefinition;
   }

   public void setEdqmDefinition(String value) {
      this.edqmDefinition = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
