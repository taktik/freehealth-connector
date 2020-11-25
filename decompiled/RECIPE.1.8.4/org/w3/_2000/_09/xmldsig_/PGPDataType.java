package org.w3._2000._09.xmldsig_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PGPDataType",
   propOrder = {"content"}
)
public class PGPDataType {
   @XmlElementRefs({@XmlElementRef(
   name = "PGPKeyID",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "PGPKeyPacket",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = JAXBElement.class,
   required = false
)})
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> content;

   public List<Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }
}
