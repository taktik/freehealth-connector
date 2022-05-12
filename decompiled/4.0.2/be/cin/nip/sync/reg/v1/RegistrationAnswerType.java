package be.cin.nip.sync.reg.v1;

import be.cin.types.v1.DetailType;
import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "registrationAnswerType",
   propOrder = {"answerDetails", "anies"}
)
public class RegistrationAnswerType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected List<DetailType> answerDetails;
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;
   @XmlAttribute(
      name = "serviceName",
      required = true
   )
   protected String serviceName;
   @XmlAttribute(
      name = "status",
      required = true
   )
   protected RegistrationStatus status;
   @XmlAttribute(
      name = "startDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "endDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public RegistrationAnswerType() {
   }

   public List<DetailType> getAnswerDetails() {
      if (this.answerDetails == null) {
         this.answerDetails = new ArrayList();
      }

      return this.answerDetails;
   }

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String value) {
      this.serviceName = value;
   }

   public RegistrationStatus getStatus() {
      return this.status;
   }

   public void setStatus(RegistrationStatus value) {
      this.status = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
