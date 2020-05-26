package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"minref", "maxref", "context"}
)
public class Refvalue implements Serializable {
   private static final long serialVersionUID = 1L;
   protected MinrefType minref;
   protected MaxrefType maxref;
   @XmlElement(
      required = true
   )
   protected TextType context;

   public MinrefType getMinref() {
      return this.minref;
   }

   public void setMinref(MinrefType value) {
      this.minref = value;
   }

   public MaxrefType getMaxref() {
      return this.maxref;
   }

   public void setMaxref(MaxrefType value) {
      this.maxref = value;
   }

   public TextType getContext() {
      return this.context;
   }

   public void setContext(TextType value) {
      this.context = value;
   }
}
