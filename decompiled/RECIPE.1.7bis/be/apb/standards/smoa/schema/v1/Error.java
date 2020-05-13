package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "error",
   propOrder = {"reference", "referenceType"}
)
public class Error extends AbstractStatus {
   @XmlElement(
      required = true
   )
   protected String reference;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected ReferenceType referenceType;

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }

   public ReferenceType getReferenceType() {
      return this.referenceType;
   }

   public void setReferenceType(ReferenceType value) {
      this.referenceType = value;
   }
}
