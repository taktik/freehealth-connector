package be.fgov.ehealth.hubservices.core.v3;

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
   name = "AcknowledgeType",
   propOrder = {"iscomplete", "errors"}
)
public class AcknowledgeType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected boolean iscomplete;
   @XmlElement(
      name = "error"
   )
   protected List<ErrorType> errors;

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
}
