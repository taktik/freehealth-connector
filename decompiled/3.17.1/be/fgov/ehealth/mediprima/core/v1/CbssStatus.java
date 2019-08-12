package be.fgov.ehealth.mediprima.core.v1;

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
   name = "CbssStatusType",
   propOrder = {"value", "code", "description", "informations"}
)
@XmlRootElement(
   name = "CbssStatus"
)
public class CbssStatus implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected String value;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Description"
   )
   protected String description;
   @XmlElement(
      name = "Information"
   )
   protected List<InformationType> informations;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }

   public List<InformationType> getInformations() {
      if (this.informations == null) {
         this.informations = new ArrayList();
      }

      return this.informations;
   }
}
