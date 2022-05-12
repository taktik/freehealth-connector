package be.fgov.ehealth.timestamping.protocol.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TSConsultRequestType",
   propOrder = {"idHospital", "period", "tsLists"}
)
@XmlRootElement(
   name = "TSConsultRequest"
)
public class TSConsultRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IDHospital",
      required = true
   )
   protected String idHospital;
   @XmlElement(
      name = "Period",
      required = true
   )
   protected PeriodType period;
   @XmlElement(
      name = "TSList"
   )
   protected List<TimeStampIdentification> tsLists;

   public TSConsultRequest() {
   }

   public String getIDHospital() {
      return this.idHospital;
   }

   public void setIDHospital(String value) {
      this.idHospital = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }

   public List<TimeStampIdentification> getTSLists() {
      if (this.tsLists == null) {
         this.tsLists = new ArrayList();
      }

      return this.tsLists;
   }
}
