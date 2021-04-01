package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.refdata.PackagingClosureKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PackagingClosureType",
   propOrder = {"name", "edqmCode", "edqmDefinition"}
)
public class PackagingClosureType extends PackagingClosureKeyType implements Serializable {
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
}
