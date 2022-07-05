package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeType",
   propOrder = {"attributeValues"}
)
@XmlRootElement(
   name = "Attribute"
)
public class Attribute implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttributeValue",
      nillable = true
   )
   protected List<Object> attributeValues;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlAttribute(
      name = "NameFormat"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String nameFormat;
   @XmlAttribute(
      name = "FriendlyName"
   )
   protected String friendlyName;
   @XmlAnyAttribute
   private Map<QName, String> otherAttributes = new HashMap();

   public Attribute() {
   }

   public List<Object> getAttributeValues() {
      if (this.attributeValues == null) {
         this.attributeValues = new ArrayList();
      }

      return this.attributeValues;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getNameFormat() {
      return this.nameFormat;
   }

   public void setNameFormat(String value) {
      this.nameFormat = value;
   }

   public String getFriendlyName() {
      return this.friendlyName;
   }

   public void setFriendlyName(String value) {
      this.friendlyName = value;
   }

   public Map<QName, String> getOtherAttributes() {
      return this.otherAttributes;
   }
}
