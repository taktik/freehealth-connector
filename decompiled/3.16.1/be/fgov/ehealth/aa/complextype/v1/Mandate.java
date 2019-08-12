package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MandateType",
   propOrder = {"id", "period", "principal"}
)
@XmlRootElement(
   name = "Mandate"
)
public class Mandate implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   protected String id;
   @XmlElement(
      name = "Period",
      required = true
   )
   protected PeriodType period;
   @XmlElement(
      name = "Principal",
      required = true
   )
   protected PrincipalType principal;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }

   public PrincipalType getPrincipal() {
      return this.principal;
   }

   public void setPrincipal(PrincipalType value) {
      this.principal = value;
   }
}
