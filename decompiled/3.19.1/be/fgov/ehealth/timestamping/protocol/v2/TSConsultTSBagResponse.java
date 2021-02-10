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
   name = "TSConsultTSBagResponseType",
   propOrder = {"error", "tsBags"}
)
@XmlRootElement(
   name = "TSConsultTSBagResponse"
)
public class TSConsultTSBagResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;
   @XmlElement(
      name = "TSBag"
   )
   protected List<TSBagType> tsBags;

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public List<TSBagType> getTSBags() {
      if (this.tsBags == null) {
         this.tsBags = new ArrayList();
      }

      return this.tsBags;
   }
}
