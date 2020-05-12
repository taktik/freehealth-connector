package org.w3._2000._09.xmldsig;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "X509DataType",
   propOrder = {"x509IssuerSerialsAndX509SKISAndX509SubjectNames"}
)
@XmlRootElement(
   name = "X509Data"
)
public class X509Data {
   @XmlElementRefs({@XmlElementRef(
   name = "X509SubjectName",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "X509Certificate",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "X509CRL",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "X509IssuerSerial",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "X509SKI",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
)})
   @XmlAnyElement
   protected List<java.lang.Object> x509IssuerSerialsAndX509SKISAndX509SubjectNames;

   public List<java.lang.Object> getX509IssuerSerialsAndX509SKISAndX509SubjectNames() {
      if (this.x509IssuerSerialsAndX509SKISAndX509SubjectNames == null) {
         this.x509IssuerSerialsAndX509SKISAndX509SubjectNames = new ArrayList();
      }

      return this.x509IssuerSerialsAndX509SKISAndX509SubjectNames;
   }
}
