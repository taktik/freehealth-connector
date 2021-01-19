package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AgreementMethodType",
   propOrder = {"content"}
)
@XmlRootElement(
   name = "AgreementMethod"
)
public class AgreementMethod implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "OriginatorKeyInfo",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class
), @XmlElementRef(
   name = "RecipientKeyInfo",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class
), @XmlElementRef(
   name = "KA-Nonce",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class
)})
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> content;
   @XmlAttribute(
      name = "Algorithm",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String algorithm;

   public List<Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public String getAlgorithm() {
      return this.algorithm;
   }

   public void setAlgorithm(String value) {
      this.algorithm = value;
   }
}
