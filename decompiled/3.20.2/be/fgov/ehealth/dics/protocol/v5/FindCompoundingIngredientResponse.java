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
   name = "FindCompoundingIngredientResponseType",
   propOrder = {"compoundingIngredients"}
)
@XmlRootElement(
   name = "FindCompoundingIngredientResponse"
)
public class FindCompoundingIngredientResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CompoundingIngredient"
   )
   protected List<ConsultCompoundingIngredientType> compoundingIngredients;

   public List<ConsultCompoundingIngredientType> getCompoundingIngredients() {
      if (this.compoundingIngredients == null) {
         this.compoundingIngredients = new ArrayList();
      }

      return this.compoundingIngredients;
   }
}
