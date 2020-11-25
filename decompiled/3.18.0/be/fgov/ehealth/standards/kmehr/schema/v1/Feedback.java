package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"reportrequested", "text"}
)
public class Feedback implements Serializable {
   private static final long serialVersionUID = 1L;
   protected boolean reportrequested;
   protected TextType text;

   public boolean isReportrequested() {
      return this.reportrequested;
   }

   public void setReportrequested(boolean value) {
      this.reportrequested = value;
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }
}
