package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCompoundingFormulaResponseType",
   propOrder = {"compoundingFormulas"}
)
@XmlRootElement(
   name = "FindCompoundingFormulaResponse"
)
public class FindCompoundingFormulaResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CompoundingFormula"
   )
   protected List<ConsultCompoundingFormulaType> compoundingFormulas;

   public List<ConsultCompoundingFormulaType> getCompoundingFormulas() {
      if (this.compoundingFormulas == null) {
         this.compoundingFormulas = new ArrayList();
      }

      return this.compoundingFormulas;
   }
}
