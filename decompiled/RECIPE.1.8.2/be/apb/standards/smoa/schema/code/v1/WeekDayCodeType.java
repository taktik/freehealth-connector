package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDWEEKDAY;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "WeekDayCodeType",
   propOrder = {"cd"}
)
public class WeekDayCodeType extends AbstractWeekDayCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDWEEKDAY cd;

   public CDWEEKDAY getCd() {
      return this.cd;
   }

   public void setCd(CDWEEKDAY value) {
      this.cd = value;
   }
}
