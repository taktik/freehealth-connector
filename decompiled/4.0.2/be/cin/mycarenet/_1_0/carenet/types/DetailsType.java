package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailsType",
   propOrder = {"details"}
)
public class DetailsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Detail"
   )
   protected List<DetailType> details;

   public DetailsType() {
   }

   public List<DetailType> getDetails() {
      if (this.details == null) {
         this.details = new ArrayList();
      }

      return this.details;
   }
}
