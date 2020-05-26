package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDINCAPACITYREASON;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "incapacityreasonType",
   propOrder = {"cd"}
)
public class IncapacityreasonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDINCAPACITYREASON cd;

   public CDINCAPACITYREASON getCd() {
      return this.cd;
   }

   public void setCd(CDINCAPACITYREASON value) {
      this.cd = value;
   }
}
