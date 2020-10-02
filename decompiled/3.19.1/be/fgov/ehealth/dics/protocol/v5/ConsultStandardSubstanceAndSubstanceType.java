package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultStandardSubstanceAndSubstanceType",
   propOrder = {"substanceCodes"}
)
public class ConsultStandardSubstanceAndSubstanceType extends ConsultStandardSubstanceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubstanceCode"
   )
   protected List<String> substanceCodes;

   public List<String> getSubstanceCodes() {
      if (this.substanceCodes == null) {
         this.substanceCodes = new ArrayList();
      }

      return this.substanceCodes;
   }
}
