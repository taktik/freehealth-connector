package org.w3._2001._04.xmlenc_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CipherDataType",
   propOrder = {"cipherValue", "cipherReference"}
)
public class CipherDataType {
   @XmlElement(
      name = "CipherValue"
   )
   protected byte[] cipherValue;
   @XmlElement(
      name = "CipherReference"
   )
   protected CipherReferenceType cipherReference;

   public byte[] getCipherValue() {
      return this.cipherValue;
   }

   public void setCipherValue(byte[] value) {
      this.cipherValue = value;
   }

   public CipherReferenceType getCipherReference() {
      return this.cipherReference;
   }

   public void setCipherReference(CipherReferenceType value) {
      this.cipherReference = value;
   }
}
