package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalIdType",
   propOrder = {"id"}
)
public class LocalIdType extends AbstractPersonIdType {
   @XmlElement(
      required = true
   )
   protected String id;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
