package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResultsType",
   propOrder = {"details", "errorDetail", "errorStack", "subResults", "extension"}
)
public class ResultsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Details",
      required = true
   )
   protected DetailsType details;
   @XmlElement(
      name = "ErrorDetail"
   )
   protected String errorDetail;
   @XmlElement(
      name = "ErrorStack"
   )
   protected String errorStack;
   @XmlElement(
      name = "SubResults"
   )
   protected List<SubResultsType> subResults;
   @XmlElement(
      name = "Extension"
   )
   protected ExtensionType extension;

   public DetailsType getDetails() {
      return this.details;
   }

   public void setDetails(DetailsType value) {
      this.details = value;
   }

   public String getErrorDetail() {
      return this.errorDetail;
   }

   public void setErrorDetail(String value) {
      this.errorDetail = value;
   }

   public String getErrorStack() {
      return this.errorStack;
   }

   public void setErrorStack(String value) {
      this.errorStack = value;
   }

   public List<SubResultsType> getSubResults() {
      if (this.subResults == null) {
         this.subResults = new ArrayList();
      }

      return this.subResults;
   }

   public ExtensionType getExtension() {
      return this.extension;
   }

   public void setExtension(ExtensionType value) {
      this.extension = value;
   }
}
