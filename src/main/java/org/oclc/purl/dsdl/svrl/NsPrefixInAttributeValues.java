package org.oclc.purl.dsdl.svrl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "ns-prefix-in-attribute-values"
)
public class NsPrefixInAttributeValues {
   @XmlAttribute(
      name = "prefix",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String prefix;
   @XmlAttribute(
      name = "uri",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String uri;

   public NsPrefixInAttributeValues() {
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String value) {
      this.prefix = value;
   }

   public String getUri() {
      return this.uri;
   }

   public void setUri(String value) {
      this.uri = value;
   }
}
