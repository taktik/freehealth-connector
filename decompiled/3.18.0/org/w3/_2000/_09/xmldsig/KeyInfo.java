package org.w3._2000._09.xmldsig;

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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KeyInfoType",
   propOrder = {"content"}
)
@XmlRootElement(
   name = "KeyInfo"
)
public class KeyInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "KeyValue",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = KeyValue.class
), @XmlElementRef(
   name = "SPKIData",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = SPKIData.class
), @XmlElementRef(
   name = "PGPData",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = PGPData.class
), @XmlElementRef(
   name = "X509Data",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = X509Data.class
), @XmlElementRef(
   name = "RetrievalMethod",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = RetrievalMethod.class
), @XmlElementRef(
   name = "MgmtData",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class
), @XmlElementRef(
   name = "KeyName",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class
)})
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<java.lang.Object> content;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public List<java.lang.Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
