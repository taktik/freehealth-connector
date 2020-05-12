package be.apb.standards.gfddpp.request;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dataSpecificParametersMedicationSchemeTimestamps",
   propOrder = {"clientMessageId", "patientINSS"}
)
public class DataSpecificParametersMedicationSchemeTimestamps {
   protected String clientMessageId;
   @XmlElement(
      required = true
   )
   protected List<String> patientINSS;

   public String getClientMessageId() {
      return this.clientMessageId;
   }

   public void setClientMessageId(String value) {
      this.clientMessageId = value;
   }

   public List<String> getPatientINSS() {
      if (this.patientINSS == null) {
         this.patientINSS = new ArrayList();
      }

      return this.patientINSS;
   }
}
