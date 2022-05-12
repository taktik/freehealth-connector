package be.cin.mycarenet._1_0.carenet.types;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NurseContractualCareUpdateDetailType",
   propOrder = {"consultantDoctor", "provider", "visitDate", "newValue", "paliatifPatient"}
)
@XmlRootElement(
   name = "NurseContractualCareUpdateDetail"
)
public class NurseContractualCareUpdateDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConsultantDoctor",
      required = true
   )
   protected String consultantDoctor;
   @XmlElement(
      name = "Provider",
      required = true
   )
   protected String provider;
   @XmlElement(
      name = "VisitDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime visitDate;
   @XmlElement(
      required = true
   )
   protected NurseContractualCareDetailType newValue;
   @XmlElement(
      name = "PaliatifPatient"
   )
   protected Boolean paliatifPatient;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected MessageNatureType type;

   public NurseContractualCareUpdateDetail() {
   }

   public String getConsultantDoctor() {
      return this.consultantDoctor;
   }

   public void setConsultantDoctor(String value) {
      this.consultantDoctor = value;
   }

   public String getProvider() {
      return this.provider;
   }

   public void setProvider(String value) {
      this.provider = value;
   }

   public DateTime getVisitDate() {
      return this.visitDate;
   }

   public void setVisitDate(DateTime value) {
      this.visitDate = value;
   }

   public NurseContractualCareDetailType getNewValue() {
      return this.newValue;
   }

   public void setNewValue(NurseContractualCareDetailType value) {
      this.newValue = value;
   }

   public Boolean isPaliatifPatient() {
      return this.paliatifPatient;
   }

   public void setPaliatifPatient(Boolean value) {
      this.paliatifPatient = value;
   }

   public MessageNatureType getType() {
      return this.type;
   }

   public void setType(MessageNatureType value) {
      this.type = value;
   }
}
