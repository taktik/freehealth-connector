package org.w3._2000._09.xmldsig;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "X509IssuerSerialType",
   propOrder = {"x509IssuerName", "x509SerialNumber"}
)
public class X509IssuerSerialType {
   @XmlElement(
      name = "X509IssuerName",
      required = true
   )
   protected String x509IssuerName;
   @XmlElement(
      name = "X509SerialNumber",
      required = true
   )
   protected BigInteger x509SerialNumber;

   public String getX509IssuerName() {
      return this.x509IssuerName;
   }

   public void setX509IssuerName(String value) {
      this.x509IssuerName = value;
   }

   public BigInteger getX509SerialNumber() {
      return this.x509SerialNumber;
   }

   public void setX509SerialNumber(BigInteger value) {
      this.x509SerialNumber = value;
   }
}
