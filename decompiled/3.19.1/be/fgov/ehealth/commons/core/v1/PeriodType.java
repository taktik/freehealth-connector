package be.fgov.ehealth.commons.core.v1;

import be.fgov.ehealth.xsd.bindingsupport.XmlDateAdapter;
import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodType",
   propOrder = {"beginDate", "endDate"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BeginDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected Calendar beginDate;
   @XmlElement(
      name = "EndDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected Calendar endDate;

   public Calendar getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(Calendar value) {
      this.beginDate = value;
   }

   public Calendar getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Calendar value) {
      this.endDate = value;
   }
}
