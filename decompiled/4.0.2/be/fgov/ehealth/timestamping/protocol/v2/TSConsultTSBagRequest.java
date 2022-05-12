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
   name = "TSConsultTSBagRequestType",
   propOrder = {"idHospital", "tsLists"}
)
@XmlRootElement(
   name = "TSConsultTSBagRequest"
)
public class TSConsultTSBagRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IDHospital",
      required = true
   )
   protected String idHospital;
   @XmlElement(
      name = "TSList"
   )
   protected List<TimeStampIdentification> tsLists;

   public TSConsultTSBagRequest() {
   }

   public String getIDHospital() {
      return this.idHospital;
   }

   public void setIDHospital(String value) {
      this.idHospital = value;
   }

   public List<TimeStampIdentification> getTSLists() {
      if (this.tsLists == null) {
         this.tsLists = new ArrayList();
      }

      return this.tsLists;
   }
}
