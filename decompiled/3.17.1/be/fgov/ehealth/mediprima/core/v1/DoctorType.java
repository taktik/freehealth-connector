package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DoctorType",
   propOrder = {"healthCareProviderList"}
)
public class DoctorType extends MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "HealthCareProviderList"
   )
   protected NihiiNumberListType healthCareProviderList;

   public NihiiNumberListType getHealthCareProviderList() {
      return this.healthCareProviderList;
   }

   public void setHealthCareProviderList(NihiiNumberListType value) {
      this.healthCareProviderList = value;
   }
}
