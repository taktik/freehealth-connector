package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenderHistoryType",
   propOrder = {"gender"}
)
public class GenderHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Gender",
      required = true
   )
   protected GenderType gender;

   public GenderHistoryType() {
   }

   public GenderType getGender() {
      return this.gender;
   }

   public void setGender(GenderType value) {
      this.gender = value;
   }
}
