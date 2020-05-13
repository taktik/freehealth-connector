package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "partyIdentification",
   propOrder = {"prescriberId", "executorId", "patientId"}
)
@XmlRootElement(
   name = "partyIdentification"
)
public class PartyIdentification implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String prescriberId;
   protected String executorId;
   protected String patientId;

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String value) {
      this.prescriberId = value;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String value) {
      this.executorId = value;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }
}
