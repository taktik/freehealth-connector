package org.w3._2001._04.xmlenc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CipherDataType",
   propOrder = {"cipherReference", "cipherValue"}
)
@XmlRootElement(
   name = "CipherData"
)
public class CipherData {
   @XmlElement(
      name = "CipherReference"
   )
   protected CipherReference cipherReference;
   @XmlElement(
      name = "CipherValue"
   )
   protected byte[] cipherValue;

   public CipherReference getCipherReference() {
      return this.cipherReference;
   }

   public void setCipherReference(CipherReference value) {
      this.cipherReference = value;
   }

   public byte[] getCipherValue() {
      return this.cipherValue;
   }

   public void setCipherValue(byte[] value) {
      this.cipherValue = (byte[])value;
   }
}
