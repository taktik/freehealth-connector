package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "X509DataType",
   propOrder = {"x509SKI", "x509SubjectName", "x509Certificate", "x509CRL", "x509IssuerSerial"}
)
@XmlRootElement(
   name = "X509Data"
)
public class X509Data implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "X509SKI"
   )
   protected List<byte[]> x509SKI;
   @XmlElement(
      name = "X509SubjectName"
   )
   protected List<String> x509SubjectName;
   @XmlElement(
      name = "X509Certificate"
   )
   protected List<byte[]> x509Certificate;
   @XmlElement(
      name = "X509CRL"
   )
   protected List<byte[]> x509CRL;
   @XmlElement(
      name = "X509IssuerSerial"
   )
   protected List<X509IssuerSerialType> x509IssuerSerial;

   public List<byte[]> getX509SKI() {
      if (this.x509SKI == null) {
         this.x509SKI = new ArrayList();
      }

      return this.x509SKI;
   }

   public List<String> getX509SubjectName() {
      if (this.x509SubjectName == null) {
         this.x509SubjectName = new ArrayList();
      }

      return this.x509SubjectName;
   }

   public List<byte[]> getX509Certificate() {
      if (this.x509Certificate == null) {
         this.x509Certificate = new ArrayList();
      }

      return this.x509Certificate;
   }

   public List<byte[]> getX509CRL() {
      if (this.x509CRL == null) {
         this.x509CRL = new ArrayList();
      }

      return this.x509CRL;
   }

   public List<X509IssuerSerialType> getX509IssuerSerial() {
      if (this.x509IssuerSerial == null) {
         this.x509IssuerSerial = new ArrayList();
      }

      return this.x509IssuerSerial;
   }
}
