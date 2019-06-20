package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IDPEntryType"
)
@XmlRootElement(
   name = "IDPEntry"
)
public class IDPEntry implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "ProviderID",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String providerID;
   @XmlAttribute(
      name = "Name"
   )
   protected String name;
   @XmlAttribute(
      name = "Loc"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String loc;

   public String getProviderID() {
      return this.providerID;
   }

   public void setProviderID(String value) {
      this.providerID = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getLoc() {
      return this.loc;
   }

   public void setLoc(String value) {
      this.loc = value;
   }
}
