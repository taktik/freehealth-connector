package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.compounding.common.CompoundingIngredientKeyType;
import be.fgov.ehealth.dics.core.v4.compounding.common.SynonymType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCompoundingIngredientType",
   propOrder = {"synonyms"}
)
public class ConsultCompoundingIngredientType extends CompoundingIngredientKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Synonym",
      required = true
   )
   protected List<SynonymType> synonyms;

   public List<SynonymType> getSynonyms() {
      if (this.synonyms == null) {
         this.synonyms = new ArrayList();
      }

      return this.synonyms;
   }
}
