package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DementiaType",
   propOrder = {"doctor"}
)
public class DementiaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Doctor"
   )
   protected String doctor;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected DementiaTypeType type;

   public String getDoctor() {
      return this.doctor;
   }

   public void setDoctor(String value) {
      this.doctor = value;
   }

   public DementiaTypeType getType() {
      return this.type;
   }

   public void setType(DementiaTypeType value) {
      this.type = value;
   }
}
