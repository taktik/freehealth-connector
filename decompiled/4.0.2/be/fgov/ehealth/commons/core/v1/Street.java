package be.fgov.ehealth.commons.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"descriptions"}
)
public class Street implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected List<LocalisedString> descriptions;

   public Street() {
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
