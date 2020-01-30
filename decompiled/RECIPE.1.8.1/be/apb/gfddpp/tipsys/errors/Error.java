package be.apb.gfddpp.tipsys.errors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "error",
   propOrder = {"reference", "referenceType", "code", "message"}
)
public class Error {
   @XmlElement(
      required = true
   )
   protected String reference;
   @XmlElement(
      name = "ReferenceType",
      required = true
   )
   protected String referenceType;
   @XmlElement(
      required = true
   )
   protected String code;
   @XmlElement(
      required = true
   )
   protected String message;

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }

   public String getReferenceType() {
      return this.referenceType;
   }

   public void setReferenceType(String value) {
      this.referenceType = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String value) {
      this.message = value;
   }
}
