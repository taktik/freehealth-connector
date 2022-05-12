package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceType",
   propOrder = {"anies"}
)
public class ReferenceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;
   @XmlAttribute(
      name = "URI",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String uri;

   public ReferenceType() {
   }

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }
}
