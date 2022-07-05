package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByTherapeuticMoietyType",
   propOrder = {"therapeuticMoietyName", "therapeuticMoietyCode"}
)
public class FindByTherapeuticMoietyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TherapeuticMoietyName"
   )
   protected String therapeuticMoietyName;
   @XmlElement(
      name = "TherapeuticMoietyCode"
   )
   protected Integer therapeuticMoietyCode;

   public FindByTherapeuticMoietyType() {
   }

   public String getTherapeuticMoietyName() {
      return this.therapeuticMoietyName;
   }

   public void setTherapeuticMoietyName(String value) {
      this.therapeuticMoietyName = value;
   }

   public Integer getTherapeuticMoietyCode() {
      return this.therapeuticMoietyCode;
   }

   public void setTherapeuticMoietyCode(Integer value) {
      this.therapeuticMoietyCode = value;
   }
}
