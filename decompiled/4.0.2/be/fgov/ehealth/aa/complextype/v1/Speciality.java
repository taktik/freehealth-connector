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
   name = "SpecialityType",
   propOrder = {"specialityCode", "specialityFriendlyNames"}
)
@XmlRootElement(
   name = "Speciality"
)
public class Speciality implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SpecialityCode",
      required = true
   )
   protected String specialityCode;
   @XmlElement(
      name = "SpecialityFriendlyName",
      required = true
   )
   protected List<NameType> specialityFriendlyNames;

   public Speciality() {
   }

   public String getSpecialityCode() {
      return this.specialityCode;
   }

   public void setSpecialityCode(String value) {
      this.specialityCode = value;
   }

   public List<NameType> getSpecialityFriendlyNames() {
      if (this.specialityFriendlyNames == null) {
         this.specialityFriendlyNames = new ArrayList();
      }

      return this.specialityFriendlyNames;
   }
}
