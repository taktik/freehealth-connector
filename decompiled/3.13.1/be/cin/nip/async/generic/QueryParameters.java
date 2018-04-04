package be.cin.nip.async.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QueryParameters",
   propOrder = {"includeIOs", "excludeIOs"}
)
public class QueryParameters implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlList
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected List<String> includeIOs;
   @XmlList
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected List<String> excludeIOs;
   @XmlAttribute(
      name = "Reference"
   )
   protected String reference;

   public List<String> getIncludeIOs() {
      if (this.includeIOs == null) {
         this.includeIOs = new ArrayList();
      }

      return this.includeIOs;
   }

   public List<String> getExcludeIOs() {
      if (this.excludeIOs == null) {
         this.excludeIOs = new ArrayList();
      }

      return this.excludeIOs;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }
}
