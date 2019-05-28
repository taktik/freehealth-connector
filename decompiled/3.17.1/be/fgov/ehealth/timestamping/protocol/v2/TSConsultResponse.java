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
   name = "TSConsultResponseType",
   propOrder = {"status", "error", "tsLists"}
)
@XmlRootElement(
   name = "TSConsultResponse"
)
public class TSConsultResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      required = true
   )
   protected String status;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;
   @XmlElement(
      name = "TSList"
   )
   protected List<TimeStampIdentification> tsLists;

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public List<TimeStampIdentification> getTSLists() {
      if (this.tsLists == null) {
         this.tsLists = new ArrayList();
      }

      return this.tsLists;
   }
}
