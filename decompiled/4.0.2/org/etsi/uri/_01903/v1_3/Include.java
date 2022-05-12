package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IncludeType"
)
@XmlRootElement(
   name = "Include"
)
public class Include implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "URI",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String uri;
   @XmlAttribute(
      name = "referencedData"
   )
   protected Boolean referencedData;

   public Include() {
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }

   public Boolean isReferencedData() {
      return this.referencedData;
   }

   public void setReferencedData(Boolean value) {
      this.referencedData = value;
   }
}
