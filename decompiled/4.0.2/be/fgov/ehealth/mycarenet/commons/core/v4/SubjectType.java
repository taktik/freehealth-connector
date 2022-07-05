package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectType",
   propOrder = {"identifiers", "attributes"}
)
@XmlSeeAlso({ActorType.class})
public class SubjectType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   protected List<Identifier> identifiers;
   @XmlElement(
      name = "Attribute"
   )
   protected List<AttributeType> attributes;
   @XmlAttribute(
      name = "Type"
   )
   protected String type;
   @XmlAttribute(
      name = "SubType"
   )
   protected String subType;

   public SubjectType() {
   }

   public List<Identifier> getIdentifiers() {
      if (this.identifiers == null) {
         this.identifiers = new ArrayList();
      }

      return this.identifiers;
   }

   public List<AttributeType> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getSubType() {
      return this.subType;
   }

   public void setSubType(String value) {
      this.subType = value;
   }
}
