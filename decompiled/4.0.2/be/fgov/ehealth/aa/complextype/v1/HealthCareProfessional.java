package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HealthCareProfessionalType",
   propOrder = {"professions"}
)
@XmlRootElement(
   name = "HealthCareProfessional"
)
public class HealthCareProfessional extends IndividualType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Profession",
      required = true
   )
   protected List<ProfessionV3> professions;

   public HealthCareProfessional() {
   }

   public List<ProfessionV3> getProfessions() {
      if (this.professions == null) {
         this.professions = new ArrayList();
      }

      return this.professions;
   }
}
