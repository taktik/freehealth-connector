package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultTemporaryDurationDetailsType",
   propOrder = {"note", "userProvided", "duration"}
)
public class ConsultTemporaryDurationDetailsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Note"
   )
   protected ConsultTextType note;
   @XmlElement(
      name = "UserProvided"
   )
   protected Object userProvided;
   @XmlElement(
      name = "Duration"
   )
   protected QuantityType duration;

   public ConsultTemporaryDurationDetailsType() {
   }

   public ConsultTextType getNote() {
      return this.note;
   }

   public void setNote(ConsultTextType value) {
      this.note = value;
   }

   public Object getUserProvided() {
      return this.userProvided;
   }

   public void setUserProvided(Object value) {
      this.userProvided = value;
   }

   public QuantityType getDuration() {
      return this.duration;
   }

   public void setDuration(QuantityType value) {
      this.duration = value;
   }
}
