package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TargetType",
   propOrder = {"targetType", "targetId"}
)
public class TargetType {
   @XmlElement(
      name = "TargetType",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String targetType;
   @XmlElement(
      name = "TargetId",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String targetId;

   public String getTargetType() {
      return this.targetType;
   }

   public void setTargetType(String value) {
      this.targetType = value;
   }

   public String getTargetId() {
      return this.targetId;
   }

   public void setTargetId(String value) {
      this.targetId = value;
   }
}
