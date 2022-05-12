package be.fgov.ehealth.medicalagreement.core.v1;

import be.fgov.ehealth.standards.kmehr.schema.v1.ErrorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "acknowledgetype",
   propOrder = {"iscomplete", "errors", "warnings"}
)
public class Acknowledgetype implements Serializable {
   private static final long serialVersionUID = 1L;
   protected boolean iscomplete;
   @XmlElement(
      name = "error"
   )
   protected List<ErrorType> errors;
   @XmlElement(
      name = "warning"
   )
   protected List<ErrorType> warnings;

   public Acknowledgetype() {
   }

   public boolean isIscomplete() {
      return this.iscomplete;
   }

   public void setIscomplete(boolean value) {
      this.iscomplete = value;
   }

   public List<ErrorType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }

   public List<ErrorType> getWarnings() {
      if (this.warnings == null) {
         this.warnings = new ArrayList();
      }

      return this.warnings;
   }
}
