package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameIDType",
   propOrder = {"value"}
)
public class NameIDType {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Format"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String format;
   @XmlAttribute(
      name = "SPProvidedID"
   )
   protected String spProvidedID;
   @XmlAttribute(
      name = "NameQualifier"
   )
   protected String nameQualifier;
   @XmlAttribute(
      name = "SPNameQualifier"
   )
   protected String spNameQualifier;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getFormat() {
      return this.format;
   }

   public void setFormat(String value) {
      this.format = value;
   }

   public String getSPProvidedID() {
      return this.spProvidedID;
   }

   public void setSPProvidedID(String value) {
      this.spProvidedID = value;
   }

   public String getNameQualifier() {
      return this.nameQualifier;
   }

   public void setNameQualifier(String value) {
      this.nameQualifier = value;
   }

   public String getSPNameQualifier() {
      return this.spNameQualifier;
   }

   public void setSPNameQualifier(String value) {
      this.spNameQualifier = value;
   }
}
