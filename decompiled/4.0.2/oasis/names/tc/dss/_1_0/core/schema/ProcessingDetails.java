package oasis.names.tc.dss._1_0.core.schema;

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
   name = "",
   propOrder = {"validDetails", "indeterminateDetails", "invalidDetails"}
)
@XmlRootElement(
   name = "ProcessingDetails"
)
public class ProcessingDetails implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValidDetail"
   )
   protected List<DetailType> validDetails;
   @XmlElement(
      name = "IndeterminateDetail"
   )
   protected List<DetailType> indeterminateDetails;
   @XmlElement(
      name = "InvalidDetail"
   )
   protected List<DetailType> invalidDetails;

   public ProcessingDetails() {
   }

   public List<DetailType> getValidDetails() {
      if (this.validDetails == null) {
         this.validDetails = new ArrayList();
      }

      return this.validDetails;
   }

   public List<DetailType> getIndeterminateDetails() {
      if (this.indeterminateDetails == null) {
         this.indeterminateDetails = new ArrayList();
      }

      return this.indeterminateDetails;
   }

   public List<DetailType> getInvalidDetails() {
      if (this.invalidDetails == null) {
         this.invalidDetails = new ArrayList();
      }

      return this.invalidDetails;
   }
}
