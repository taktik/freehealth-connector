package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "recipeLinesComplexType",
   propOrder = {"abstractRecipeLine"}
)
public class RecipeLinesComplexType {
   @XmlElementRef(
      name = "abstract-RecipeLine",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected List<JAXBElement<? extends AbstractRecipeLineType>> abstractRecipeLine;

   public List<JAXBElement<? extends AbstractRecipeLineType>> getAbstractRecipeLine() {
      if (this.abstractRecipeLine == null) {
         this.abstractRecipeLine = new ArrayList();
      }

      return this.abstractRecipeLine;
   }
}
