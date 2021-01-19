package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.virtual.common.VmpGroupKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultVmpGroupType",
   propOrder = {"name", "noGenericPrescriptionReason", "noSwitchReason"}
)
public class ConsultVmpGroupType extends VmpGroupKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "NoGenericPrescriptionReason"
   )
   protected NoGenericPrescriptionReasonType noGenericPrescriptionReason;
   @XmlElement(
      name = "NoSwitchReason"
   )
   protected NoSwitchReasonType noSwitchReason;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public NoGenericPrescriptionReasonType getNoGenericPrescriptionReason() {
      return this.noGenericPrescriptionReason;
   }

   public void setNoGenericPrescriptionReason(NoGenericPrescriptionReasonType value) {
      this.noGenericPrescriptionReason = value;
   }

   public NoSwitchReasonType getNoSwitchReason() {
      return this.noSwitchReason;
   }

   public void setNoSwitchReason(NoSwitchReasonType value) {
      this.noSwitchReason = value;
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
