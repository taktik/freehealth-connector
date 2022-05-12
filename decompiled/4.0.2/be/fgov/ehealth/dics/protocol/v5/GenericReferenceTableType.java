package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericReferenceTableType",
   propOrder = {"genericReferenceEntry"}
)
public class GenericReferenceTableType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenericReferenceEntry",
      required = true
   )
   protected GenericReferenceEntryType genericReferenceEntry;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String name;

   public GenericReferenceTableType() {
   }

   public GenericReferenceEntryType getGenericReferenceEntry() {
      return this.genericReferenceEntry;
   }

   public void setGenericReferenceEntry(GenericReferenceEntryType value) {
      this.genericReferenceEntry = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
