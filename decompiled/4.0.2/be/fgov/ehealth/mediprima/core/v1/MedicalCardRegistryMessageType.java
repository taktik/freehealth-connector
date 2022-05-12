package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicalCardRegistryMessageType",
   propOrder = {"reasonCode", "source", "communications"}
)
public class MedicalCardRegistryMessageType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReasonCode",
      required = true
   )
   protected String reasonCode;
   @XmlElement(
      name = "Source",
      required = true
   )
   protected String source;
   @XmlElement(
      name = "Communication",
      required = true
   )
   protected List<NameType> communications;
   @XmlAttribute(
      name = "Severity",
      required = true
   )
   protected String severity;

   public MedicalCardRegistryMessageType() {
   }

   public String getReasonCode() {
      return this.reasonCode;
   }

   public void setReasonCode(String value) {
      this.reasonCode = value;
   }

   public String getSource() {
      return this.source;
   }

   public void setSource(String value) {
      this.source = value;
   }

   public List<NameType> getCommunications() {
      if (this.communications == null) {
         this.communications = new ArrayList();
      }

      return this.communications;
   }

   public String getSeverity() {
      return this.severity;
   }

   public void setSeverity(String value) {
      this.severity = value;
   }
}
