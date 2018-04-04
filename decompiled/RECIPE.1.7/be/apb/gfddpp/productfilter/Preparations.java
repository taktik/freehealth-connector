package be.apb.gfddpp.productfilter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "preparations",
   propOrder = {"preparation"}
)
public class Preparations {
   protected List<Preparation> preparation;

   public List<Preparation> getPreparation() {
      if (this.preparation == null) {
         this.preparation = new ArrayList();
      }

      return this.preparation;
   }
}
