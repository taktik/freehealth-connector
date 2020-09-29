package org.w3._2000._09.xmldsig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RSAKeyValueType",
   propOrder = {"modulus", "exponent"}
)
@XmlRootElement(
   name = "RSAKeyValue"
)
public class RSAKeyValue {
   @XmlElement(
      name = "Modulus",
      required = true
   )
   protected byte[] modulus;
   @XmlElement(
      name = "Exponent",
      required = true
   )
   protected byte[] exponent;

   public byte[] getModulus() {
      return this.modulus;
   }

   public void setModulus(byte[] value) {
      this.modulus = (byte[])value;
   }

   public byte[] getExponent() {
      return this.exponent;
   }

   public void setExponent(byte[] value) {
      this.exponent = (byte[])value;
   }
}
