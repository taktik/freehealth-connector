package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.CDCONTACTPERSON;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MaxSetContactPersonType",
   propOrder = {"name", "familyName", "relationshipType", "relationshipDescription", "telecom"}
)
public class MaxSetContactPersonType extends AbstractPersonType {
   @XmlElement(
      required = true
   )
   protected List<String> name;
   @XmlElement(
      required = true
   )
   protected String familyName;
   @XmlSchemaType(
      name = "token"
   )
   protected CDCONTACTPERSON relationshipType;
   protected String relationshipDescription;
   protected List<AbstractTelecomType> telecom;

   public List<String> getName() {
      if (this.name == null) {
         this.name = new ArrayList();
      }

      return this.name;
   }

   public String getFamilyName() {
      return this.familyName;
   }

   public void setFamilyName(String value) {
      this.familyName = value;
   }

   public CDCONTACTPERSON getRelationshipType() {
      return this.relationshipType;
   }

   public void setRelationshipType(CDCONTACTPERSON value) {
      this.relationshipType = value;
   }

   public String getRelationshipDescription() {
      return this.relationshipDescription;
   }

   public void setRelationshipDescription(String value) {
      this.relationshipDescription = value;
   }

   public List<AbstractTelecomType> getTelecom() {
      if (this.telecom == null) {
         this.telecom = new ArrayList();
      }

      return this.telecom;
   }
}
