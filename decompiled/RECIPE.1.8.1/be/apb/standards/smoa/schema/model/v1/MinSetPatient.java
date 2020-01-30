package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MinSetPatient",
   propOrder = {"personId", "name", "familyName"}
)
public class MinSetPatient extends AbstractPatientType {
   @XmlElement(
      required = true
   )
   protected AbstractPersonIdType personId;
   @XmlElement(
      required = true
   )
   protected List<String> name;
   @XmlElement(
      required = true
   )
   protected String familyName;

   public AbstractPersonIdType getPersonId() {
      return this.personId;
   }

   public void setPersonId(AbstractPersonIdType value) {
      this.personId = value;
   }

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
}
