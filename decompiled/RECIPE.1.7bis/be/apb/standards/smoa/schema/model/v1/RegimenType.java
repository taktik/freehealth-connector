package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractWeekDayCodeType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegimenType",
   propOrder = {"daynumberOrDateOrWeekday"}
)
public class RegimenType extends AbstractRegimenType {
   @XmlElements({@XmlElement(
   name = "daynumber",
   required = true,
   type = BigInteger.class
), @XmlElement(
   name = "date",
   required = true,
   type = XMLGregorianCalendar.class
), @XmlElement(
   name = "weekday",
   required = true,
   type = AbstractWeekDayCodeType.class
), @XmlElement(
   name = "daytime",
   required = true,
   type = RegimenDayTimeType.class
), @XmlElement(
   name = "quantity",
   required = true,
   type = RegimenAdministrationQuantityType.class
)})
   protected List<Object> daynumberOrDateOrWeekday;

   public List<Object> getDaynumberOrDateOrWeekday() {
      if (this.daynumberOrDateOrWeekday == null) {
         this.daynumberOrDateOrWeekday = new ArrayList();
      }

      return this.daynumberOrDateOrWeekday;
   }
}
