package be.fgov.ehealth.messageservices.core.v1;

import be.fgov.ehealth.standards.kmehr.schema.v1.ErrorMyCarenetType;
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
   protected List<ErrorMyCarenetType> errors;

   public boolean isIscomplete() {
      return this.iscomplete;
   }

   public void setIscomplete(boolean value) {
      this.iscomplete = value;
   }

   public List<ErrorMyCarenetType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }
}
