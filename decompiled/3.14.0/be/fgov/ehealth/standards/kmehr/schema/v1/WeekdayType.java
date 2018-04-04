package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDWEEKDAY;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "weekdayType",
   propOrder = {"cd"}
)
@XmlSeeAlso({Weekday.class})
public class WeekdayType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDWEEKDAY cd;

   public CDWEEKDAY getCd() {
      return this.cd;
   }

   public void setCd(CDWEEKDAY value) {
      this.cd = value;
   }
}
