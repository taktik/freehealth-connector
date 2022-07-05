package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CarmedAttributedListType",
   propOrder = {"carmeds"}
)
public class CarmedAttributedListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Carmed",
      required = true
   )
   protected List<ConsultCarmedInterventionResultType> carmeds;

   public CarmedAttributedListType() {
   }

   public List<ConsultCarmedInterventionResultType> getCarmeds() {
      if (this.carmeds == null) {
         this.carmeds = new ArrayList();
      }

      return this.carmeds;
   }
}
