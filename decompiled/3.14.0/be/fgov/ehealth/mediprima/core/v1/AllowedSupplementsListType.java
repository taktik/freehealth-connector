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
   name = "AllowedSupplementsListType",
   propOrder = {"supplementTypes"}
)
public class AllowedSupplementsListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SupplementType",
      required = true
   )
   protected List<String> supplementTypes;

   public List<String> getSupplementTypes() {
      if (this.supplementTypes == null) {
         this.supplementTypes = new ArrayList();
      }

      return this.supplementTypes;
   }
}
