package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UpdateSignatureInstructionType"
)
@XmlSeeAlso({TimeSignatureInstructionType.class})
public class UpdateSignatureInstructionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Type"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;

   public UpdateSignatureInstructionType() {
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
