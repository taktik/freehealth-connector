package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BusinessAnomalyType",
   propOrder = {"code", "severity", "description", "informations"}
)
public class BusinessAnomalyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Severity",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected SeverityType severity;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected String description;
   @XmlElement(
      name = "Information"
   )
   protected List informations;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public SeverityType getSeverity() {
      return this.severity;
   }

   public void setSeverity(SeverityType value) {
      this.severity = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }

   public List getInformations() {
      if (this.informations == null) {
         this.informations = new ArrayList();
      }

      return this.informations;
   }
}
