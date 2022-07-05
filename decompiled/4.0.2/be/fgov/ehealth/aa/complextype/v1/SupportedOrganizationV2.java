package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SupportedOrganizationTypeV2"
)
@XmlRootElement(
   name = "SupportedOrganizationV2"
)
public class SupportedOrganizationV2 extends OrganizationDescriberTypeV2 implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "resourceEnumeration",
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      required = true
   )
   protected String resourceEnumeration;

   public SupportedOrganizationV2() {
   }

   public String getResourceEnumeration() {
      return this.resourceEnumeration;
   }

   public void setResourceEnumeration(String value) {
      this.resourceEnumeration = value;
   }
}
