package be.fgov.ehealth.mycarenet.commons.core.v4;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RoutingType",
   propOrder = {"subject", "careReceiver", "referenceDate", "period"}
)
public class RoutingType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Subject"
   )
   protected SubjectType subject;
   @XmlElement(
      name = "CareReceiver"
   )
   protected CareReceiverIdType careReceiver;
   @XmlElement(
      name = "ReferenceDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime referenceDate;
   @XmlElement(
      name = "Period"
   )
   protected PeriodType period;

   public RoutingType() {
   }

   public SubjectType getSubject() {
      return this.subject;
   }

   public void setSubject(SubjectType value) {
      this.subject = value;
   }

   public CareReceiverIdType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverIdType value) {
      this.careReceiver = value;
   }

   public DateTime getReferenceDate() {
      return this.referenceDate;
   }

   public void setReferenceDate(DateTime value) {
      this.referenceDate = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }
}
