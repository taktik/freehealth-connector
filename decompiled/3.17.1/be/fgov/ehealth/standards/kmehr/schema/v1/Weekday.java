package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"weeknumber"}
)
public class Weekday extends WeekdayType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger weeknumber;

   public BigInteger getWeeknumber() {
      return this.weeknumber;
   }

   public void setWeeknumber(BigInteger value) {
      this.weeknumber = value;
   }
}
