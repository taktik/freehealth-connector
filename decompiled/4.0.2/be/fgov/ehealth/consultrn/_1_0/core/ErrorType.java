package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ErrorType",
   propOrder = {"code", "description", "informations"}
)
public class ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected String description;
   @XmlElement(
      name = "Information"
   )
   protected List<InformationType> informations;

   public ErrorType() {
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
