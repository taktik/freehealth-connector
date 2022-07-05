package org.oclc.purl.dsdl.svrl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
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
   name = "fired-rule"
)
public class FiredRule {
   @XmlAttribute(
      name = "id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "context",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String context;
   @XmlAttribute(
      name = "role"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String role;
   @XmlAttribute(
      name = "flag"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String flag;

   public FiredRule() {
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getContext() {
      return this.context;
   }

   public void setContext(String value) {
      this.context = value;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String value) {
      this.role = value;
   }

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String value) {
      this.flag = value;
   }
}
