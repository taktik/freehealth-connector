package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDTEMPORALITY;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "temporalityType",
   propOrder = {"cd"}
)
public class TemporalityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDTEMPORALITY cd;

   public TemporalityType() {
   }

   public CDTEMPORALITY getCd() {
      return this.cd;
   }

   public void setCd(CDTEMPORALITY value) {
      this.cd = value;
   }
}
