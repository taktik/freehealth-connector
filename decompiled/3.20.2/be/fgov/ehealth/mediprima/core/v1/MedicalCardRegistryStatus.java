package be.fgov.ehealth.mediprima.core.v1;

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
   name = "MedicalCardRegistryStatusType",
   propOrder = {"medicalCardRegistryMessages"}
)
@XmlRootElement(
   name = "MedicalCardRegistryStatus"
)
public class MedicalCardRegistryStatus implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MedicalCardRegistryMessage",
      required = true
   )
   protected List<MedicalCardRegistryMessageType> medicalCardRegistryMessages;

   public List<MedicalCardRegistryMessageType> getMedicalCardRegistryMessages() {
      if (this.medicalCardRegistryMessages == null) {
         this.medicalCardRegistryMessages = new ArrayList();
      }

      return this.medicalCardRegistryMessages;
   }
}
