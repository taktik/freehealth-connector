package be.fgov.ehealth.globalmedicalfile.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CareProviderType",
   propOrder = {"nihii", "physicalPerson", "organization"}
)
public class CareProviderType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nihii",
      required = true
   )
   protected NihiiType nihii;
   @XmlElement(
      name = "PhysicalPerson"
   )
   protected IdType physicalPerson;
   @XmlElement(
      name = "Organization"
   )
   protected IdType organization;

   public NihiiType getNihii() {
      return this.nihii;
   }

   public void setNihii(NihiiType value) {
      this.nihii = value;
   }

   public IdType getPhysicalPerson() {
      return this.physicalPerson;
   }

   public void setPhysicalPerson(IdType value) {
      this.physicalPerson = value;
   }

   public IdType getOrganization() {
      return this.organization;
   }

   public void setOrganization(IdType value) {
      this.organization = value;
   }
}
