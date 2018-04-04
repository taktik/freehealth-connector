package be.apb.gfddpp.productfilter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ranges",
   propOrder = {"abstractRange"}
)
public class Ranges {
   @XmlElementRef(
      name = "abstractRange",
      type = JAXBElement.class,
      required = false
   )
   protected List<JAXBElement<? extends RangesType>> abstractRange;

   public List<JAXBElement<? extends RangesType>> getAbstractRange() {
      if (this.abstractRange == null) {
         this.abstractRange = new ArrayList();
      }

      return this.abstractRange;
   }
}
