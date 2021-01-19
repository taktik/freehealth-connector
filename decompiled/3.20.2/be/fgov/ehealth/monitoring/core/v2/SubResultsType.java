package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubResultsType",
   propOrder = {"subDetails", "subErrorDetail", "subErrorStack"}
)
public class SubResultsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubDetails",
      required = true
   )
   protected DetailsType subDetails;
   @XmlElement(
      name = "SubErrorDetail"
   )
   protected String subErrorDetail;
   @XmlElement(
      name = "SubErrorStack"
   )
   protected String subErrorStack;

   public DetailsType getSubDetails() {
      return this.subDetails;
   }

   public void setSubDetails(DetailsType value) {
      this.subDetails = value;
   }

   public String getSubErrorDetail() {
      return this.subErrorDetail;
   }

   public void setSubErrorDetail(String value) {
      this.subErrorDetail = value;
   }

   public String getSubErrorStack() {
      return this.subErrorStack;
   }

   public void setSubErrorStack(String value) {
      this.subErrorStack = value;
   }
}
