package org.w3._2000._09.xmldsig;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KeyValueType",
   propOrder = {"content"}
)
@XmlRootElement(
   name = "KeyValue"
)
public class KeyValue {
   @XmlElementRefs({@XmlElementRef(
   name = "RSAKeyValue",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = RSAKeyValue.class,
   required = false
), @XmlElementRef(
   name = "DSAKeyValue",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = DSAKeyValue.class,
   required = false
)})
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<java.lang.Object> content;

   public List<java.lang.Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }
}
