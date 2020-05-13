package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EntityIdType",
   propOrder = {"id", "version"}
)
public class EntityIdType extends AbstractEntityIdType {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String id;
   protected int version;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int value) {
      this.version = value;
   }
}
