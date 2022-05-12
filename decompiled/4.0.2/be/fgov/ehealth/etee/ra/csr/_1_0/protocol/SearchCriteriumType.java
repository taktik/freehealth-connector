package be.fgov.ehealth.etee.ra.csr._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchCriteriumType",
   propOrder = {"type", "value", "applicationID"}
)
public class SearchCriteriumType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected String value;
   @XmlElement(
      name = "ApplicationID"
   )
   protected String applicationID;

   public SearchCriteriumType() {
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getApplicationID() {
      return this.applicationID;
   }

   public void setApplicationID(String value) {
      this.applicationID = value;
   }
}
