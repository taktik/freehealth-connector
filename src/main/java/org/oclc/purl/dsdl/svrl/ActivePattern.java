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
   name = "active-pattern"
)
public class ActivePattern {
   @XmlAttribute(
      name = "document"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String document;
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
      name = "name"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String name;
   @XmlAttribute(
      name = "role"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String role;

   public ActivePattern() {
   }

   public String getDocument() {
      return this.document;
   }

   public void setDocument(String value) {
      this.document = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String value) {
      this.role = value;
   }
}
