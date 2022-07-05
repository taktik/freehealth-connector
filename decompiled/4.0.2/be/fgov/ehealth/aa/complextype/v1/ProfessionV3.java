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
   name = "ProfessionTypeV3",
   propOrder = {"professionCodes", "professionFriendlyNames", "nihii", "specialities"}
)
@XmlRootElement(
   name = "ProfessionV3"
)
public class ProfessionV3 implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProfessionCode",
      required = true
   )
   protected List<ProfessionCode> professionCodes;
   @XmlElement(
      name = "ProfessionFriendlyName",
      required = true
   )
   protected List<NameType> professionFriendlyNames;
   @XmlElement(
      name = "NIHII"
   )
   protected String nihii;
   @XmlElement(
      name = "Speciality"
   )
   protected List<Speciality> specialities;

   public ProfessionV3() {
   }

   public List<ProfessionCode> getProfessionCodes() {
      if (this.professionCodes == null) {
         this.professionCodes = new ArrayList();
      }

      return this.professionCodes;
   }

   public List<NameType> getProfessionFriendlyNames() {
      if (this.professionFriendlyNames == null) {
         this.professionFriendlyNames = new ArrayList();
      }

      return this.professionFriendlyNames;
   }

   public String getNIHII() {
      return this.nihii;
   }

   public void setNIHII(String value) {
      this.nihii = value;
   }

   public List<Speciality> getSpecialities() {
      if (this.specialities == null) {
         this.specialities = new ArrayList();
      }

      return this.specialities;
   }
}
