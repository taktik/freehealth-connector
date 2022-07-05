package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HospitalizationType",
   propOrder = {"hospitalList"}
)
public class HospitalizationType extends MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "HospitalList"
   )
   protected NihiiNumberListType hospitalList;

   public HospitalizationType() {
   }

   public NihiiNumberListType getHospitalList() {
      return this.hospitalList;
   }

   public void setHospitalList(NihiiNumberListType value) {
      this.hospitalList = value;
   }
}
